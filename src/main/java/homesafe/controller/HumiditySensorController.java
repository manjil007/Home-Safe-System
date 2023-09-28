/**
 * Author: Raju Nayak
 * Date: 9/27/23
 */

package homesafe.controller;

public class HumiditySensorController extends AbstractController{

    private float humidity;

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

    }
}
