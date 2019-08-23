import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;

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