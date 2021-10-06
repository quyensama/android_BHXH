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

public class danhsachtaikhoandangky_adapter extends ArrayAdapter {
    Activity context = null;
    ArrayList <users> myArray = null;
    int resource;

    public danhsachtaikhoandangky_adapter( Activity context, int resource, ArrayList<users> myArray) {
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
            final TextView txtMaBHXH, txtHoTen, txtXemChiTiet;
            txtMaBHXH=(TextView) convertView.findViewById(R.id.txtMaBHXH);
            txtHoTen=(TextView) convertView.findViewById(R.id.txtHoTen);
            txtXemChiTiet=(TextView) convertView.findViewById(R.id.txtXemChiTiet);

            final users us = myArray.get(position);
            txtMaBHXH.setText(us.getMaBHXH()+"");
            txtHoTen.setText(us.getTenuser());
            if(us.getGioitinh()==1){
                txtXemChiTiet.setText("Nam");

            }else {
                txtXemChiTiet.setText("Nữ");
            }
        }
        return convertView;
    }
}
