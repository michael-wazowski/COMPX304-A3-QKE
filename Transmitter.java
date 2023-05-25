import java.io.*;
import java.net.*;

public class Transmitter {

    Socket sock;
    
    public Transmitter(String host, int port) {
        try {
            sock = new Socket(InetAddress.getByName(host), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line = reader.readLine();
            reader.close();

            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void sendMessage(String message) {
        if (sock != null) {
            try {
                PrintWriter writer = new PrintWriter(sock.getOutputStream());
                writer.println(message);
            } catch (IOException e) {
                System.err.println("SEND: "+e.getMessage());
            }
        }
    }

    public void closeSocket() {
        if (sock != null) {
            try {
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
