package homesafe.event;

/**
 * Temperature specific event Data Transfer Object
 */
public class TemperatureEvent extends AbstractSafeEvent {

    private final float temperature;

    public TemperatureEvent(String eventType, float temperature) {
        super(eventType);
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }
}
