import org.junit.Test;

import static org.junit.Assert.*;

public class EchoServerTest {
    @Test
    public void greetingReturnsAString() {
        EchoServer echoServer = new EchoServer();
        String expected = "HELLO... Hello... hello!";
        String actual = echoServer.greeting();

        assertEquals(expected, actual);
    }

}