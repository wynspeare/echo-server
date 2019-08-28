package server;

import java.net.ServerSocket;

public interface SocketWrapper {
    void createAndListen(int port);
    void accept(ServerSocket serverSocket);
    String receiveData();
    void sendData(String data);
    void close();
}
