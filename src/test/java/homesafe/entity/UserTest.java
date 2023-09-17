package homesafe.entity;

import homesafe.UnitTest;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

/**
 * created by:
 * author: MichaelMillar
 */
public class UserTest {

    @UnitTest
    public void testCreateUser() {
        String name = "Mike";
        String pin = "123456";
        String hashedPin = String.format("%s:%s", name, pin);

        User user = new User(name);
        user.setHashedPIN(hashedPin);
        user.setAdmin(true);
        user.setExpiration(LocalDateTime.of(2023, 9, 16, 11, 15));

        Assertions.assertNotNull(user.getUsername());
        Assertions.assertNotNull(user.getHashedPIN());
        Assertions.assertTrue(user.isAdmin());
        Assertions.assertNotNull(user.getExpiration());
    }

}
