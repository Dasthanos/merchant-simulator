import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trader {
    private int liftingCapacity;
    private int speed;
    private int balance;
    private List<Goods> goods = new ArrayList<>();;

    public Trader() {
        Random rnd = new Random();
        this.liftingCapacity = 20 + rnd.nextInt(21);
        this.speed = 1 + rnd.nextInt(5);
        this.balance = 100 + rnd.nextInt(201);
    }

    public int getSpeed() {
        return speed;
    }

    public void moveOneDay(City city) {
        int distance = city.getDistance();
        int moved = Math.min(speed, distance);
        city.reduceDistance(moved);
        System.out.println("Проехали " + moved + " лиг. Осталось: " + city.getDistance());
    }

    public boolean buyGoods(Goods g) {
        double totalWeight = goods.stream().mapToDouble(Goods::getWeight).sum();
        if(balance >= g.getPrice() && totalWeight + g.getWeight() <= liftingCapacity) {
            goods.add(g);
            balance -= g.getPrice();
            System.out.println("Куплен товар: " + g);
            return true;
        }
        return false;
    }

    public static Goods generateRandomGoods() {
        Random rnd = new Random();
        ProductType type =
                ProductType.values()[rnd.nextInt(ProductType.values().length)];
        ProductQuality quality = ProductQuality.NORMAL;
        double weight = 1 + rnd.nextDouble() * 5;
        double price = 10 + rnd.nextDouble() * 41;
        return new Goods(weight, type, quality, price);
    }

    public void skipDay(){
        System.out.println("В этот день ничего не произошло");
    }
    public void decreaseSpeed(int value) {
        int t = Math.max(speed-value, 0);
        speed = t;
        System.out.printf("Из-за дождя скорость снижена до %d лиг в день %n", t);
    }
    public void increaseSpeed(int value){
        int t = Math.min(speed+value, 5);
        speed = t;
        System.out.printf("Благодаря ровной дороге скорость увеличилась до %d %n", t);
    }

    public void spoilRandomProduct() {
        if (goods.isEmpty()) return;
        Random rnd = new Random();
        int index = rnd.nextInt(goods.size());
        Goods g = goods.get(index);
        g.spoil();
        System.out.println("Один из товаров испортился!");
    }
    public void loseBestProduct() {
        if (goods.isEmpty()) return;
        Goods best = goods.get(0);
        for (Goods good : goods){
            double value = good.getPrice() * good.getQuality().getCoefficient();
            double bestValue = best.getPrice() * best.getQuality().getCoefficient();
            if(value > bestValue)
                best = good;
        }
        goods.remove(best);
        System.out.println("Разбойники забрали лучший товар: " + best.getType());
    }

    public void buyUntilFullOrNoMoney() {
        while (true) {
            Goods g = generateRandomGoods();
            if (!buyGoods(g)) {
                System.out.println("Закупка завершена");
                break;
            }
        }
    }

    public void sellAllGoods() {
        double total = 0;
        for (Goods g : goods) {
            double value = g.getPrice() * g.getQuality().getCoefficient();
            total += value;
        }
        goods.clear();
        balance += total;
        System.out.println("Товары проданы на сумму: " + total);
        System.out.println("Итоговый баланс: " + balance);
    }
}
