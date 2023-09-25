package homesafe.controller;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.SafeEventPublisher;
import homesafe.service.EventService;

/**
 * created by:
 * author: MichaelMillar
 */
public abstract class AbstractController implements Runnable, SafeEventPublisher<AbstractSafeEvent> {

    @Override
    public void publishEvent(AbstractSafeEvent event) {
        EventService.getInstance().publishEvent(event);
    }

}
