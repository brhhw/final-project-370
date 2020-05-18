package edu.cuny.qc.cs.covid19;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// This main activity is to test zip_database
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order(View v){
        zip_database db = new zip_database(this);
        String result="";

        zip_code home = db.findZip("11365");
        result +="Zipcode: " + home.getZip();
        result += "\nState: "+home.getState()+", City: "+home.getCity()+", County: "+home.getCounty();
        result +="\nis in NY: "+db.isNY("11365");

        zip_code somewhere = db.findZip("11367");
        result +="\n\nZipcode: " + somewhere.getZip();
        result += "\nState: "+somewhere.getState()+", City: "+somewhere.getCity()+", County: "+somewhere.getCounty();
        result +="\nis in NY: "+db.isNY("11367");

        //distance between home and somewhere
        result +="\n\nDistance:\n in mile = "+home.distance_mile(somewhere)+" miles\n in km = "+home.distance_km(somewhere)+" km";

        zip_code otherPlace = db.findZip("98401");
        result +="\n\nZipcode: " + otherPlace.getZip();
        result += "\nState: "+otherPlace.getState()+", City: "+otherPlace.getCity()+", County: "+otherPlace.getCounty();
        result +="\nis in NY: "+db.isNY("98401");

        display(result);
    }

    public void display(String value){
        TextView x = (TextView) findViewById(R.id.result);
        x.setText(value);
    }
}
