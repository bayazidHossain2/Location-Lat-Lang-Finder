package com.example.daily_khoroch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.daily_khoroch.Adapters.AmountAdapter;
import com.example.daily_khoroch.Database.DatabaseHelper;
import com.example.daily_khoroch.Model.Khoroch_Model;

import java.util.ArrayList;

public class DataListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);

        recyclerView = findViewById(R.id.rv_show);
        databaseHelper = new DatabaseHelper(DataListActivity.this);

        ArrayList<Khoroch_Model> list = databaseHelper.getKhoroch();
        AmountAdapter adapter = new AmountAdapter(DataListActivity.this,list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DataListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }
}