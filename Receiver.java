import java.net.*;
import java.util.*;
import java.io.*;

class Receiver {
    public static void main(String[] args) {
        try {

            int streamLen = 16;




            // Create a server socket, using port 0 to automatically bind it to an available port
            ServerSocket ss = new ServerSocket(0);
            System.out.println("Listening on port "+String.valueOf(ss.getLocalPort())); // display bound port
            Socket s=null; // create null socket for client connection
            s = ss.accept(); // Accept a client connection

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            // Qubit data = msg.geQubit();

            Random rand = new Random();
            ArrayList<Qubit> bits = new ArrayList<Qubit>();

            ArrayList<Integer> pVals = new ArrayList<Integer>();
            ArrayList<Integer> bVals = new ArrayList<Integer>();

            for (int i=0; i<streamLen; i++) {
                Qubit msg = (Qubit)ois.readObject();
                pVals.add(rand.nextInt(2));
                bVals.add(msg.measure(pVals.get(i)));
            }


            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

            for (int i=0; i<streamLen; i++) {
                oos.writeUTF("");
            }







            // close client and server sockets
            s.close();
            ss.close();
        } catch (Exception e) {
            System.err.println(e); // print any error to console/terminal
        }
    }
}
