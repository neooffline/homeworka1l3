package ru.neooffline.homework_a1l2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Weather weather;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("weather", weather);
        makeToastNLog("App Save Instance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weather = savedInstanceState.getParcelable("weather");
        makeToastNLog("App Restore Instance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView currentWeather = findViewById(R.id.weather);
        makeToastNLog("App Created");
        String current;
        if (savedInstanceState == null)
            weather = new Weather();
        else {
            weather = savedInstanceState.getParcelable("weather");
            try {
                current = weather.getFullWeather();
            } catch (NullPointerException n) {
                n.printStackTrace();
                current = "NoNe";
            }
            currentWeather.setText(current);
        }
        final Button changeValue = findViewById(R.id.measure_weather);
        currentWeather.setText(weather.getFullWeather());
        changeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weather.setFullWeather();
                Log.d("Button", "Button pressed");
                currentWeather.setText(weather.getFullWeather());
                Log.d("TextField", "TextField filled");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        makeToastNLog("App Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        makeToastNLog("App onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        makeToastNLog("App Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeToastNLog("App Destroyed");
    }

    private void makeToastNLog(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle",message);
    }
}
