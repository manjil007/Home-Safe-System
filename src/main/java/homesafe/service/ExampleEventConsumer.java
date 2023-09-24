package homesafe.service;

import homesafe.entity.ExampleSafeEvent;
import homesafe.entity.SafeEventHandler;

import java.util.logging.Logger;

import static java.util.logging.Level.INFO;

/**
 * created by:
 * author: MichaelMillar
 */
public class ExampleEventConsumer implements SafeEventHandler<ExampleSafeEvent> {

    public ExampleEventConsumer() {
        EventService.getInstance()
                .subscribe(this, ExampleSafeEvent.class);
    }

    private static Logger getLogger() {
        return Logger.getLogger(ExampleEventConsumer.class.getName());
    }

    @Override
    public void handleEvent(ExampleSafeEvent event) {
        getLogger().log(INFO, "{0}({1}): {2}",
                new Object[]{event.getClass().getName(),
                        event.getEventType(),
                        event.getExampleData()});
    }
}
