/**
 * Author: Raju Nayak
 * Date: 9/30/23
 */

package homesafe.controller;
import homesafe.event.DoorEvent;

/**
 * Controller class to handle the light functionality inside safe.
 */
public class LightController extends AbstractController{

    private boolean isLightOn;
    private final DoorEvent doorEvent;

    /**
     * Constructor of the light controller.
     * @param doorEvent a dependency of the door event
     */
    public LightController(DoorEvent doorEvent) {
        this.doorEvent = doorEvent;
    }

    /**
     * Method to handle the light functionality based on door event.
     */
    public void lightHandler() {
        boolean isDoorOpen = doorEvent.isDoorOpen();

        if (isDoorOpen && !isLightOn) {
            isLightOn = true;
        } else if (!isDoorOpen && isLightOn) {
            isLightOn = false;
        }
    }

    @Override
    public void run() {
        lightHandler();
    }
}
