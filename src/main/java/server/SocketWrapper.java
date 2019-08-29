package server;

import java.net.ServerSocket;
import java.net.Socket;

public interface SocketWrapper {
//    void createAndListen(int port);
    void accept(Socket Socket);
    String receiveData();
    void sendData(String data);
    void close();
}
