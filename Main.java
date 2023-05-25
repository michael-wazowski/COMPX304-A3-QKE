public class Main {
    static Receiver Rx;
    static Transmitter Tx;
    public static void main(String[] args) {
        Rx = new Receiver();
        System.out.println("Server made");
        Rx.run(); // to accept connection without pausing application
        System.out.println("Waiting for connection");
        Tx = new Transmitter("localhost", Rx.getPort());

        Tx.sendMessage("testing");
        System.out.println(Rx.receiveMessage());

        Tx.closeSocket();
        Rx.closeConn();
        Rx.closeServer();
    }
}
