package homesafe.event;

/**
 * created by:
 * author: MichaelMillar
 */
public class PowerEvent extends AbstractSafeEvent {

    private final float powerLevel;
    private final float threshold;

    public PowerEvent(String eventType, float powerLevel, float threshold) {
        super(eventType);
        this.powerLevel = powerLevel;
        this.threshold = threshold;
    }

    public float getPowerLevel() {
        return powerLevel;
    }

    public float getThreshold() {
        return threshold;
    }
}
