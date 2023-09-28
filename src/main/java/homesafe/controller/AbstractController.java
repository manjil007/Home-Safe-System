package homesafe.controller;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.SafeEventPublisher;
import homesafe.service.EventService;

/**
 * created by:
 * author: MichaelMillar
 */
public abstract class AbstractController implements Runnable, SafeEventPublisher<AbstractSafeEvent> {

    protected boolean run = true;

    @Override
    public void publishEvent(AbstractSafeEvent event) {
        EventService.getInstance().publishEvent(event);
    }

    public void stop() {
        this.run = false;
    }

}
