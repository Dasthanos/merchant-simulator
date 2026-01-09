import java.util.*;
import java.util.function.Consumer;

public class Event {

    private static final Random rnd = new Random();

    private static final Map<EventType, Consumer<Trader>> EVENTS =
            new HashMap<>();

    static {
        EVENTS.put(EventType.NORMAL_DAY, trader ->
                trader.skipDay()
        );

        EVENTS.put(EventType.RAIN, trader -> {
            trader.decreaseSpeed(2);
            if (rnd.nextDouble() < 0.3) {
                trader.spoilRandomProduct();
            }
        });

        EVENTS.put(EventType.GOOD_ROAD, trader ->
                trader.increaseSpeed(2)
        );

        EVENTS.put(EventType.BROKEN_WHEEL, trader ->
                System.out.println("Сломалось колесо. День потерян.")
        );

        EVENTS.put(EventType.RIVER, trader ->
                System.out.println("Искали брод через реку. День потерян.")
        );

        EVENTS.put(EventType.MET_LOCAL, trader -> {
            int bonus = 3 + rnd.nextInt(4);
            System.out.println("Встретили местного, срезали путь на " + bonus + " лиг");
            // позже добавим ускорение движения
        });

        EVENTS.put(EventType.BANDITS, trader ->
                trader.loseBestProduct()
        );

        EVENTS.put(EventType.INN, trader ->
                System.out.println("Зашли в придорожный трактир.")
        );

        EVENTS.put(EventType.GOODS_SPOILED, trader ->
                trader.spoilRandomProduct()
        );
    }

    public static void triggerRandomEvent(Trader trader) {
        EventType[] values = EventType.values();
        EventType randomEvent = values[rnd.nextInt(values.length)];

        System.out.println("Событие: " + randomEvent);
        EVENTS.get(randomEvent).accept(trader);
    }
}