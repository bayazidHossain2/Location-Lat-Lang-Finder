package com.example.daily_khoroch.Database;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.daily_khoroch.Model.LocationModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String NAME = "LocationDatabase";
    static final int VERSION = 2;

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME,null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table Location_Table" +
                        "(id INTEGER primary key AUTOINCREMENT," +
                        "Name text," +
                        "Lat text," +
                        "Lang text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Location_Table");
        onCreate(sqLiteDatabase);
    }

    public void insertLocation(LocationModel model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Name",model.getLocationName());
        values.put("Lat",model.getLat());
        values.put("Lang",model.getLang());

        database.insert("Location_Table",null,values);
        Log.d(TAG, "**********Data inserted Success ***************");
    }

    public ArrayList<LocationModel> getKhoroch(){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select Name,Lat,Lang from Location_Table",null);
        ArrayList<LocationModel> list;
        if(cursor.moveToFirst()){
            list = new ArrayList<>();
            do{
                LocationModel model = new LocationModel(cursor.getString(0),cursor.
                        getString(1),cursor.getString(2));
                list.add(model);
            }while (cursor.moveToNext());
        }else{
            list = new ArrayList<>();
        }
        cursor.close();
        database.close();
        return list;
    }
}
