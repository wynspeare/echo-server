package client;

public class EchoClient {
    private ClientWrapper client;

    public EchoClient(ClientWrapper client) {
        this.client = client;
    }

    public void start(int port) {
        client.create(port);
        String messageToSend;
        while ((messageToSend = client.getUserInput()) != null) {
            client.sendData(messageToSend);
            client.receiveData();
        }
        client.close();
    }

    public static void main(String[] args) {
        EchoClientWrapper clientWrapper = new EchoClientWrapper();
        EchoClient client = new EchoClient(clientWrapper);
        client.start(4242);
    }
}
