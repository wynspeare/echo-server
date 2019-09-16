package client;

public class EchoClient {
    private IClientWrapper client;

    public EchoClient(IClientWrapper client) {
        this.client = client;
    }

    public void start(int port) {
        client.create(port);
        String messageToSend;
        while ((messageToSend = client.getUserInput()) != null) {
            if (messageToSend.toLowerCase().trim().equals("close")) {
                System.out.println("Closing Client");
                client.sendData(messageToSend);
                break;
            } else {
                client.sendData(messageToSend);
                client.receiveData();
            }
        }
        client.close();
    }

    public static void main(String[] args) {
        EchoClientWrapper clientWrapper = new EchoClientWrapper();
        EchoClient client = new EchoClient(clientWrapper);
        client.start(4242);
    }
}