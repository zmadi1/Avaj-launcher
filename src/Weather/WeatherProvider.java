package Weather;

import Aircraft.Coordinates;

import java.util.Random;

public class WeatherProvider {

    private static WeatherProvider weatherProvider;
    private static String[]  weather = {"RAIN","FOG","SUN","SNOW"};


    private  WeatherProvider(){

    }

    public static WeatherProvider getProvider(){
        weatherProvider = new WeatherProvider();
        return weatherProvider;

    }



    public String getCurrentWeather(Coordinates coordinates){
        Random random = new Random();

        //int rand = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude() % 4;
          //int rand =random.nextInt(weather.length);
        return weather[(coordinates.getHeight() + coordinates.getLongitude() + coordinates.getLongitude()) % 4];
    }
}
