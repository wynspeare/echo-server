import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class EchoClientTest {

    private static final int PORT = 4242;

    @Test
    public void clientTestDataIsSendAndEchoedBack() {

        BufferedReader reader = new BufferedReader(
                new StringReader("echo\n"));
        PrintWriter writer = new PrintWriter(new StringWriter(), true);

        EchoClientWrapperSpy clientWrapper =
                new EchoClientWrapperSpy (reader, writer);

        EchoClient echoClient = new EchoClient(clientWrapper);
        echoClient.start(PORT);

        assertTrue(clientWrapper.wasCreateCalled());
        assertEquals("ECHO", clientWrapper.getReceivedData());
        assertTrue(clientWrapper.wasCloseCalled());
    }

}