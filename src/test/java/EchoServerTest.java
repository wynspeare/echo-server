import org.junit.Test;
import server.EchoServer;
import server.ServerSocketWrapperSpy;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoServerTest {

    private static final int PORT = 4242;

    @Test
    public void testReceivedDataIsEchoedBackInSentData() {

        BufferedReader input = new BufferedReader(
                new StringReader("echo\n"));
        PrintWriter output = new PrintWriter(new StringWriter(), true);

        ServerSocketWrapperSpy socketWrapper =
                new ServerSocketWrapperSpy (input, output);

        EchoServer echoServer = new EchoServer(socketWrapper);
        echoServer.start(PORT);

        assertTrue(socketWrapper.wasCreateAndListenCalled());
        assertEquals("ECHO", socketWrapper.getSentData());
        assertTrue(socketWrapper.wasCloseCalled());
    }
}