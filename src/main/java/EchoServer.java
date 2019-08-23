public class EchoServer {
    private SocketWrapper socket;

    public EchoServer(SocketWrapper socket) {
        this.socket = socket;
    }

    public void start(int port) {
        socket.createAndListen(port);
        String clientMessage = socket.receiveData();
        socket.sendData(clientMessage);
//        socket.close();
    }

    public static void main(String[] args) {
        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
        EchoServer server = new EchoServer(serverWrapper);
        server.start(4242);
    }
}
