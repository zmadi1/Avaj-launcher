package Aircraft;

import Weather.WeatherProvider;
import Weather.WeatherTower;
import Weather.WriteFile;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates){
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);

        HashMap<String, String> JetPlane = new HashMap<>();

        JetPlane.put("RAIN","If we were flying above this clouds we won't experience this rain!.");

        JetPlane.put("SUN","The sun is hurting my eyes up here!.");

        JetPlane.put("FOG", "I am losing visibility from this fog.");

        JetPlane.put("SNOW","It has started snowing look out the window");

        String tmp = "Jetplane#" +  super.name + "("+ this.id + ")";

        switch (weather){

            case  "SUN":
                super.coordinates.setHeight(super.coordinates.getHeight() + 2);
                super.coordinates.setLatitude(super.coordinates.getLatitude() + 10);
                WriteFile.getWriteFile().writeToFile(tmp + JetPlane.get("SUN"));
                break;
            case  "RAIN":
                super.coordinates.setLatitude(super.coordinates.getLatitude() + 5);
                WriteFile.getWriteFile().writeToFile(tmp + JetPlane.get("RAIN"));
                break;
            case "FOG" :
                super.coordinates.setLatitude(super.coordinates.getLatitude() - 1);
                WriteFile.getWriteFile().writeToFile(tmp + JetPlane.get("FOG"));
                break;
            case "SNOW" :
                super.coordinates.setHeight(super.coordinates.getHeight() - 7);
                WriteFile.getWriteFile().writeToFile(tmp + JetPlane.get("SNOW"));
        }
        if (this.coordinates.getHeight() > 100)
            this.coordinates.setLongitude(100);

        if(this.coordinates.getHeight() <= 0){
            WriteFile.getWriteFile().writeToFile("JetPlane#" + name + "(" + id + "): landing.");

            WriteFile.getWriteFile().writeToFile("Tower says: Jetplane#" + this.name + "(" + this.id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        String write = "Tower Says: Jetplane#" + this.name + "("+super.id + ") registered to the weatherTower.\n";
        this.weatherTower = weatherTower;
        WriteFile.getWriteFile().writeToFile(write);

    }
}

