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

public class HospitalList extends ArrayAdapter<Hospital> {
    private Activity context;
    private List<Hospital> hospitalList;

    public HospitalList(Activity context, List<Hospital> hospitalList)
    {
        super(context,R.layout.list_hospital,hospitalList);
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_hospital,null,true);
        TextView txtName = (TextView) listViewItem.findViewById(R.id.txt_namehosp);
        TextView txtAddress = (TextView) listViewItem.findViewById(R.id.txt_addresshosp);
        TextView txtPhone = (TextView) listViewItem.findViewById(R.id.txt_phonehosp);
        Hospital hospital = hospitalList.get(position);

        txtName.setText(hospital.getHospitalName());
        txtAddress.setText(hospital.getHospitalAddress());
        txtPhone.setText(hospital.getHospitalPhone());
        return listViewItem;
    }
}
