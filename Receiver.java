import java.net.*;
import java.util.*;
import java.io.*;

class Receiver {
    public static void main(String[] args) {
        try {

            int streamLen = 256;




            // Create a server socket, using port 0 to automatically bind it to an available port
            ServerSocket ss = new ServerSocket(0);
            System.out.println("Listening on port "+String.valueOf(ss.getLocalPort())); // display bound port
            Socket s=null; // create null socket for client connection
            s = ss.accept(); // Accept a client connection

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            Random rand = new Random();
            // ArrayList<Qubit> bits = new ArrayList<Qubit>();

            ArrayList<Integer> pVals = new ArrayList<Integer>();
            ArrayList<Integer> bVals = new ArrayList<Integer>();

            String key = "";

            for (int i=0; i<streamLen; i++) {
                String[] polAndBit = ois.readUTF().split(",");

                Qubit msg = new Qubit(Integer.parseInt(polAndBit[0]), Integer.parseInt(polAndBit[1]));
                pVals.add(rand.nextInt(2));
                bVals.add(msg.measure(pVals.get(i)));

                if (polAndBit[1].equals(pVals.get(i).toString())) {
                    if (polAndBit[0].equals(bVals.get(i).toString())) {
                        key += polAndBit[0];
                    }
                }
            }

            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            for (int i=0; i<streamLen; i++) {
                oos.writeUTF(bVals.get(i).toString()+","+pVals.get(i).toString());
                oos.flush();
            }

            /* QKE Done -> Message exchange from here */

            String message = ois.readUTF();
            String output = "";
            for (int i=0; i<message.length(); i++) {
                if (message.charAt(i) == '1' && key.charAt(i) == '1') {
                    output += "0";
                } else if (message.charAt(i) == '1' || key.charAt(i) == '1') {
                    output += "1";
                } else {
                    output += "0";
                }
            }

            System.out.println(output);


            // close client and server sockets
            s.close();
            ss.close();
        } catch (Exception e) {
            System.err.println(e); // print any error to console/terminal
        }
    }
}
