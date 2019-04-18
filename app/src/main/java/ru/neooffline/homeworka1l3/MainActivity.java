package ru.neooffline.homeworka1l3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Weather weather;
    private TextView currentWeather;
    private Button changeValue;
    private CheckBox checkTemp, checkHum, checkPress;
    private SharedPreferences spf;
    private boolean isCheckTemp, isCheckHum, isCheckPress;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("weather", weather);
        outState.putBoolean("isCheckTemp", checkTemp.isChecked());
        outState.putBoolean("isCheckHum", checkHum.isChecked());
        outState.putBoolean("isCheckPress", checkPress.isChecked());
        makeToastNLog("App Save Instance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weather = savedInstanceState.getParcelable("weather");
        checkTemp.setChecked(savedInstanceState.getBoolean("isCheckTemp"));
        checkHum.setChecked(savedInstanceState.getBoolean("isCheckHum"));
        checkPress.setChecked(savedInstanceState.getBoolean("isCheckPress"));
        makeToastNLog("App Restore Instance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather = new Weather(true);
        checkTemp = findViewById(R.id.check_temp);
        checkHum = findViewById(R.id.check_hum);
        checkPress = findViewById(R.id.check_pres);
        if (savedInstanceState != null) {
            checkTemp.setChecked(savedInstanceState.getBoolean("isCheckTemp"));
            checkHum.setChecked(savedInstanceState.getBoolean("isCheckHum"));
            checkPress.setChecked(savedInstanceState.getBoolean("isCheckPress"));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        loadText();
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
//        saveText();
        makeToastNLog("App Destroyed");
    }

    private void makeToastNLog(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("LifeCycle", message);
    }

    public void goToSecond(View view) {
        weather.changeAll();
        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtra("weatherObj",weather);
        intent.putExtra("isCheckTemp", checkTemp.isChecked());
        intent.putExtra("isCheckHum", checkHum.isChecked());
        intent.putExtra("isCheckPress", checkPress.isChecked());
        startActivity(intent);
    }

    private void saveText() {
        spf = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = spf.edit();
        ed.putString("SAVED_WEATHER", currentWeather.getText().toString());
        ed.apply();
        makeToastNLog("Saving Weather");
    }

    private void loadText() {
        spf = getPreferences(MODE_PRIVATE);
        String savedWeather = spf.getString("SAVED_WEATHER", "");
        currentWeather.setText(savedWeather);
        makeToastNLog("Loading Weather");
    }
}
