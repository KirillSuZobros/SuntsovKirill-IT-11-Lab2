import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MapLoader{
   public static Set<City> loadMapFromFile(String filePath){
        Set<City> allCities = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()) continue;

                String[] parts = line.split(";");

                if(parts.length != 3) continue;

                String from_name = parts[0];
                String to_name = parts[1];
                int distance;

                try {
                    distance = Integer.parseInt(parts[2].trim());
                }

                catch (Exception e) {
                    continue;
                }
                if(distance < 0) continue;

                City fromCity = null;
                City toCity = null;

                for(City city_other : allCities){
                    if(city_other.getName().equals(from_name)) fromCity = city_other;
                    if(city_other.getName().equals(to_name)) toCity = city_other;
                }

                if(fromCity == null){
                    fromCity = new City(from_name);
                    allCities.add(fromCity);
                }

                if(toCity == null){
                    toCity = new City(to_name);
                    allCities.add(toCity);
                }

                fromCity.addOneWayRoad(toCity, distance);
            }
        }

        catch(IOException e){
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return allCities;
    }
}