package server;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    private SocketWrapper socketWrapper;
    private ServerSocket serverSocket;
    private boolean socketRunning = true;

    public EchoServer(SocketWrapper socket) {
        this.socketWrapper = socket;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Awaiting connection");
            while (true) {
                socketWrapper.accept(serverSocket);

                Runnable runnable = new MyRunnable(socketWrapper);
                Thread thread = new Thread(runnable);

                thread.start();

//                if (((MyRunnable) runnable).isOff) {
//                    System.out.println("Runnable is off");
//                    socketWrapper.close();
////                    thread.interrupt();
////                    socketRunning = false;
//                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                System.out.println("CLOSING ServerSOCKET");
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
        EchoServer server = new EchoServer(serverWrapper);
        server.start(4242);
    }
}


//package server;
//
//public class EchoServer {
//    private SocketWrapper socket;
//
//    public EchoServer(SocketWrapper socket) {
//        this.socket = socket;
//    }
//
//    public void start(int port) {
//        try {
//            socket.createAndListen(port);
//
//            String clientMessage;
//            while ((clientMessage = socket.receiveData()) != null) {
//                System.out.println("Client message received by server: " + clientMessage);
//                socket.sendData(clientMessage);
//            }
//        } finally {
//            socket.close();
//        }
//    }
//
//    public static void main(String[] args) {
//        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
//        EchoServer server = new EchoServer(serverWrapper);
//        server.start(4242);
//    }
//}
