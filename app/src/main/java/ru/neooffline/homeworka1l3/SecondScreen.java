package ru.neooffline.homeworka1l3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

public class SecondScreen extends AppCompatActivity {

    static final String TOKEN = "weatherObj";
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        Intent i = this.getIntent();

        try {
            weather = i.getParcelableExtra(TOKEN);
        } catch (Throwable t) {
            Log.e("Error", t.getMessage());
            weather = new Weather(true);
        }
        //weather.changeAll();
        TextView tempValue = findViewById(R.id.ss_temp_value);
        TextView humValue = findViewById(R.id.ss_hum_value);
        TextView pressValue = findViewById(R.id.ss_press_value);
        hideTextViewes(findViewById(R.id.temp_value),
                findViewById(R.id.ss_temp_value),
                i.getBooleanExtra("isCheckTemp",false));
//
//        assert weather != null;
        tempValue.setText(String.format(Locale.ENGLISH, "%d C", weather.getTemperature()));
        humValue.setText(String.format(Locale.ENGLISH, "%d %%", weather.getHumidity()));
        pressValue.setText(String.format(Locale.ENGLISH, "%d hPa", weather.getPressure()));
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void hideTextViewes(View tv, View tvVal, boolean isHiden) {
        if (isHiden) {
            tv.setVisibility(View.VISIBLE);
            tvVal.setVisibility(View.VISIBLE);
        } else {
            tv.setVisibility(View.INVISIBLE);
            tvVal.setVisibility(View.INVISIBLE);
        }
    }
}
