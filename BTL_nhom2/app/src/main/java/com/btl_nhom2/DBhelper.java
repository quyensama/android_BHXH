package com.btl_nhom2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DBhelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    public static final String DBNAME = "quanlybhxh.db";
    private static final int DATABASE_VERSION = 1;
    protected static final String TABLE_USER = "tblUsers";
    private static final String MA_BHXH = "maBhxh";
    private static final String TEN_USER = "tenUser";
    private static final String NGAY_SINH = "ngaySinh";
    private static final String GIOI_TINH = "gioiTinh";
    private static final String SO_CMND = "soCmnd";
    private static final String DIA_CHI = "diaChi";
    private static final String SDT = "sdt";
    private static final String MUC_LUONG = "mucLuong";
    private static final String TT_CHITRA = "tinhTrangChiTra";
    private static final String TT_NGHIHUU = "tinhTrangNghiHuu";
    private static final String CREATE_USER_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                    MA_BHXH + " INTEGER NOT NULL PRIMARY KEY ," +
                    TEN_USER + " TEXT NOT NULL," +
                    NGAY_SINH + " DATE NOT NULL," +
                    GIOI_TINH + " TEXT NOT NULL," +
                    SO_CMND + " INTEGER NOT NULL," +
                    SDT + " TEXT NOT NULL," +
                    DIA_CHI + " TEXT NOT NULL," +
                    MUC_LUONG + " INTEGER NOT NULL," +
                    TT_CHITRA + " INTEGER NOT NULL," +
                    TT_NGHIHUU + " TEXT NOT NULL" +
                    ")" ;
    protected static final String TABLE_USER_DETAIL = "tblUserDetails";
    private static final String TU_THANG = "tuThang";
    private static final String DEN_THANG = "denThang";
    private static final String TIEN_BHXH = "tienBHXH";
    private static final String TT_MUCDONG = "tinhTrangMucDong";
    private static final String CREATE_USER_DETAIL_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER_DETAIL + " (" +
                    TU_THANG + " TEXT NOT NULL," +
                    DEN_THANG + " TEXT NOT NULL," +
                    TT_MUCDONG + " TEXT NOT NULL," +
                    TIEN_BHXH + " TEXT NOT NULL," +
                    MA_BHXH + " INTEGER NOT NULL REFERENCES tblUsers,"
                    +"PRIMARY KEY (" + MA_BHXH + "," + TU_THANG + "," + DEN_THANG + ")"
                    +")";

    private static DBhelper sInstance;
    public static DBhelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBhelper(context.getApplicationContext());
        }
        return sInstance;
    }
    public DBhelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        Log.e(TAG, "Create CSDL: ");
        try {
            MyDB.setLocale(Locale.getDefault());
            MyDB.setVersion(1);
            MyDB.execSQL("create Table TAIKHOAN(tentk TEXT primary key, matkhau TEXT)");
            MyDB.execSQL(CREATE_USER_TABLE_SQL);
            MyDB.execSQL(CREATE_USER_DETAIL_TABLE);
        }
        catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        Log.e(TAG, "Update CSDL: ");
//        MyDB.execSQL("drop Table if exists TAIKHOAN");
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAIL);
        onCreate(MyDB);
    }

    public boolean isTableExists(SQLiteDatabase MyDB,String tableName)
    {
        Cursor cursor = MyDB.rawQuery(
                "select DISTINCT tbl_name " +
                        "from sqlite_master " +
                        "where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public boolean insertInfor(users users){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MA_BHXH, users.getMaBHXH());
        values.put(TEN_USER, users.getTenuser());
        values.put(NGAY_SINH, users.getNgaysinh());
        values.put(GIOI_TINH, users.getGioitinh());
        values.put(SO_CMND, users.getSoCMND());
        values.put(SDT, users.getSDT());
        values.put(DIA_CHI, users.getDiachi());
        values.put(MUC_LUONG, users.getMucluong());
        values.put(TT_CHITRA, users.getTinhtrangchitra());
        values.put(TT_NGHIHUU, users.getTinhtrangnghihuu());
        long rowId = db.insert(TABLE_USER, null, values);
        db.close();
        if(rowId != -1)
            return true;
        return false;
    }
    public boolean insertInforDetail(user_detail user_detail){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TU_THANG, user_detail.getTuthang());
        values.put(DEN_THANG, user_detail.getDenthang());
        values.put(TT_MUCDONG, user_detail.getTinhtrangmucdong());
        values.put(TIEN_BHXH, user_detail.getTienBHXH());
        values.put(MA_BHXH, user_detail.getMaBHXH());
        long rowId = db.insert(TABLE_USER_DETAIL, null, values);
        db.close();
        if(rowId != -1)
            return true;
        return false;
    }

    public int getTotal(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+ TABLE_USER;
        Cursor cursor = db.rawQuery(sql,null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }

    public int getTotalDetail(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT b."+ TU_THANG + ", b." + DEN_THANG + ", b." + TT_MUCDONG +
                ", b." + TIEN_BHXH + ", a." + MA_BHXH +  " FROM " +  TABLE_USER_DETAIL + " b LEFT JOIN " + TABLE_USER +
                " a ON a." + MA_BHXH + " = b." + MA_BHXH;
        Cursor cursor = db.rawQuery(sql,null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }
    public ArrayList<user_detail> getAllInforDetail() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<user_detail> listsdetail = new ArrayList<user_detail>();
        String sql = "SELECT b."+ TU_THANG + ", b." + DEN_THANG + ", b." + TT_MUCDONG +
                ", b." + TIEN_BHXH + ", a." + MA_BHXH +  " FROM " +  TABLE_USER + " a LEFT JOIN " + TABLE_USER_DETAIL +
                " b ON a." + MA_BHXH + " = b." + MA_BHXH;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                listsdetail.add(new user_detail(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return listsdetail;
    }

    public ArrayList<users> getAllInfor() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<users> lists = new ArrayList<users>();
        String sql = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                        ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }
    public ArrayList<users> getAllInforLuongHuu1() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<users> lists = new ArrayList<users>();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + TT_NGHIHUU + " = 'Đã nghỉ hưu'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }
    public ArrayList<users> getAllInforLuongHuu2() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<users> lists = new ArrayList<users>();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + TT_NGHIHUU + " = 'Chưa nghỉ hưu'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }
    public ArrayList<users> getAllInforChiTra1() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<users> lists = new ArrayList<users>();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + TT_CHITRA + " = 'Đã trả'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }
    public ArrayList<users> getAllInforChiTra2() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<users> lists = new ArrayList<users>();
        String sql = "SELECT * FROM " + TABLE_USER + " WHERE " + TT_CHITRA + " = 'Chưa trả'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                lists.add(new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                ));

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return lists;
    }

    public users getInforByID(String tableName, String fieldName, int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        users lists = new users();
        Cursor cursor = db.query(tableName, null, fieldName+"= "+id,
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
                lists = new users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7),
                        cursor.getString(8),
                        cursor.getString(9)
                );
            cursor.close();
        }
        return lists;
    }

    public int updateUserDetail(user_detail user_detail, String id, String tuthang, String denthang){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TU_THANG, user_detail.getTuthang());
        values.put(DEN_THANG,user_detail.getDenthang());
        values.put(TT_MUCDONG, user_detail.getTinhtrangmucdong());
        values.put(TIEN_BHXH, user_detail.getTienBHXH());
        values.put(MA_BHXH,user_detail.getMaBHXH());
        int rowEff = db.update(TABLE_USER_DETAIL, values, MA_BHXH + "=? and "+TU_THANG+"=? and "+DEN_THANG+"=?",new String[]{id,tuthang,denthang} );
        db.close();
        return rowEff;
    }

    public Boolean insertData(String tentk, String matkhau){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("tentk", tentk);
        contentValues.put("matkhau", matkhau);
        long result = MyDB.insert("TAIKHOAN", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checktentk(String tentk) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TAIKHOAN where tentk = ?", new String[]{tentk});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checktentkmatkhau(String tentk, String matkhau){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TAIKHOAN where tentk = ? and matkhau = ?", new String[] {tentk,matkhau});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}