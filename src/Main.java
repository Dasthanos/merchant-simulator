public class Main {
    public static void main(String[] args) {

        Trader trader = new Trader();

        City[] cities = {
                new City("Самарканд"),
                new City("Бухара"),
                new City("Ош"),
                new City("Тараз"),
                new City("Баласагун")
        };

        City destination = cities[new java.util.Random().nextInt(cities.length)];

        System.out.println("Пункт назначения: " + destination);
        System.out.println("Начинаем закупку товаров\n");

        trader.buyUntilFullOrNoMoney();

        System.out.println("Начинаем путь...\n");

        while (destination.getDistance() > 0) {
            Event.triggerRandomEvent(trader);
            trader.moveOneDay(destination);
            System.out.println();
        }

        System.out.println("Торговец прибыл в город " + destination.getName() + "!\n");

        trader.sellAllGoods();
        System.out.println("Путешествие завершено.");
    }
}