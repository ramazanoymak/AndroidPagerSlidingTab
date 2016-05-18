package com.example.ramazan.fightgang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ramazan on 15.05.2016.
 */
public class ChatsAdapter extends BaseAdapter {

    TextView txtMessage;
    TextView txtFrom;
    TextView txtDate;
    Context _c;
    List<ChatModel> _data;
    public  ChatsAdapter(Context c,List<ChatModel> data){
        _c=c;
        _data=data;
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
            v = v1.inflate(R.layout.chat_cell_view, null);
        }
        txtMessage = (TextView) v.findViewById(R.id.txtChat_message);
        txtFrom = (TextView) v.findViewById(R.id.txt_Chat_from);
        txtDate = (TextView) v.findViewById(R.id.txt_Chat_date);

        txtMessage.setText(_data.get(position).getMessage());
        txtFrom.setText(_data.get(position).getFrom()+" , ");
        txtDate.setText(_data.get(position).getDate());

        return v;
    }
}
