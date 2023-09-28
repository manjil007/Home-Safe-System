package homesafe.controller;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.SafeEventPublisher;
import homesafe.service.EventService;

/**
 * Abstract controller class which forms the baseline of all controllers and
 * sensors.
 */
public abstract class AbstractController implements Runnable, SafeEventPublisher<AbstractSafeEvent> {

    protected boolean run = true;

    @Override
    public void publishEvent(AbstractSafeEvent event) {
        EventService.getInstance().publishEvent(event);
    }

    /**
     * Method to stop the running controller on it's thread.
     */
    public void stop() {
        this.run = false;
    }

}
