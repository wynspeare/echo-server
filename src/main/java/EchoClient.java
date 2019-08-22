import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public void start() {
        try (
            Socket s = new Socket("127.0.0.1", 4242);

            PrintWriter writer = new PrintWriter(s.getOutputStream()); //create object to send data to server
            InputStreamReader streamReader = new InputStreamReader(s.getInputStream()); //get response from server
            BufferedReader reader = new BufferedReader(streamReader); //get response from server

            ) {
                String message;
                while ((message = getConsoleInput()) != null) {
//            while ((message = getConsoleInput()).length() > 0) {
                    writer.println(message);
                    writer.close();


//                    String serverResponse = reader.readLine(); //create the string response from server
//                    System.out.println("Response from server: " + serverResponse); //print to console
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
//            consoleReader.close();
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
