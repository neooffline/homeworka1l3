package ru.neooffline.homeworka1l3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondScreen extends AppCompatActivity {
    private TextView tempValue, humValue, pressValue;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        weather = savedInstanceState.getParcelable("weather");
        tempValue.findViewById(R.id.temp_value);
        humValue.findViewById(R.id.hum_value);
        pressValue.findViewById(R.id.press_value);
        tempValue.setVisibility(View.INVISIBLE);
        tempValue.setText(String.format("%d C", weather.getTemperature()));
        tempValue.setText(String.format("%d %%", weather.getHumidity()));
        tempValue.setText(String.format("%d hPa", weather.getPressure()));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
