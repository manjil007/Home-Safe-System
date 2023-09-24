package homesafe.entity;

/**
 * Locking PIN Motor specific event Data Transfer Object
 */
public class LockingMotorEvent extends AbstractSafeEvent {

    private final float motorPosition;
    private final boolean motorJammed;
    private final boolean fullyExtended;
    private final boolean fullyRetracted;

    public LockingMotorEvent(String eventType, float motorPosition, boolean motorJammed,
                             boolean fullyExtended, boolean fullyRetracted) {
        super(eventType);
        this.motorPosition = motorPosition;
        this.motorJammed = motorJammed;
        this.fullyExtended = fullyExtended;
        this.fullyRetracted = fullyRetracted;
    }

    public float getMotorPosition() {
        return motorPosition;
    }

    public boolean isMotorJammed() {
        return motorJammed;
    }

    public boolean isFullyExtended() {
        return fullyExtended;
    }

    public boolean isFullyRetracted() {
        return fullyRetracted;
    }
}
