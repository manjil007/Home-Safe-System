package homesafe.service;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.SafeEventHandler;
import homesafe.event.SafeEventPublisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * Service that will handle the publishing and subscribing of events
 * This service should be able to run on it's own thread.
 */
public class EventService implements Runnable {
    // TODO: Transition EventService into an async model

    private static EventService INSTANCE;

    private final ConcurrentHashMap<Class<? extends AbstractSafeEvent>,
            List<SafeEventHandler<? extends AbstractSafeEvent>>> subscribers;

    private final ConcurrentLinkedQueue<AbstractSafeEvent> eventQueue;

    private boolean run;

    private EventService() {
        subscribers = new ConcurrentHashMap<>();
        eventQueue = new ConcurrentLinkedQueue<>();
        run = true;
    }

    public static EventService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventService();
        }
        return INSTANCE;
    }

    private static Logger getLogger() {
        return Logger.getLogger(EventService.class.getName());
    }

    /**
     * Adds the {@link SafeEventHandler} to a list of event handlers that are
     * mapped by eventType.
     *
     * @param handler   the event consumer to add
     * @param eventType the event class to handle
     * @param <T>       the event type class
     */
    public <T extends AbstractSafeEvent>
    void subscribe(SafeEventHandler<T> handler, Class<T> eventType) {
        List<SafeEventHandler<? extends AbstractSafeEvent>> eventHandlers =
                subscribers.computeIfAbsent(eventType, (key -> Collections.synchronizedList(new ArrayList<>())));
        if (!eventHandlers.contains(handler)) {
            eventHandlers.add(handler);
        }
    }

    /**
     * Removes the {@link SafeEventHandler} from a list of event handlers that
     * are mapped by eventType.
     *
     * @param handler   the event consumer to remove
     * @param eventType the event class to handle
     * @param <T>       the event type class
     */
    public <T extends AbstractSafeEvent>
    void unsubscribe(SafeEventHandler<T> handler, Class<T> eventType) {
        subscribers.computeIfPresent(eventType, (key, value) -> {
            value.remove(handler);
            return value;
        });
    }

    /**
     * Method invoked by {@link SafeEventPublisher} classes that produce and
     * emit events to {@link SafeEventHandler} who are listening for the event
     * type.
     *
     * @param event the event to publish
     * @param <T>   the event type class
     */
    public <T extends AbstractSafeEvent>
    void publishEvent(T event) {
        eventQueue.add(event);
    }

    @Override
    public void run() {
        while (run) {
            AbstractSafeEvent event = eventQueue.poll();
            if (event == null) continue;

            List<SafeEventHandler<? extends AbstractSafeEvent>> eventHandlers =
                    subscribers.get(event.getClass());

            if (eventHandlers == null) continue;
            // This unchecked warning is ok, the events will always extend AbstractSafeEvent
            for (int i = eventHandlers.size() - 1; i >= 0; i--) {
                SafeEventHandler handler = eventHandlers.get(i);
                handler.handleEvent(event);
            }
        }
    }

    public void stop() {
        this.run = false;
    }
}
