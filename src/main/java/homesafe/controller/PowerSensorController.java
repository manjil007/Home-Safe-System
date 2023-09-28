package homesafe.controller;

import homesafe.event.PowerEvent;

/**
 * Power sensor controller that updates the power levels from the sensor readings.
 */
public class PowerSensorController extends AbstractController {

    private static PowerSensorController instance;

    private float powerLevel;
    private final float threshold;

    public static PowerSensorController getInstance() {
        if (instance == null) {
            instance = new PowerSensorController(1, 0.15f);
        }
        return instance;
    }

    private PowerSensorController(float powerLevel, float threshold) {
        this.powerLevel = powerLevel;
        this.threshold = threshold;
    }

    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(float powerLevel) {
        this.powerLevel = powerLevel;
        if (this.powerLevel <= threshold) {
            PowerEvent event = new PowerEvent(PowerEvent.LOW_POWER_EVENT, powerLevel, threshold);
            publishEvent(event);
        }
    }

    @Override
    public void run() {
        // TODO
    }
}
