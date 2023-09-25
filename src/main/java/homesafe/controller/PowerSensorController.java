package homesafe.controller;

import homesafe.event.PowerEvent;

/**
 * created by:
 * author: MichaelMillar
 */
public class PowerSensorController extends AbstractController {

    private float powerLevel;
    private float threshold;

    public PowerSensorController(float powerLevel, float threshold) {
        this.powerLevel = powerLevel;
        this.threshold = threshold;
    }

    public float getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(float powerLevel) {
        this.powerLevel = powerLevel;
        if (this.powerLevel <= threshold) {
            PowerEvent event = new PowerEvent("low_power", powerLevel, threshold);
            publishEvent(event);
        }
    }

    @Override
    public void run() {
        // TODO
    }
}
