import java.io.*;
import java.net.Socket;

public class EchoClientWrapperSpy implements ClientWrapper {

    private PrintWriter writer;
    private BufferedReader reader;
    private boolean createCalled = false;
    private String receivedData;
    private boolean closeCalled = false;

    public EchoClientWrapperSpy(
            BufferedReader reader, PrintWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void create(int port) {
        createCalled = true;
    }

    public String getData() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading mock socket reader");
        }
        return "";
    }

    public String receiveData() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading mock socket input");
        }
        return "";
    }

    public void sendData(String data) {
        writer.println(data.toUpperCase());
        receivedData = data.toUpperCase();
    }

    public void close() {
        closeCalled = true;
    }
    public boolean wasCreateCalled() {
        return createCalled;
    }
    public String getReceivedData() {
        return receivedData;
    }
    public boolean wasCloseCalled() {
        return closeCalled;
    }
}