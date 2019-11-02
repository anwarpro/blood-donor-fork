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

public class BankList extends ArrayAdapter<Bank> {
    private Activity context;
    private List<Bank> bankList;

    public BankList(Activity context, List<Bank> bankList)
    {
        super(context,R.layout.list_bank,bankList);
        this.context = context;
        this.bankList = bankList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_bank,null,true);
        TextView txtName = (TextView) listViewItem.findViewById(R.id.txt_namebank);
        TextView txtAddress = (TextView) listViewItem.findViewById(R.id.txt_addressbank);
        TextView txtPhone = (TextView) listViewItem.findViewById(R.id.txt_phonebank);
        Bank bank = bankList.get(position);

        txtName.setText(bank.getBankName());
        txtAddress.setText(bank.getBankAddress());
        txtPhone.setText(bank.getBankPhone());
        return listViewItem;
    }
}

