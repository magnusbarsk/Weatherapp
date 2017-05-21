package com.example.aasviken.weatherapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.options_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.search);
        View view = menu.findItem(R.id.search).getActionView();


        final Intent intent = new Intent(this,SearchResultsActivity.class);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
               startActivity(intent);

                return true;
            }
        });
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent sokeResultat = getIntent();

        final Intent intent = new Intent(this,LangtidsActivity.class);

        String protoUrl;
        if(sokeResultat.hasExtra("xml")) {
            protoUrl = sokeResultat.getExtras().getString("xml");
        }
        else {
            protoUrl = "http://www.yr.no/sted/Norge/Nordland/Rana/Mo/varsel.xml";
        }
        final String url = protoUrl;

        final Button langtidsvarselknapp = (Button) findViewById(R.id.langtidsvarselknapp);
        final TextView by = (TextView) findViewById(R.id.navn);
        final TextView temperatur = (TextView) findViewById(R.id.temperatur);
        final TextView winds = (TextView) findViewById(R.id.windspeed);
        final TextView windd = (TextView) findViewById(R.id.winddirection);
        final TextView trykk = (TextView) findViewById(R.id.pressure);


        langtidsvarselknapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("xml",url);
                startActivity(intent);
            }
        });

        new GetFeedTask(new GetFeedTask.AsyncResponse() {

            @Override
            public void processFinish(WeatherData feed) {

                String location = feed.getLocation().getName();
                by.setText(location);



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
