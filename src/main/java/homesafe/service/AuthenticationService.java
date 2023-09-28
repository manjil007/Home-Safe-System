package homesafe.service;

import homesafe.entity.ApplicationState;
import homesafe.entity.State;
import homesafe.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class will be responsible for obtaining and authentication user data.
 */
public class AuthenticationService {
    private static User currentUser;
    private static boolean authorized = false;
    private static int concurrentFailedAttempts = 0;

    private static final Timer timer = new Timer("Auth Timer");
    private static AuthorizationTimer failedAttemptedTimer;
    private static AuthorizationTimer lockoutTimer;

    // 60 second consecutive fail timer (for demo purposes)
    private static final long FAILED_ATTEMPT_TIME = 60000L;
    // 15 second lockout timer (for demo purposes)
    private static final long LOGOUT_TIME = 15000L;

    private static Logger getLogger() {
        return Logger.getLogger(AuthenticationService.class.getName());
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isAuthorized() {
        return authorized;
    }

    /**
     * Utility method that takes a {@link User}'s name and PIN and calculates
     * a hash string from them.
     *
     * @param name the user's name
     * @param pin  the user's PIN
     * @return a hashed string of the user's name and PIN
     */
    public static String hashPIN(String name, String pin) {
        String combo = String.format("%s%s", name, pin);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(combo.getBytes());
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16)
                        .substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            getLogger().log(Level.WARNING, "[AuthService]: Failed to hash user ({0}). {1}",
                    new Object[]{name, e.getMessage().trim()});
        }
        return null;
    }

    public static boolean verifyHash(User user, String hash) {
        return user.getHashedPIN().equals(hash);
    }

    public static boolean authorizeUser(String name, String pin) {
        currentUser = new User(name);

        // Check current state and determine if authorization is allowed
        State currentState = ApplicationState.getInstance().getState();
        if (currentState == State.LOCKOUT) {
            LogService.log(String.format("%s attempted to authorize during lockout.",
                    name));
            return false;
        }

        // Search user, deny if no matching user by name, and hash doesn't match
        Optional<User> oUser = UserService.getInstance().getUserByName(name);
        // check if user exists in DB
        if (oUser.isEmpty()) {
            updateFailedAttempts(concurrentFailedAttempts++);
            LogService.log(String.format("%s attempted to authorize: no matching user found.",
                    name));
            return false;
        }
        // compare hash
        User user = oUser.get();
        String hash = hashPIN(name, pin);
        boolean verify = verifyHash(user, hash);
        if (!verify) {
            updateFailedAttempts(concurrentFailedAttempts++);
            LogService.log(String.format("%s attempted to authorize: hash doesn't match.",
                    name));
            return false;
        }
        // user verified, allow access
        authorizeUser(user);
        return true;
    }

    private static void updateFailedAttempts(int val) {
        concurrentFailedAttempts = val;
        if (val == 0) {
            failedAttemptedTimer.cancel();
        } else if (val >= 3) { // 3 failed attempts before timer reset and we lock the system out
            performLockout();
            concurrentFailedAttempts = 0;
        } else {
            failedAttemptedTimer.cancel();
            failedAttemptedTimer = new AuthorizationTimer(true);
            timer.schedule(failedAttemptedTimer, FAILED_ATTEMPT_TIME);
        }
    }

    public static void resetFailedAttempts() {
        updateFailedAttempts(0);
    }

    private static void performLockout() {
        LogService.log("Safe Lockout System Activated due to excessive failed access attempts.");
        ApplicationState.getInstance().transitionState(State.LOCKOUT);
        failedAttemptedTimer.cancel();
        lockoutTimer = new AuthorizationTimer(false);
        timer.schedule(lockoutTimer, LOGOUT_TIME);
    }

    public static void resetLockout() {
        ApplicationState.getInstance().transitionState(State.SLEEP);
        resetFailedAttempts();
    }

    private static void authorizeUser(User user) {
        currentUser = user;
        authorized = true;
        updateFailedAttempts(0);
        LogService.log(String.format("%s was granted access to safe.",
                user.getUsername()));
    }

    public static void deAuthorizeUser() {
        currentUser = null;
        authorized = false;
    }

}
