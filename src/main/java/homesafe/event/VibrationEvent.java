package homesafe.event;

import homesafe.entity.VibrationLevel;

/**
 * created by:
 * author: MichaelMillar
 */
public class VibrationEvent extends AbstractSafeEvent {

    private final VibrationLevel level;
    private final VibrationLevel threshold;

    public VibrationEvent(String eventType, VibrationLevel level, VibrationLevel threshold) {
        super(eventType);
        this.level = level;
        this.threshold = threshold;
    }

    public VibrationLevel getLevel() {
        return level;
    }

    public VibrationLevel getThreshold() {
        return threshold;
    }
}
