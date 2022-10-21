package com.example.daily_khoroch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daily_khoroch.Database.DatabaseHelper;
import com.example.daily_khoroch.Helper.LocationPermissionGetter;
import com.example.daily_khoroch.Model.LocationModel;

public class MainActivity extends AppCompatActivity {

    int LOCATION_REFRESH_TIME = 15000; // 15 seconds to update
    int LOCATION_REFRESH_DISTANCE = 500; // 500 meters to update

    private Button show_btn, load, save, clear;
    private EditText locationName, lang, lat;
    DatabaseHelper databaseHelper;
    TextView helpMsg;
    static Location publicLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_btn = findViewById(R.id.btn_show);
        load = findViewById(R.id.btn_load);
        save = findViewById(R.id.btn_save);
        clear = findViewById(R.id.btn_clear);
        locationName = findViewById(R.id.et_location);
        lang = findViewById(R.id.et_lang);
        lat = findViewById(R.id.et_lat);
        helpMsg = findViewById(R.id.tv_msg);
        publicLocation = new Location("Test Initilizer");

        LocationPermissionGetter locationPermissionGetter = new LocationPermissionGetter(MainActivity.this);
        locationPermissionGetter.checkPermission();


        databaseHelper = new DatabaseHelper(MainActivity.this);

        show_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataListActivity.class);
                startActivity(intent);
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.
                        ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                        checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    helpMsg.setText("Permission Not granted.");
                    return;
                }else {
                    helpMsg.setText("Permission Granted.");
                }
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                        LOCATION_REFRESH_DISTANCE, mLocationListener);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LocationModel model = new LocationModel(locationName.getText().toString()+"",
                        lat.getText().toString()+"",lang.getText().toString()+"");
                databaseHelper.insertLocation(model);
                Toast.makeText(MainActivity.this,"Save Success.",Toast.LENGTH_SHORT).show();
                clearAll();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocationOnView();
                //clearAll();
            }
        });
    }

    private void clearAll(){
        locationName.setText("");
        lang.setText("");
        lat.setText("");

    }

    private void setLocationOnView(){
        lang.setText(publicLocation.getLongitude()+"");
        lat.setText(publicLocation.getLatitude()+"");
        helpMsg.setText("Provider : "+publicLocation.getProvider().toString());
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            publicLocation = location;
            setLocationOnView();
        }
    };
}