package com.example.ramazan.fightgang;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private Button btnLogin;
    private Button btnRegister;
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputAlias;
    public static  UserModel User;
    String UserName;
    String Password;
    String Alias;
    Channel channel;
    ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);
        }
        channel=new Channel();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin.setEnabled(false);
        btnRegister.setEnabled(false);

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        User=new UserModel();
        inputUsername = (EditText) findViewById(R.id.edtUserName);
        inputPassword = (EditText) findViewById(R.id.edtPassword);
        inputAlias = (EditText) findViewById(R.id.edtAlias);
        inputUsername.addTextChangedListener(textWatcher);
        inputPassword.addTextChangedListener(textWatcher);
        inputAlias.addTextChangedListener(textWatcher);

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isConnected()) {
                    UserName = inputUsername.getText().toString().trim();
                    Password = inputPassword.getText().toString().trim();

                    String[] dataForRegueqst = new String[2];
                    dataForRegueqst[0] = UserName;
                    dataForRegueqst[1] = Password;

                    new RequestLoginThread().execute(dataForRegueqst);

                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Your Network!", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Register button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (isConnected()) {
                    UserName = inputUsername.getText().toString().trim();
                    Password = inputPassword.getText().toString().trim();
                    Alias = inputAlias.getText().toString().trim();

                    String[] dataForRegueqst = new String[3];
                    dataForRegueqst[0] = UserName;
                    dataForRegueqst[1] = Password;
                    dataForRegueqst[2] = Alias;

                    new RequestRegisterThread().execute(dataForRegueqst);

                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Your Network!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };

    private  void checkFieldsForEmptyValues(){

         UserName = inputUsername.getText().toString().trim();
         Password = inputPassword.getText().toString().trim();
         Alias = inputAlias.getText().toString().trim();

        if(!UserName.isEmpty() && !Password.isEmpty())
        {
            btnLogin.setEnabled(true);
        }
        if(!UserName.isEmpty() && !Password.isEmpty()&& !Alias.isEmpty())
        {
            btnRegister.setEnabled(true);
        }

    }

    public  class RequestLoginThread extends AsyncTask<String,String,String> {

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

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (User!=null){
                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), "Data Receipt Fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }

    public  class RequestRegisterThread extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpPostRegister(params[0], params[1],params[2]);
                User=channel.getUser(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (User!=null){

                Intent i=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), "Data receipt Fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }


    public  boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
