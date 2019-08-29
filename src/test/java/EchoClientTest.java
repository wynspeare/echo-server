import client.EchoClient;
import client.EchoClientWrapperSpy;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoClientTest {

    private static final int PORT = 4242;

    @Test
    public void clientTestDataIsSentAndEchoedBack() {

        BufferedReader reader = new BufferedReader(
                new StringReader("echo\n"));
        PrintWriter writer = new PrintWriter(new StringWriter(), true);

        EchoClientWrapperSpy clientWrapper =
                new EchoClientWrapperSpy (reader, writer);

        EchoClient echoClient = new EchoClient(clientWrapper);
        echoClient.start(PORT);

        assertTrue(clientWrapper.wasCreateCalled());
        assertEquals("ECHO", clientWrapper.getReceivedData());
    }

    @Test
    public void clientCanBeClosed() {

        BufferedReader reader = new BufferedReader(
                new StringReader("echo\nclose\n"));
        PrintWriter writer = new PrintWriter(new StringWriter(), true);

        EchoClientWrapperSpy clientWrapper =
                new EchoClientWrapperSpy (reader, writer);

        EchoClient echoClient = new EchoClient(clientWrapper);
        echoClient.start(PORT);

        assertTrue(clientWrapper.wasCreateCalled());
        assertTrue(clientWrapper.wasCloseCalled());
    }

}