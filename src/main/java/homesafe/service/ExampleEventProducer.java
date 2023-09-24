package homesafe.service;

import homesafe.event.ExampleSafeEvent;
import homesafe.event.SafeEventPublisher;

/**
 * created by:
 * author: MichaelMillar
 */
public class ExampleEventProducer implements SafeEventPublisher<ExampleSafeEvent> {

    private String name;

    public ExampleEventProducer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        ExampleSafeEvent event = new ExampleSafeEvent("Name changed", name);
        publishEvent(event);
    }

    @Override
    public void publishEvent(ExampleSafeEvent event) {
        EventService.getInstance().publishEvent(event);
    }
}
