package homesafe.entity;

import static homesafe.entity.State.SLEEP;

/**
 * created by:
 * author: MichaelMillar
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

    public void transitionState(State state) {
        // TODO: Do we need more logic for state transitions?
        this.state = state;
    }
}
