package com.example.mymap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UserList(Activity context, List<User> userList)
    {
        super(context, R.layout.list_layout, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewitem = inflater.inflate(R.layout.list_layout,null,true);
        TextView textviewname = (TextView) listViewitem.findViewById(R.id.txt_name);
        TextView textviewphone = (TextView) listViewitem.findViewById(R.id.txt_phone);
        TextView textviewbloodgroup = (TextView) listViewitem.findViewById(R.id.txt_bloodgroup);

        User user = userList.get(position);

        textviewname.setText(user.getUserName());
        textviewphone.setText(user.getUserPhone());
        textviewbloodgroup.setText(user.getUserBlood());

        return listViewitem;

    }
}
