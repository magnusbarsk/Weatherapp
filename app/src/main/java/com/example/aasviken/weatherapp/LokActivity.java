package com.example.aasviken.weatherapp;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class LokActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lok_activity);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 3);
        } else {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

            android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double latitude = 0.0;
            double longitude = 0.0;
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
            final Intent sok = new Intent(this, MainActivity.class);
            new Lokasjon(this, new Lokasjon.AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    sok.putExtra("xml",url);
                    startActivity(sok);
                }
            }).execute(latitude, longitude);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 3:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

                    android.location.Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double latitude = 0.0;
                    double longitude = 0.0;
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }

                    final Intent sok = new Intent(this, MainActivity.class);
                    new Lokasjon(this, new Lokasjon.AsyncResponse() {
                        @Override
                        public void processFinish(String url) {
                            sok.putExtra("xml",url);
                            startActivity(sok);
                        }
                    }).execute(latitude, longitude);
                }
                break;
        }
    }
}
