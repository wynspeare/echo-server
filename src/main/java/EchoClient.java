import java.io.*;
import java.net.Socket;

public class EchoClient {
    public void start() {
        try (
            Socket s = new Socket("127.0.0.1", 4242);

            PrintWriter writer = new PrintWriter(s.getOutputStream(), true);
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
        ) {
            String message;
            while ((message = getConsoleInput()) != null) {
                if (message == "CLOSE") {
                    s.close();
                    reader.close();
                    writer.close();
                } else {
//            while ((message = getConsoleInput()).length() > 0) {
                    writer.println(message);
                    String serverResponse = reader.readLine();
                    System.out.println("Echo from server: " + serverResponse);
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getConsoleInput() {
        String consoleInput;
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            consoleInput = consoleReader.readLine();
            return consoleInput;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient();
        client.start();
    }
}

