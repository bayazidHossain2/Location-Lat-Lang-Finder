package com.example.daily_khoroch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.daily_khoroch.Database.DatabaseHelper;
import com.example.daily_khoroch.Model.Khoroch_Model;

public class MainActivity extends AppCompatActivity {

    private Button show_btn,spand_btn;
    private EditText mony_et,short_desc_et;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_btn = findViewById(R.id.btn_show);
        spand_btn = findViewById(R.id.btn_spand);
        mony_et = findViewById(R.id.et_mony_input);
        short_desc_et = findViewById(R.id.et_short_desc);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DataListActivity.class);
                startActivity(intent);
            }
        });

        spand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = Integer.parseInt(mony_et.getText().toString());
                String desc = ""+short_desc_et.getText().toString();
                Khoroch_Model model = new Khoroch_Model(amount,desc);
                //Toast.makeText(MainActivity.this,"Amount : "+amount,Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this,"Spend btn Clicked.",Toast.LENGTH_SHORT).show();

                databaseHelper.insertKhoroch(model);
                Toast.makeText(MainActivity.this,"Spend btn Clicked.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}