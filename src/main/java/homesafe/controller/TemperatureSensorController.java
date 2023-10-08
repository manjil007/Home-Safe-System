/**
 * Author: Raju Nayak
 */

package homesafe.controller;
import homesafe.event.TemperatureEvent;

public class TemperatureSensorController extends AbstractController{
    private float temperature;
    private float threshold;
    private static TemperatureSensorController instance;

    public TemperatureSensorController(float temperature, float threshold) {
        this.temperature = temperature;
        this.threshold = threshold;
    }
    public static TemperatureSensorController getInstance() {
        if (instance == null) {
            instance = new TemperatureSensorController(0.0f, 0.0f);
        }
        return instance;
    }
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        if (this.temperature > threshold) {
            TemperatureEvent event = new TemperatureEvent("High temperature", temperature);
            publishEvent(event);
        }
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public void run() {
        // TODO
    }
}
