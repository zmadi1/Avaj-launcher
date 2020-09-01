package Aircraft;

import Weather.*;

import java.util.HashMap;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    Helicopter(String name, Coordinates coordinates){
        super(name, coordinates);

    }

    @Override
    public void updateConditions() {

        String weather = this.weatherTower.getWeather(this.coordinates);
        HashMap<String, String> Helicopter = new HashMap<>();

        Helicopter.put("RAIN","It's raining  we're going to crash we better land!.");

        Helicopter.put("SUN","I like the sun on but the noise from the rotar is too much");

        Helicopter.put("FOG", "I better shine some beam light or someone will crash on us");

        Helicopter.put("SNOW","We're going down quick and its snowing outside!.");

        String tmp = "Helicopter#" + super.name + "("+this.id +"): ";

        switch (weather) {

            case "SUN" :
                super.coordinates.setHeight(super.coordinates.getHeight() + 2);
                super.coordinates.setLongitude(super.coordinates.getLongitude() + 10);
                WriteFile.getWriteFile().writeToFile(tmp + Helicopter.get("SUN"));
                break;

            case "RAIN" :
                super.coordinates.setLongitude(super.coordinates.getLongitude() + 5);
                WriteFile.getWriteFile().writeToFile(tmp + Helicopter.get("RAIN"));
                break;

            case  "FOG" :
                super.coordinates.setLongitude(super.coordinates.getLongitude() + 1);
                WriteFile.getWriteFile().writeToFile(tmp + Helicopter.get("FOG"));
                break;

            case  "SNOW" :
                super.coordinates.setHeight(super.coordinates.getHeight() - 12);
                WriteFile.getWriteFile().writeToFile(tmp + Helicopter.get("SNOW"));
                break;
        }
        if (this.coordinates.getHeight() > 100)
            this.coordinates.setLongitude(100);


        if(this.coordinates.getHeight() <= 0){
            WriteFile.getWriteFile().writeToFile("Helicopter#" + name + "(" + id + "): landing.");
            WriteFile.getWriteFile().writeToFile("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }

    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        WriteFile.getWriteFile().writeToFile("Tower Says: Helicopter#" + super.name + "("+ super.id +") registered to weather tower.");

    }
}
