package com.example.aasviken.weatherapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;


public class Lokasjon extends AsyncTask <Double,Void,String> {

    public AsyncResponse delegate = null;
    private Context c;

    public Lokasjon (Context c, AsyncResponse delegate){
        this.delegate = delegate;
        this.c = c;
    }

    public interface AsyncResponse {
        public void processFinish(String feed);
    }


    @Override
    protected String doInBackground(Double... params) {
        double lt = params[0];
        double ln = params[1];

        String mydata = "https://www.yr.no/sted/Norge/Nordland/Rana/Mo/varsel.xml";
        Geocoder geocoder = new Geocoder(c,Locale.getDefault());
        try{
            Address addresse  = geocoder.getFromLocation(lt,ln,1).get(0);
            String sted = addresse.getLocality();
            InputStream is = c.getResources().openRawResource(R.raw.norge);
            String tekst = "";
            try{
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                tekst = new String (buffer);

            }
            catch(IOException e){
                //IOexpetions
            }
            String[] lines = tekst.split("\n");
            for(String l: lines){
                String[] rows = l.split("\t");
                if(rows[1].contains(sted)){
                    mydata = rows[12];
                    break;
                }
            }



        }
        catch(IOException e){
            //IOEXEPTIONS
        }
        catch(IndexOutOfBoundsException ex){

        }
        return mydata;


    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("Kommer så langt");
        delegate.processFinish(result);

    }
}
