package com.btl_nhom2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class chitietmucdong_adapter extends ArrayAdapter {
    Activity context = null;
    ArrayList <user_detail> myArray = null;
    int resource;

    public chitietmucdong_adapter( Activity context, int resource, ArrayList<user_detail> myArray) {
        super(context, resource, myArray);
        this.context=context;
        this.resource=resource;
        this.myArray=myArray;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resource, null);
        if(myArray.size()>0 && position>=0){
            final TextView txtTuThang, txtDenThang, txtTienBHXH, txtTinhTrang;
            txtTuThang = convertView.findViewById(R.id.txtTuThang);
            txtDenThang = convertView.findViewById(R.id.txtDenThang);
            txtTinhTrang = convertView.findViewById(R.id.txtTinhTrang);
            txtTienBHXH = convertView.findViewById(R.id.txtTienBHXH);
            final user_detail us = myArray.get(position);

            txtTuThang.setText(us.getTuthang());
            txtDenThang.setText(us.getDenthang());
            txtTinhTrang.setText(us.getTinhtrangmucdong()+"");
            txtTienBHXH.setText(us.getTienBHXH()+"");
        }
        return convertView;
    }
}
