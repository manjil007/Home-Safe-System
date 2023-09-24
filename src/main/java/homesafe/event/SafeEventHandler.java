package homesafe.event;

import homesafe.event.AbstractSafeEvent;

/**
 * created by:
 * author: MichaelMillar
 */
public interface SafeEventHandler<T extends AbstractSafeEvent> {

    /**
     * Method that consumes an {@link AbstractSafeEvent} to perform some
     * additional function.
     *
     * @param event the event
     */
    void handleEvent(T event);

}
