package homesafe.controller;

import homesafe.entity.VibrationLevel;
import homesafe.event.VibrationEvent;

/**
 * Vibration sensor controller that reads the vibration data from the sensor.
 */
public class VibrationSensorController extends AbstractController {

    private static VibrationSensorController instance;

    private VibrationLevel level;
    private VibrationLevel threshold;

    public static VibrationSensorController getInstance() {
        if (instance == null) {
            instance = new VibrationSensorController(VibrationLevel.HIGH);
        }
        return instance;
    }

    public VibrationSensorController(VibrationLevel threshold) {
        this.level = VibrationLevel.NONE;
        this.threshold = threshold;
    }

    public VibrationLevel getLevel() {
        return level;
    }

    public void setLevel(VibrationLevel level) {
        this.level = level;
        if (this.level.getValue() >= this.threshold.getValue()) {
            VibrationEvent event = new VibrationEvent("vibration_alert",
                    this.level, this.threshold);
            publishEvent(event);
        }
    }

    @Override
    public void run() {
        // TODO
    }
}
