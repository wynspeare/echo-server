package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable {
    private static SocketWrapper socketWrapper;
    private static ServerSocket serverSocket;
    private boolean socketRunning = true;

    public EchoServer(SocketWrapper serverWrapper) {
        this.socketWrapper = serverWrapper;
    }

//    public void create (int port) {
//        try {
//            serverSocket = new ServerSocket(port);
//            System.out.println("Awaiting connection with instance serversocket");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void run() {
        try {
            String clientMessage;
            while ((clientMessage = socketWrapper.receiveData()) != null) {

                if (clientMessage.toLowerCase().trim().equals("close")) {
                    System.out.println("Closing Socket and exiting while loop");
                    break;
                } else {
                    System.out.println("Client message received by server: " + clientMessage);
                    socketWrapper.sendData(clientMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing at finally statement");
            socketWrapper.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
//        EchoServer server = new EchoServer(serverWrapper);

        ServerSocket serverSocket = new ServerSocket(4242);
        System.out.println("Awaiting connection (non-instance serverSocket)");
//        server.create(4242);

        while (true) {
            ServerSocketWrapper serverWrapper = new ServerSocketWrapper();

//            serverWrapper.accept(serverSocket);
            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection");

            serverWrapper.accept(socket);

            new Thread(new EchoServer(serverWrapper)).start();
        }
    }
}

//
//package server;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//
//public class EchoServer {
//    private SocketWrapper serverWrapper;
//    private ServerSocket serverSocket;
//    private boolean socketRunning = true;
//
//    public EchoServer(SocketWrapper serverWrapper) {
//        this.serverWrapper = serverWrapper;
//    }
//
//    public void start(int port) {
//        try {
//            serverSocket = new ServerSocket(port);
//            System.out.println("Awaiting connection");
//            while (true) {
//                serverWrapper.accept(serverSocket);
//
//                Runnable runnable = new MyRunnable(serverWrapper);
//                Thread thread = new Thread(runnable);
//
//                thread.start();
//
////                if (((MyRunnable) runnable).isOff) {
////                    System.out.println("Runnable is off");
////                    socketWrapper.close();
//////                    thread.interrupt();
//////                    socketRunning = false;
////                }
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                System.out.println("CLOSING ServerSOCKET");
//                serverSocket.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        ServerSocketWrapper serverWrapper = new ServerSocketWrapper();
//        EchoServer server = new EchoServer(serverWrapper);
//        server.start(4242);
//    }
//}