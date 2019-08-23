import java.io.*;
import java.net.*;

public class ServerSocketWrapper implements SocketWrapper {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    public void createAndListen(int port) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public String receiveData() {
        try {
            String clientMessage = input.readLine();
            System.out.println("Client message received by server: " + clientMessage);
            return clientMessage;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    return null;
    }
    public void sendData(String data) {
        output.println(data);
    }
    public void close() {
        try {
            output.close();
            input.close();
            socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}