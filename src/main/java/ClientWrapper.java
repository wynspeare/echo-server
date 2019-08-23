public interface ClientWrapper {
    void create(int port);
    String getData(); //inject console?
    void sendData(String data);
    String receiveData();
    void close();
}
