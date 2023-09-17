package homesafe.service;

import homesafe.UnitTest;
import org.junit.jupiter.api.Assertions;

/**
 * created by:
 * author: MichaelMillar
 */
public class AuthenticationServiceTest {

    @UnitTest
    public void testHashPin() {
        // TODO(mike): Update test once actual hash method implemented
        String name = "Mike";
        String pin = "123456";
        String expected = String.format("%s:%s", name, pin);
        String actual = AuthenticationService.hashPIN(name, pin);

        Assertions.assertEquals(expected, actual);
    }

}
