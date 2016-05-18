package com.example.ramazan.fightgang;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ramazan on 14.05.2016.
 */
public class UserAdapterArena extends BaseAdapter{

    Button btnAttack;

    TextView txtUserName;
    TextView txtLevel;
    TextView txtStamina;
    Context _c;
    List<UserModel> _data;
    Activity _activity;
    Channel _channel;
    ProgressBar ProgresstBar = null;

    public UserAdapterArena(Context c,List<UserModel> data,Activity activity,Channel channel){
        _c=c;
        _data=data;
        _activity=activity;
        _channel=channel;
    }
    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater v1 = (LayoutInflater) _c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = v1.inflate(R.layout.arenacell_view, null);
        }
         btnAttack = (Button) v.findViewById(R.id.btn_Attack);
         btnAttack.setVisibility(View.GONE);
         btnAttack.setEnabled(false);

         txtUserName = (TextView) v.findViewById(R.id.arena_txtUserName);
         txtStamina = (TextView) v.findViewById(R.id.txtArena_stamina);
         txtLevel = (TextView) v.findViewById(R.id.txtArena_level);
         ProgresstBar = (ProgressBar)v.findViewById(R.id.HorizantProgressBar);

         ProgresstBar.setProgress(70);

        if (_data.get(position).getId()!=LoginActivity.User.getId())
        {
            btnAttack.setVisibility(View.VISIBLE);
            txtUserName.setText(_data.get(position).getAlias());
        }
        else {
            txtUserName.setText(_data.get(position).getAlias()+"(Me)");
            }

        if (ArenaFragment.isUserinArena==1)
        {
            btnAttack.setEnabled(true);
        }
        txtStamina.setText(String.valueOf(_data.get(position).getStamina())+"/"+
        _data.get(position).getMaxStamina());
        txtLevel.setText(String.valueOf(_data.get(position).getLevel()));

        btnAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            new RequestAttackThread().execute();
            }
        });

        return v;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked


                    break;
            }
        }
    };

    public  class RequestAttackThread extends AsyncTask<String,String,String> {
        String[] attackData=new  String[2];
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= _channel.HttpPostAttack();
                attackData=_channel.getAttackData(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (attackData!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(_activity);
                builder.setTitle("Success!").setMessage("You hit  \""+attackData[0]+"\" for "+attackData[1]+" damage !").setCancelable(false)
                        .setPositiveButton("OK", dialogClickListener).show();
            }
            else {
                Toast.makeText(_activity.getApplicationContext(), "Data Receip Fail", Toast.LENGTH_LONG).show();
            }

        }
    }
}
