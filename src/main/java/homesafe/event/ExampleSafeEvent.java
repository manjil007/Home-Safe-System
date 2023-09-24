package homesafe.entity;

/**
 * created by:
 * author: MichaelMillar
 */
public class ExampleSafeEvent extends AbstractSafeEvent {

    private final String exampleData;

    public ExampleSafeEvent(String eventType, String exampleData) {
        super(eventType);
        this.exampleData = exampleData;
    }

    public String getExampleData() {
        return exampleData;
    }
}
