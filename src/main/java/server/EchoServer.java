package server;

import java.io.IOException;

public class EchoServer {
    public IServerSocketWrapper serverSocket;

    public EchoServer(IServerSocketWrapper serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws Exception {
        IServerSocketWrapper serverSocketWrapper = new ServerSocketWrapper(4242);
        EchoServer server = new EchoServer(serverSocketWrapper);
        server.serve();
    }

    public Runnable createRunnable() throws IOException {
        return new ServerRunnable(serverSocket.acceptConnection());
    }

    public void serve() throws IOException {
        while (!serverSocket.isClosed()) {
            Thread thread = new Thread(createRunnable());
            thread.start();
        }
    }
}
