import java.util.Random;

public class City {
    private String name;
    private int distance; // расстояние до города

    public City(String name) {
        this.name = name;
        this.distance =50 + new Random().nextInt(51);
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return name + " (дистанция: " + distance + " лиг)";
    }

    public void reduceDistance(int value) {
        distance = Math.max(0, distance - value);
    }

}
