package homesafe.entity;

import homesafe.service.EventService;

/**
 * created by:
 * author: MichaelMillar
 */
public interface SafeEventPublisher<T extends AbstractSafeEvent> {

    /**
     * Method that publishes an {@link AbstractSafeEvent} to the {@link EventService}.
     *
     * @param event event to publish.
     */
    void publishEvent(T event);

}
