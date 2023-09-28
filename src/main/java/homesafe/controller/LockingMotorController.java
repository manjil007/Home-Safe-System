package homesafe.controller;

import homesafe.event.AbstractSafeEvent;
import homesafe.event.LockingMotorEvent;
import homesafe.event.SafeEventHandler;

/**
 * Locking pin motor controller class that handles the unlock / locking of the safe
 * pins.
 */
public class LockingMotorController extends AbstractController implements SafeEventHandler<AbstractSafeEvent> {

    private static LockingMotorController instance;

    private static final float OPEN_POSITION = 0;
    private static final float CLOSED_POSITION = 1;

    private float motorPosition;
    private boolean jammed;

    public static LockingMotorController getInstance() {
        if (instance == null) {
            instance = new LockingMotorController(CLOSED_POSITION);
        }
        return instance;
    }

    private LockingMotorController(float motorPosition) {
        this.motorPosition = motorPosition;
        this.jammed = false;
    }

    public float getMotorPosition() {
        return motorPosition;
    }

    /**
     * Method that updates the position of the locking pin motor
     * @param motorPosition new position
     */
    public void setMotorPosition(float motorPosition) {
        this.motorPosition = motorPosition;
        if (motorPosition == OPEN_POSITION) {
            LockingMotorEvent event = new LockingMotorEvent(LockingMotorEvent.DOOR_UNLOCKED_EVENT,
                    motorPosition, jammed, false, true);
            publishEvent(event);
        } else if (motorPosition == CLOSED_POSITION) {
            LockingMotorEvent event = new LockingMotorEvent(LockingMotorEvent.DOOR_LOCKED_EVENT,
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
            LockingMotorEvent event = new LockingMotorEvent(LockingMotorEvent.DOOR_JAMMED_EVENT,
                    motorPosition, jammed, motorPosition == CLOSED_POSITION,
                    motorPosition == OPEN_POSITION);
            publishEvent(event);
        }
    }

    /**
     * Method to unlock the safe door
     */
    private void unlockDoor() {
        // TODO
    }


    /**
     * Method to lock the safe door
     */
    private void lockDoor() {
        // TODO
    }

    /**
     * Method that will progress the safe door by a pre-given sequence. This is
     * purely for simulation purposes, actual data would get received from the
     * internal motor position sensor
     * @param sequence sequence of motor positions
     */
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
