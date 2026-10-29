
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City{
    private String name;
    private Map<City,Integer> cities_array = new HashMap<>();

    public City(String new_name){
        this.name = new_name;
    }

    public City(String new_name, Map<City,Integer> new_cities_array){
        this.name = new_name;
        this.cities_array = new_cities_array;
    }

    public String getName(){return name;}
    public Map<City,Integer> getCitiesList(){return cities_array;}

    public void addOneWayRoad(City city_other, int road){
        this.cities_array.put(city_other, road);
    }

    public void addTwoWayRoad(City city_other, int road){
        this.addOneWayRoad(city_other, road);
        city_other.addOneWayRoad(this, road);
    }

    @Override
    public String toString(){
        if(cities_array.isEmpty()) return "Из города " + name + " нет дорог";
        String result = "Город " + name + "- ";
        for(City city_other : cities_array.keySet()){
            int distance = cities_array.get(city_other);
            result = result + city_other.getName() + ":" + distance + " ";
        }
        return result.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(this.name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
}