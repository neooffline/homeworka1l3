package ru.neooffline.homework_a1l2;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;
import java.util.Random;

public class Weather implements ChangeValue,Parcelable{
    private int temperature;
    private int humidity;
    private String fullWeather;
//    private Context context = getAc

    public Weather() {
        fullWeather = "Нет данных по погоде";
    }

    private Weather(Parcel in) {
        temperature = in.readInt();
        humidity = in.readInt();
        fullWeather = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public void setFullWeather() {
        changeTemp();
        changeHumidity();
        this.fullWeather = String.format(Locale.ENGLISH,"Температура: %d С\u00b0\nВлажность: %d %%",
                this.temperature, this.humidity);
    }

    public String getFullWeather() {
        return fullWeather;
    }

    @Override
    public void changeTemp() {
        temperature = getRandomNumberInRange(-10, 26);
    }

    @Override
    public void changeHumidity() {
        humidity = getRandomNumberInRange(20, 90);
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
