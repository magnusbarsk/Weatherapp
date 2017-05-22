package com.example.aasviken.weatherapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class SearchResultsActivity extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult_activity);

        final TextView input = (TextView) findViewById(R.id.sokefelt2);
        final Button sokeknapp = (Button) findViewById(R.id.sokeknapp);
        final LinearLayout linearliste = (LinearLayout) findViewById(R.id.linearliste);

        final Button findloc = (Button)findViewById(R.id.findloc);
        final Intent lokintent = new Intent(this,LokActivity.class);
        findloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(lokintent);
            }
        });


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

        final String fil = text;

        final Context theContext = SearchResultsActivity.this;
        final Intent intent = new Intent(this,MainActivity.class);
        sokeknapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String brukerinput = input.getText().toString().toLowerCase();
                System.out.println(brukerinput);
                if(brukerinput.equals("")){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(theContext);
                    dialog.setCancelable(true);
                    dialog.setPositiveButton("Lukk", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setMessage("Feltet kan ikke være tomt");
                    dialog.create().show();
                }
                else{
                    linearliste.removeAllViews();
                    String[] steder = fil.split("\n");
                    String test = "";
                    for(String s:steder) {
                        String[] elementer = s.split("\t");

                        if(elementer[1].toLowerCase().contains(brukerinput)) {

                            test+= elementer[1]+"\n";

                            TextView tv = new TextView(theContext);
                            tv.setId((int)System.currentTimeMillis()% Integer.MAX_VALUE);
                            tv.setText(elementer[1]+" | "+elementer[6]+","+elementer[7]);
                            tv.setTag(elementer[12]);
                            tv.setPadding(0,10,0,10);
                            tv.setTextColor((Color.parseColor("#ffffff")));
                            tv.setTextSize(24);


                            tv.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    intent.putExtra("xml",v.getTag().toString());
                                    startActivity(intent);

                                }
                            });
                            linearliste.addView(tv);


                        }
                    }

                }

            }

        });
    }
}


