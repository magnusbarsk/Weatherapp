package com.example.aasviken.weatherapp;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

public class SearchResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult_activity);

        //henter ut info fra norge.txt
        String text = "";
        try{
            InputStream fil = getResources().openRawResource(R.raw.norge);
            byte[] buffer = new byte[fil.available()];
            fil.read(buffer);
            fil.close();
            text = new String(buffer);

        }
        catch(IOException ex){

        }


    }
}


