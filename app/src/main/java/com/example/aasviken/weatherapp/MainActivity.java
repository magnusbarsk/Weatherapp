package com.example.aasviken.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this,LangtidsActivity.class);



        final String url = "http://www.yr.no/sted/Norge/Nordland/Rana/Mo/varsel.xml";

        final Button langtidsvarselknapp = (Button) findViewById(R.id.langtidsvarselknapp);

        final TextView temperatur = (TextView) findViewById(R.id.temperatur);
        final TextView winds = (TextView) findViewById(R.id.windspeed);
        final TextView windd = (TextView) findViewById(R.id.winddirection);
        final TextView trykk = (TextView) findViewById(R.id.pressure);


        langtidsvarselknapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        new GetFeedTask(new GetFeedTask.AsyncResponse() {

            @Override
            public void processFinish(WeatherData feed) {
                Temperature temp = feed.getForecast().getTimeList().get(0).getTemperature();
                temperatur.setText(String.valueOf(temp.getValue()+" °"+temp.getUnit()));



                WindSpeed windss = feed.getForecast().getTimeList().get(0).getWindSpeed();
                winds.setText(String.valueOf(windss.getName()+" "+windss.getMps()+"M/s"));

                WindDirection windDirection = feed.getForecast().getTimeList().get(0).getWindDirection();
                windd.setText(String.valueOf(windDirection.getName()+" "+"\n"+"("+windDirection.getDeg()+")"));

                Pressure pressure = feed.getForecast().getTimeList().get(0).getPressure();
                trykk.setText(String.valueOf(pressure.getUnit()+" "+pressure.getValue()));


            }
        }).execute(url);





    }



}
