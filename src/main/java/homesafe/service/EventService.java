package homesafe.service;

import homesafe.entity.AbstractSafeEvent;
import homesafe.entity.SafeEventHandler;
import homesafe.entity.SafeEventPublisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
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

    private final BlockingQueue<AbstractSafeEvent> eventQueue;

    private boolean run;

    private EventService() {
        subscribers = new ConcurrentHashMap<>();
        eventQueue = new LinkedBlockingQueue<>();
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
        try {
            eventQueue.put(event);
        } catch (InterruptedException e) {
            getLogger().log(Level.WARNING, "[EventService] Error adding event data to event queue. {0}",
                    e.getMessage().trim());
        }
    }

    @Override
    public void run() {
        while (run) {
            try {
                AbstractSafeEvent event = eventQueue.take();
                List<SafeEventHandler<? extends AbstractSafeEvent>> eventHandlers =
                        subscribers.get(event.getClass());
                if (eventHandlers != null) {
                    // This unchecked warning is ok, the events will always extend AbstractSafeEvent
                    for (SafeEventHandler handler : eventHandlers) {
                        handler.handleEvent(event);
                    }
                }
            } catch (InterruptedException e) {
                getLogger().log(Level.WARNING, "[EventService] Error getting data from event queue. {0}",
                        e.getMessage().trim());
            }
        }
    }
}
