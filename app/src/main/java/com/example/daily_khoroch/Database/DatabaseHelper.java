package com.example.daily_khoroch.Database;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.daily_khoroch.Model.Khoroch_Model;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String NAME = "Khoroch";
    static final int VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, NAME,null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table daily_khoroch" +
                        "(id INTEGER primary key AUTOINCREMENT," +
                        "Amount int," +
                        "Short_desc text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists daily_khoroch");
        onCreate(sqLiteDatabase);
    }

    public void insertKhoroch(Khoroch_Model model){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Amount",model.getAmount());
        values.put("Short_desc",model.getDesc());

        database.insert("daily_khoroch",null,values);
        Log.d(TAG, "**********Data inserted Success ***************");
    }

    public ArrayList<Khoroch_Model> getKhoroch(){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor;
        cursor = database.rawQuery("select Amount,Short_desc from daily_khoroch",null);
        ArrayList<Khoroch_Model> list;
        if(cursor.moveToFirst()){
            list = new ArrayList<>();
            do{
                Khoroch_Model model = new Khoroch_Model(cursor.getInt(0),cursor.getString(1));
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
