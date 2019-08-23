import java.io.*;

public class ServerSocketWrapperSpy implements SocketWrapper {
    private BufferedReader input;
    private PrintWriter output;
    private boolean createAndListenCalled = false;
    private String receivedData;
    private String sentData;
    private boolean closeCalled = false;

    public ServerSocketWrapperSpy(
            BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public void createAndListen(int port) {
        createAndListenCalled = true;
    }
    public String receiveData() {
        try {
            receivedData = input.readLine();
            return receivedData;
        } catch (IOException e) {
            System.err.println("Error reading mock socket input");
        }
        return "";
    }

    public void sendData(String data) {
        output.println(data.toUpperCase());
        sentData = data.toUpperCase();
    }

    public void close() {
        closeCalled = true;
    }
    public boolean wasCreateAndListenCalled() {
        return createAndListenCalled;
    }
    public String getSentData() {
        return sentData;
    }
    public String getReceivedData() {
        return receivedData;
    }
    public boolean wasCloseCalled() {
        return closeCalled;
    }
}