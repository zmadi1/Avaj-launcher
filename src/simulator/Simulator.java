package simulator;


import Aircraft.*;
import Weather.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import  java.util.*;

import Weather.*;
import java.util.List;

import java.util.*;
import java.io.*;



public	class	Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<Flyable>();

    public static void main(String[] arg) throws InterruptedException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("scenario.txt"));

            String line = reader.readLine();

            if (line != null) {

                weatherTower = new WeatherTower();

                int simulations = Integer.parseInt(line.split(" ")[0]);

                if (simulations < 0) {
                    System.out.println("Invalid simulation count can't be negative  ");
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null) {

                  
                  Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    
                    if (flyable != null)
                        flyables.add(flyable);
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++) {
                    WriteFile.getWriteFile().writeToFile("\n"+"count" + i);
                    weatherTower.changeWeather();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file " + arg[0]);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading the file " + arg[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You did not specify the simulation file");
        } catch (NullPointerException e) {
            System.out.println("value is null");
        } catch (NumberFormatException e) {
            System.out.println("not a valid number entered in file");
        } finally {
            WriteFile.getWriteFile().close();
        }
    }
}


