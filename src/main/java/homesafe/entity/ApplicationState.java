package homesafe.entity;

import static homesafe.entity.State.SLEEP;

/**
 * Singleton class that keeps track of the current safe state
 */
public class ApplicationState {

    private static ApplicationState instance;
    private State state;

    public static ApplicationState getInstance() {
        if (instance == null) {
            instance = new ApplicationState(SLEEP);
        }
        return instance;
    }

    private ApplicationState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    /**
     * This method will transition the safe's state and perform any required
     * business logic when transitioning between states.
     * @param state new state
     */
    public void transitionState(State state) {
        // TODO: Do we need more logic for state transitions?
        this.state = state;
    }
}
