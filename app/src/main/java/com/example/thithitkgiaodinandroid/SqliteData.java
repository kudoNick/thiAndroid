package com.example.thithitkgiaodinandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SqliteData  extends SQLiteOpenHelper {

    SqliteData(Context context){
        super(context,"Data.db",null,1);
    }

    public static final String TABLE_NAME = "data";

    public static final String Col_SKU = "sku";
    public static final String Col_TACGIA= "tacgia";
    public static final String  Col_ngay= "ngay";
    public static final String Col_SOTRANG= "sotrang";
    public static final String Col_GIA= "gia";
    public static final String Col_GIOUTHiEu= "gioiThieu";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " +TABLE_NAME +"("+Col_SKU+" INTEGER  PRIMARY KEY,"+Col_TACGIA+" VARCHAR,"+Col_ngay+" VARCHAR,"+Col_SOTRANG+" VARCHAR , " + Col_GIA + "  VARCHAR," + Col_GIOUTHiEu + " VARCHAR)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(Data data){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_SKU,data.SKU);
        contentValues.put(Col_TACGIA,data.tacGia);
        contentValues.put(Col_ngay,data.ngayXuatBan);
        contentValues.put(Col_SOTRANG,data.soTrang);
        contentValues.put(Col_GIA,data.gia);
        contentValues.put(Col_GIOUTHiEu,data.gioiThieu);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return  result;
    }

    public List<Data> getAllData(){
        List<Data> dataList= new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String truyVan = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan,null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){



                String sku = cursor.getString(cursor.getColumnIndex(Col_SKU));
                String tacgia = cursor.getString(cursor.getColumnIndex(Col_TACGIA));
                String ngay = cursor.getString(cursor.getColumnIndex(Col_ngay));
                String sotrang = cursor.getString(cursor.getColumnIndex(Col_SOTRANG));
                String gia = cursor.getString(cursor.getColumnIndex(Col_GIA));
                String gioiThieu = cursor.getString(cursor.getColumnIndex(Col_GIOUTHiEu));

                Data data= new Data();

                data.setSKU(sku);
                data.setGia(tacgia);
                data.setNgayXuatBan(ngay);
                data.setSoTrang(sotrang);
                data.setGia(gia);
                data.setGioiThieu(gioiThieu);

                dataList.add(data);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return  dataList;
    }
}
