package com.example.aasviken.weatherapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.List;

public class LangtidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.langtids_activity);
        final String url = "http://www.yr.no/sted/Norge/Nordland/Rana/Mo/varsel.xml";

        final TextView langtidstext = (TextView) findViewById(R.id.langtidstext);
        final TextView langtidsvarsel = (TextView) findViewById(R.id.langtidsvarsel);
        langtidsvarsel.setMovementMethod(new ScrollingMovementMethod());
        new GetFeedTask(new GetFeedTask.AsyncResponse(){
            @Override
            public void processFinish(WeatherData feed) {

                List<Time> langtids = feed.getForecast().getTimeList();
                String neste = "";

                for (int i = 1;i<langtids.size(); i++){
                    Time t = langtids.get(i);
                    String [] test  = t.getFrom().split("T");
                    neste+=(String.valueOf(t.getFrom()+test[0]+" Til "+t.getTo()+test[0]+""+t.getTemperature().getValue()+" °"+t.getTemperature().getUnit()+"\n"));
                }
                langtidsvarsel.setText(neste);
            }
        }).execute(url);


    }
}