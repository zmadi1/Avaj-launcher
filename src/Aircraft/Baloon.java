package Aircraft;

import Weather.*;
import javax.imageio.spi.ImageReaderWriterSpi;
import java.io.FileWriter;
import java.util.HashMap;


public class  Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {

        String weather = this.weatherTower.getWeather(this.coordinates);
        HashMap<String, String> Baloon = new HashMap<>();

        Baloon.put("RAIN", "It's raining  we're going to crash we better land!.");

        Baloon.put("SUN", "I like the sun on my eyes, i better enjoy it");

        Baloon.put("FOG", "I better shine some beam light or someone will crash on us");

        Baloon.put("SNOW", "I enjoy snow when we land i am making a snow man!.");

        String tmp = "Baloon#" + super.name + "(" + this.id + "): ";

        switch (weather) {
            case "SUN":
                super.coordinates.setHeight(super.coordinates.getHeight() + 4);
                super.coordinates.setLatitude(super.coordinates.getLatitude() - 2);
                WriteFile.getWriteFile().writeToFile(tmp + Baloon.get("SUN"));
                break;

            case "RAIN":
                super.coordinates.setHeight(super.coordinates.getHeight() - 5);
                WriteFile.getWriteFile().writeToFile(tmp + Baloon.get("RAIN"));
                break;

            case "FOG":
                super.coordinates.setHeight(super.coordinates.getHeight() + 3);
                WriteFile.getWriteFile().writeToFile(tmp + Baloon.get("FOG"));
                break;
            case "SNOW":
                super.coordinates.setHeight(super.coordinates.getHeight() - 15);
                WriteFile.getWriteFile().writeToFile(tmp + Baloon.get("SNOW"));
                break;
        }
        if (this.coordinates.getHeight() > 100)
            this.coordinates.setLongitude(100);

        if (this.coordinates.getHeight() <= 0) {
            WriteFile.getWriteFile().writeToFile("Baloon#" + name + "(" + id + "): landing.");
            WriteFile.getWriteFile().writeToFile("Tower says: Baloon#" + super.name + "(" + super.id + ") unregister from weather tower.");
            weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        String write = "Tower Says: Baloon#" + super.name + "(" + super.id + ") registered to weather tower.";
        this.weatherTower = weatherTower;
        WriteFile.getWriteFile().writeToFile(write);

    }
}
