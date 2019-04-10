package ru.neooffline.homework_a1l1;
import java.util.Random;

public class Weather implements ChangeValue {
    private int temperature;
    private int humidity;
    private String fullWeather;

    public Weather() {
        fullWeather = "Нет данных по погоде";
    }

    public void setFullWeather() {
        changeTemp();
        changeHumidity();
        this.fullWeather = String.format("Температура: %d С\u00b0\nВлажность: %d %%",
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

}
