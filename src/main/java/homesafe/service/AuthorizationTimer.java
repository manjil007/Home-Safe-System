package homesafe.service;

import java.util.TimerTask;

/**
 * created by:
 * author: MichaelMillar
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
