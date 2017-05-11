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
                String currentDato = "";
                for (int i = 1;i<langtids.size(); i++){
                    Time t = langtids.get(i);
                    String [] from  = t.getFrom().split("T");
                    String [] to = t.getTo().split("T");
                    String [] tidsplittFrom = from[1].split(":");
                    String [] tidsplittTo = to[1].split(":");
                    String [] datosplit = from[0].split("-");

                    if(!currentDato.equals(from[0])) {
                        neste+= "\n"+datosplit[2]+"-"+datosplit[1]+"-"+datosplit[0]+"\n";
                        currentDato = from[0];
                    }


                    neste+=tidsplittFrom[0]+":"+tidsplittFrom[1]+" Til "+" "+tidsplittTo[0]+":"+tidsplittTo[1]+" | "+t.getTemperature().getValue()+"°"+t.getTemperature().getUnit()+"\n";
                }
                langtidsvarsel.setText(neste);
            }
        }).execute(url);


    }
}