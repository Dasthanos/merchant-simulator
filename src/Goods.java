import java.util.Random;

public class Goods {
    private double weight;
    private ProductType type;
    private ProductQuality quality;
    private double price;

    public double getWeight() {
        return weight;
    }

    public ProductQuality getQuality() {
        return quality;
    }

    public ProductType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public Goods(double weight, ProductType type, ProductQuality quality, double price) {
        this.weight = weight;
        this.type = type;
        this.quality = quality;
        this.price = price;
    }

    public void spoil(){
        quality = quality.downgrade();
    }

    @Override
    public String toString() {
        return "Goods{" +
                "weight=" + weight +
                ", type=" + type +
                ", quality=" + quality +
                ", price=" + price +
                '}';
    }
}
