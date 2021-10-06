package com.btl_nhom2;

import java.util.ArrayList;

public class fakeCSDL {
    ArrayList<users> usersArrayList = new ArrayList<>();
    ArrayList<user_detail> user_detailArrayList = new ArrayList<>();

    // fakeCSDL();
    public ArrayList fakeusers(){
        users u = new users(123450,"Nguyen Duc Nam","23/07/1950",1,123123120,"01241440","Ha noi",8000000,"Đã trả","Chưa nghỉ hưu");
        users u1 = new users(123451,"Nguyen Phuong Thao","15/02/1980",0,123123121,"01241441","Ha nam",8000000,"Chưa trả","Chưa nghỉ hưu");
        users u2 = new users(123452,"Nguyen Thanh Tam","20/10/1955",0,123123122,"01241442","Nam dinh",8000000,"Chưa trả","Chưa nghỉ hưu");
        users u3 = new users(123453,"Nguyen Duc Long","20/10/1966",1,123123123,"01241443","Hai phong",8000000,"Chưa trả","Đã nghỉ hưu");

        usersArrayList.add(u);
        usersArrayList.add(u1);
        usersArrayList.add(u2);
        usersArrayList.add(u3);
        return  usersArrayList;
    }

    public ArrayList fakeuser_detail() {
        user_detail u = new user_detail("01/2020","06/2020","Đã đóng",1600000,1);
        user_detail u1 = new user_detail("06/2020","12/2020","Đã đóng",1600000,1);
        user_detail u2 = new user_detail("01/2021","06/2021","Chưa đóng",1600000,1);
        user_detail u3 = new user_detail("01/2020","06/2020","Đã đóng",1600000,2);
        user_detail u4 = new user_detail("06/2020","12/2020","Đã đóng",1600000,2);
        user_detail u5 = new user_detail("01/2020","06/2020","Đã đóng",1600000,3);
        user_detail u6 = new user_detail("06/2021","12/2021","Đã đóng",1600000,3);
        user_detail u7 = new user_detail("01/2019","06/2019","Đã đóng",1600000,4);
        user_detail u8 = new user_detail("06/2019","12/2019","Đã đóng",1600000,4);
        user_detail u9 = new user_detail("01/2020","06/2020","Đã đóng",1600000,4);

        user_detailArrayList.add(u);
        user_detailArrayList.add(u1);
        user_detailArrayList.add(u2);
        user_detailArrayList.add(u3);
        user_detailArrayList.add(u4);
        user_detailArrayList.add(u5);
        user_detailArrayList.add(u6);
        user_detailArrayList.add(u7);
        user_detailArrayList.add(u8);
        user_detailArrayList.add(u9);

        return  user_detailArrayList;
    }
}
