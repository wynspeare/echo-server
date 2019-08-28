package server;

public class MyRunnable implements Runnable {
    private SocketWrapper socketWrapper;
    public boolean isOff = false;

    public static void main(String args[]) throws Exception {
    }

    public MyRunnable(SocketWrapper socketWrapper) {
        this.socketWrapper = socketWrapper;
    }

    public void run() {
        while(!isOff) {
            try {
                String clientMessage;
                while ((clientMessage = socketWrapper.receiveData()) != null) {

                    if (clientMessage.toLowerCase().trim().equals("close")) {
                        System.out.println("is off is now true");
//                        socketWrapper.close();
                        isOff = true;
                    } else {
                        System.out.println("Client message received by server: " + clientMessage);
                        socketWrapper.sendData(clientMessage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("CLOSING SOCKET");
        socketWrapper.close();
    }
}



//package server;
//
//
//import java.io.IOException;
//
//public class MyRunnable implements Runnable {
//    private SocketWrapper socketWrapper;
//    public String hello = "Hello";
//    public boolean isOff = false;
//
//    public static void main(String args[]) throws Exception {
//    }
//
//    public MyRunnable(SocketWrapper socketWrapper) {
//        this.socketWrapper = socketWrapper;
//    }
//
//    public void run() {
//
//        try {
//            String clientMessage;
//            while ((clientMessage = socketWrapper.receiveData()) != null) {
//
//                if (clientMessage.toLowerCase().trim().equals("close")) {
////                    socketWrapper.close();
//                    isOff = true;
//                }
//                System.out.println("Client message received by server: " + clientMessage);
//                socketWrapper.sendData(clientMessage);
//            }
//        } finally {
//            System.out.println("MAYBE CLOSED?");
//            socketWrapper.close();
//        }
//    }
//}
//
