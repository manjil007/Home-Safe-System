package homesafe.event;

/**
 * Humidity specific safe event Data Transfer Object
 */
public class HumidityEvent extends AbstractSafeEvent {

    private final float humidity;

    public HumidityEvent(String eventType, float humidity) {
        super(eventType);
        this.humidity = humidity;
    }

    public float getHumidity() {
        return humidity;
    }

}
