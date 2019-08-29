import org.junit.Test;
import server.EchoServer;
import server.ServerSocketWrapperSpy;

import java.io.*;
import java.net.ServerSocket;

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

        try {
            ServerSocket serverSocket = new ServerSocket(4242);
            EchoServer echoServer = new EchoServer(serverSocket);

            Runnable runnable = echoServer.createRunnable(socketWrapper);
            runnable.run();

            assertTrue(socketWrapper.wasCreateAndListenCalled());
            assertEquals("ECHO", socketWrapper.getSentData());
            assertTrue(socketWrapper.wasCloseCalled());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
