import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public String greeting() {
        return "HELLO... Hello... hello!";
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true) {
                Socket socket = serverSocket.accept();
                PrintWriter sendResponse = new PrintWriter(socket.getOutputStream());

                String clientMessage;
                while ((clientMessage = getClientData(socket)) != null) {
                        System.out.println("Client MESSAGE: " + clientMessage);
//                        sendResponse.println(clientMessage);
                    }

//                PrintWriter writer = new PrintWriter(socket.getOutputStream());
//                String text_input = "Hello World";
//                writer.println(text_input);
//                writer.close();
//                System.out.println(text_input);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getClientData(Socket socket) {
        String clientMessage;
        try {
            BufferedReader getInput = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            clientMessage = getInput.readLine();
            return clientMessage;

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return "";
    }

    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start();
    }
}
