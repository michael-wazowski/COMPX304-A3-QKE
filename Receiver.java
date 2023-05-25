import java.io.*;
import java.net.*;

public class Receiver extends Thread {
    ServerSocket ss;
    Socket client;

    public Receiver() {
        try {
            ss = new ServerSocket(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        if (client != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line = reader.readLine();
                reader.close();

                return line;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Client is null. Cannot receive message");
        }
        return "";
    }

    public void sendMessage(String message) {
        if (client != null) {
            try {
                PrintWriter writer = new PrintWriter(client.getOutputStream());
                writer.println(message);
            } catch (IOException e) {
                System.err.println("SEND: "+e.getMessage());
            }
        }
    }

    public void closeConn() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeServer() {
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConn() {
        try {
            client = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return ss.getLocalPort();
    }

    public void run() {
        this.acceptConn();
        return;
    }
}
