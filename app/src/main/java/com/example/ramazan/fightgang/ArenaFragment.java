package com.example.ramazan.fightgang;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramazan on 13.05.2016.
 */
public class ArenaFragment extends Fragment {


    ListView listViewArenaUser;
    Channel channel;
    public static int isUserinArena=0;
    private Switch mySwitch;

    ProgressBar spinner;

    public ArenaFragment(){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.arena_view, container, false);
        channel=new Channel();

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        listViewArenaUser = (ListView) rootView.findViewById(R.id.listview_Arena);
        mySwitch = (Switch) rootView.findViewById(R.id.mySwitch);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    isUserinArena=1;
                    new RequestEnterArenaThread().execute();
                     //on
                }else{
                    //off
                    isUserinArena=0;
                   new RequestLeaveArenaThread().execute();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity)getActivity()).isConnected()){
            if (isUserinArena==1){
                new RequestEnterArenaThread().execute();
            }
            else{
                new  RequestArenaThread().execute();
            }
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please Check Your Network!", Toast.LENGTH_LONG).show();
        }


    }

    public  class RequestArenaThread extends AsyncTask<String,String,String> {
        List<UserModel> userModelList=new ArrayList<UserModel>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpGetArena();
                userModelList=channel.getUserList(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (userModelList!=null){
                userModelList.add(0,LoginActivity.User);
                listViewArenaUser.setAdapter(new UserAdapterArena(getActivity().getApplicationContext(),
                        userModelList,getActivity(),channel));
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }

    public  class RequestEnterArenaThread extends AsyncTask<String,String,String> {
        List<UserModel> userModelList=new ArrayList<UserModel>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpPostEnterArena();
                userModelList=channel.getUserList(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (userModelList!=null){
                listViewArenaUser.setAdapter(new UserAdapterArena(getActivity().getApplicationContext(),
                        userModelList,getActivity(),channel));
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }

    public  class RequestLeaveArenaThread extends AsyncTask<String,String,String> {

        List<UserModel> userModelList=new ArrayList<UserModel>();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpDeleteLeaveArena();
                userModelList=channel.getUserList(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (userModelList!=null){
                userModelList.add(0,LoginActivity.User);
                listViewArenaUser.setAdapter(new UserAdapterArena(getActivity().getApplicationContext(),
                        userModelList,getActivity(),channel));
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }
}
