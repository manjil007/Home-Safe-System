/**
 * Author: Raju Nayak
 * Date: 9/27/23
 */

package homesafe.controller;
import homesafe.event.TemperatureEvent;

public class TemperatureSensorController extends AbstractController{
    private float temperature;
    private float threshold;

    public TemperatureSensorController(float temperature, float threshold) {
        this.temperature = temperature;
        this.threshold = threshold;
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

    }
}
