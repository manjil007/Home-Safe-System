/**
 * Author: Raju Nayak
 */

package homesafe.controller;

import homesafe.event.HumidityEvent;
import homesafe.event.SafeEventHandler;

/**
 * Humidity sensor controller handle the humidity event inside safe.
 */
public class HumiditySensorController extends AbstractController implements SafeEventHandler<HumidityEvent> {

    private float humidity;
    private static HumiditySensorController instance;

    public static HumiditySensorController getInstance() {
        if (instance == null) {
            instance = new HumiditySensorController(0.0f);
        }
        return instance;
    }

    public HumiditySensorController(float humidity) {
        this.humidity = humidity;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    @Override
    public void run() {
        // TODO
    }

    @Override
    public void handleEvent(HumidityEvent event) {
        setHumidity(event.getHumidity());
    }
}
