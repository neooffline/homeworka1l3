package ru.neooffline.homework_a1l2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Weather weather;
    private TextView currentWeather;
    private Button changeValue;
    private SharedPreferences spf;
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
        currentWeather = findViewById(R.id.weather);
        makeToastNLog("App Created");
        String current;
        if (savedInstanceState == null) {
            weather = new Weather();
            current = weather.getFullWeather();
        }else{
            weather = savedInstanceState.getParcelable("weather");
            try {
                current = weather.getFullWeather();
                Log.d("Weather","get weather" + current);
            } catch (NullPointerException n) {
                Log.e("Weather",n.getMessage());
                current = "Error while reading values";
            }
        }
        currentWeather.setText(current);
        changeValue = findViewById(R.id.measure_weather);
        changeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", "Button pressed");
                weather.setFullWeather();
                String current = weather.getFullWeather();
                currentWeather.setText(current);
                Log.d("TextField", "TextField filled with: "
                        + current);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadText();
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
        saveText();
        makeToastNLog("App Destroyed");
    }

    private void makeToastNLog(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle", message);
    }
    private void saveText(){
        spf = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString("SAVED_WEATHER",currentWeather.getText().toString());
        ed.apply();
        makeToastNLog("Saving Weather");
    }
    private void loadText(){
        spf = getPreferences(MODE_PRIVATE);
        String savedWeather = spf.getString("SAVED_WEATHER","");
        currentWeather.setText(savedWeather);
        makeToastNLog("Loading Weather");
    }
}
