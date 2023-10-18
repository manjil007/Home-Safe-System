/**
 * Author: Raju Nayak
 */

package homesafe.controller;

import homesafe.event.HumidityEvent;
import homesafe.event.SafeEventHandler;
import homesafe.service.EventService;

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

    /**
     * The humidity sensor controller constructor
     * @param humidity the safe humidity
     */
    public HumiditySensorController(float humidity) {
        this.humidity = humidity;
    }

    /**
     * This set the humidity for display
     * @param humidity the safe humidity
     */
    public void setHumidity(float humidity) {
        this.humidity = humidity;
        EventService.getInstance().publishEvent(new HumidityEvent(HumidityEvent.UPDATE_EVENT, humidity));
    }

    @Override
    public void run() {
        // TODO
    }

    /**
     *
     * @param event the event
     */
    @Override
    public void handleEvent(HumidityEvent event) {
        setHumidity(event.getHumidity());
    }
}
