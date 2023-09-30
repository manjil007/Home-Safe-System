/**
 * Author: Raju Nayak
 */

package homesafe.controller;

import homesafe.event.ButtonEvent;
import homesafe.event.DoorEvent;
import homesafe.event.SafeEventHandler;
import homesafe.service.EventService;

/**
 * Controller class to handle the light functionality inside safe.
 */
public class LightController extends AbstractController implements SafeEventHandler<DoorEvent> {

    private static LightController instance;
    private boolean isLightOn;

    private LightController() {
        isLightOn = false;
        EventService.getInstance().subscribe(this, DoorEvent.class);
    }

    public static LightController getInstance() {
        if (instance == null) {
            instance = new LightController();
        }
        return instance;
    }

    /**
     * Method to handle the light functionality based on door event.
     */
    public void lightHandler(DoorEvent doorEvent) {
        boolean isDoorOpen = doorEvent.isDoorOpen();

        if (isDoorOpen && !isLightOn) {
            isLightOn = true;
        } else if (!isDoorOpen && isLightOn) {
            isLightOn = false;
        }
    }

    @Override
    public void run() {
        // TODO
    }

    @Override
    public void handleEvent(DoorEvent event) {
        lightHandler(event);
    }
}
