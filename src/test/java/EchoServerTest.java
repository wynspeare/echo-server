
import org.junit.Test;
import server.*;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EchoServerTest {

  @Test
  public void testReceivedDataIsEchoedBackInSentData() {

    BufferedReader input = new BufferedReader(
            new StringReader("echo\n"));
    PrintWriter output = new PrintWriter(new StringWriter(), true);
    ServerSocketWrapperSpy serverSocketWrapperSpy = new ServerSocketWrapperSpy(input, output);

    EchoServer echoServer = new EchoServer(serverSocketWrapperSpy);

    try {
      ServerRunnable runnable = (ServerRunnable) echoServer.createRunnable();
      runnable.run();
      SocketWrapperSpy socketWrapperSpy = ((SocketWrapperSpy) runnable.socketWrapper);

      assertTrue(serverSocketWrapperSpy.wasAcceptConnectionCalled());
      assertEquals("ECHO", socketWrapperSpy.getSentData());
      assertTrue(socketWrapperSpy.wasCloseCalled());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

