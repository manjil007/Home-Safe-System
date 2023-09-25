package homesafe.controller;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.LockingMotorEvent;
import homesafe.event.SafeEventHandler;

/**
 * created by:
 * author: MichaelMillar
 */
public class LockingMotorController extends AbstractController implements SafeEventHandler<AbstractSafeEvent> {

    private static final float OPEN_POSITION = 0;
    private static final float CLOSED_POSITION = 1;

    private float motorPosition;
    private boolean jammed;

    public LockingMotorController(float motorPosition) {
        this.motorPosition = motorPosition;
        this.jammed = false;
    }

    public float getMotorPosition() {
        return motorPosition;
    }

    public void setMotorPosition(float motorPosition) {
        this.motorPosition = motorPosition;
        if (motorPosition == OPEN_POSITION) {
            LockingMotorEvent event = new LockingMotorEvent("door_unlocked",
                    motorPosition, jammed, false, true);
            publishEvent(event);
        } else if (motorPosition == CLOSED_POSITION) {
            LockingMotorEvent event = new LockingMotorEvent("door_locked",
                    motorPosition, jammed, true, false);
            publishEvent(event);
        }
    }

    public boolean isJammed() {
        return jammed;
    }

    public void setJammed(boolean jammed) {
        this.jammed = jammed;
        if (jammed) {
            LockingMotorEvent event = new LockingMotorEvent("motor_jammed",
                    motorPosition, jammed, motorPosition == CLOSED_POSITION,
                    motorPosition == OPEN_POSITION);
            publishEvent(event);
        }
    }

    private void unlockDoor() {
        // TODO
    }

    private void lockDoor() {
        // TODO
    }

    private void progressDoor(float[] sequence) {
        // TODO: this will simulate the door opening and closing through
        //       a pre-determined sequence of motor positions.
        //       It will use this data to determine when the door
        //       is in the open and closed position, as well as when
        //       it is jammed.
    }

    @Override
    public void run() {
        // TODO
    }

    @Override
    public void handleEvent(AbstractSafeEvent event) {
        // TODO
    }
}
