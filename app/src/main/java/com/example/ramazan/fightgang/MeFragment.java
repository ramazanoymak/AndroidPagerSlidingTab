package com.example.ramazan.fightgang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ramazan on 13.05.2016.
 */
public class MeFragment extends Fragment {


    TextView txtUserName;
    TextView txtLevel;
    TextView txtExperince;
    TextView txtStamina;
    TextView txtTotalHits;

    ProgressBar spinner;
    Channel channel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.me_view, container, false);

        channel=new Channel();
        txtUserName = (TextView) rootView.findViewById(R.id.txtUserName);
        txtLevel = (TextView) rootView.findViewById(R.id.txtLevel);
        txtExperince = (TextView) rootView.findViewById(R.id.txtExperience);
        txtStamina = (TextView) rootView.findViewById(R.id.txtStamina);

        txtTotalHits = (TextView) rootView.findViewById(R.id.txtTotalHits);
        spinner = (ProgressBar)rootView.findViewById(R.id.progressBarMe);
        spinner.setVisibility(View.GONE);

        //new RequestGetOwnProfileThread().execute();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        new RequestGetOwnProfileThread().execute();
    }

    public  class RequestGetOwnProfileThread extends AsyncTask<String,String,String> {
        UserModel User=new UserModel();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpGetProfile();
                User=channel.getUser(_strReq);

            }catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (User!=null){
        txtUserName.setText(User.getAlias());
        txtLevel.setText(String.valueOf(User.getLevel()));
        txtExperince.setText(String.valueOf(User.getExp())+"/"+String.valueOf(User.getExpNet()));
        txtStamina.setText(String.valueOf(User.getStamina()));
        txtTotalHits.setText(String.valueOf(User.getHits()));
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }


}
