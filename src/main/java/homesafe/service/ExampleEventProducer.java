package homesafe.service;

import homesafe.entity.ExampleSafeEvent;
import homesafe.entity.SafeEventPublisher;

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
        EventService.publishEvent(event);
    }
}
