package ru.neooffline.homeworka1l3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondScreen extends AppCompatActivity {
    static final String weatherObj = "weatherObj";
    private TextView tempValue, humValue, pressValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        tempValue.findViewById(R.id.temp_value);
        tempValue.setText("шазам");
        humValue.findViewById(R.id.hum_value);
        humValue.setText("оглы");
        pressValue.findViewById(R.id.press_value);
        pressValue.setText("лукойл");
//        dumpIntent(i);
        Weather weather = this.getIntent().getExtras().getParcelable(weatherObj);
        weather.changePres();
//        tempValue.setText(String.format("%d C", weather.getTemperature()).toString());
//        humValue.setText(String.format("%s %%", weather.getHumidity()));
//        pressValue.setText(String.format("%s hPa", weather.getPressure()));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
