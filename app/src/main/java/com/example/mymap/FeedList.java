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

public class FeedList extends ArrayAdapter<Feed> {
    private Activity context;
    private List<Feed> feedList;

    public FeedList(Activity context, List<Feed> feedList)
    {
        super(context,R.layout.list_feed,feedList);
        this.context = context;
        this.feedList = feedList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_feed,null,true);
        TextView txtname = (TextView) listViewItem.findViewById(R.id.txt_namefeed);
        TextView txtaddress = (TextView) listViewItem.findViewById(R.id.txt_addressfeed);
        TextView txtblood = (TextView) listViewItem.findViewById(R.id.txt_blood_groupfeed);
        TextView txtbloodbag = (TextView) listViewItem.findViewById(R.id.txt_bagfeed);
        TextView txtphone = (TextView) listViewItem.findViewById(R.id.txt_phonefeed);
        TextView txtdate = (TextView) listViewItem.findViewById(R.id.txt_timefeed);
        TextView txthosp = (TextView) listViewItem.findViewById(R.id.txt_areafeed);
        Feed feed = feedList.get(position);

        txtname.setText(feed.getFeedName());
        txtaddress.setText(feed.getFeedAddress());
        txtblood.setText(feed.getFeedBlood());
        txtbloodbag.setText(feed.getFeedAmount()+" Bag");
        txtphone.setText(feed.getFeedPhone());
        txtdate.setText(feed.getFeedDate());
        txthosp.setText(feed.getFeedHosp());
        return listViewItem;
    }
}
