package ru.neooffline.homework_a1l1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weather = new Weather();
        final TextView currentWeather = findViewById(R.id.weather);
        final Button changeValue = findViewById(R.id.measure_weather);
        currentWeather.setText(weather.getFullWeather());
        changeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weather.setFullWeather();
                currentWeather.setText(weather.getFullWeather());
            }
        });
    }
}
