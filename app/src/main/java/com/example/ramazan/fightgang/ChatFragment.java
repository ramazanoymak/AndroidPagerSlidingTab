package com.example.ramazan.fightgang;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramazan on 13.05.2016.
 */
public class ChatFragment extends Fragment {

    EditText edtMessage;
    Button btnSendMessage;
    ListView listViewMessage;
    Channel channel;
    String message;
    List<ChatModel> chatModelList;
    ChatsAdapter adapter;

    ProgressBar spinner;

    public ChatFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chat_view, container, false);
        channel=new Channel();
        chatModelList=new ArrayList<ChatModel>();
        listViewMessage = (ListView) rootView.findViewById(R.id.listView_Chats);
        btnSendMessage = (Button) rootView.findViewById(R.id.btn_sendMessage);
        edtMessage = (EditText) rootView.findViewById(R.id.edt_message);

        spinner = (ProgressBar)rootView.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                message=edtMessage.getText().toString().trim();

                if (((MainActivity)getActivity()).isConnected()){

                    if (!message.isEmpty())
                    {
                        new RequestSendMessageThread().execute();
                        edtMessage.setText("");
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(), "Please fill required content",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Please Check Your Network!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((MainActivity)getActivity()).isConnected()){
            new  RequestGetChatsThread().execute();
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Please Check Your Network!", Toast.LENGTH_LONG).show();
        }
    }

    public  class RequestGetChatsThread extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpGetChats();
                chatModelList=channel.getChatList(_strReq);

            }catch (Exception e){}
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (chatModelList!=null){

                adapter=new ChatsAdapter(getActivity().getApplicationContext(), chatModelList);
                listViewMessage.setAdapter(adapter);
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }

    public  class RequestSendMessageThread extends AsyncTask<String,String,String> {

        ChatModel chatModel=new ChatModel();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            try
            {
                String _strReq= channel.HttpPostSendMessage(message);
                chatModel=channel.getChatMessage(_strReq);

            }catch (Exception e){}
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (chatModel!=null){
                chatModelList.add(0,chatModel);
                adapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Data receipt fail", Toast.LENGTH_LONG).show();
            }
            spinner.setVisibility(View.GONE);
        }
    }
}
