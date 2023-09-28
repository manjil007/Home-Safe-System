package homesafe.service;

import java.util.TimerTask;

/**
 * TimerTask class that performs the failed attempt process reset or resets the
 * lockout after a pre-determined time period if they aren't canceled first.
 */
public class AuthorizationTimer extends TimerTask {
    private final boolean failedAttemptTimer;

    public AuthorizationTimer(boolean failedAttemptTimer) {
        this.failedAttemptTimer = failedAttemptTimer;
    }

    @Override
    public void run() {
        if (failedAttemptTimer) {
            AuthenticationService.resetFailedAttempts();
        } else {
            AuthenticationService.resetLockout();
        }
    }
}
