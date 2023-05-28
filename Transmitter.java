import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

class Transmitter {
    public static void main(String[] args) {
        Random rand = new Random();
        // confirm there are 2 arguments (ip to connect to and the port)
        if (args.length != 1) {
            System.out.println("Usage: Transmitter <port>"); // inform user on usage
            return; // end program
        }

        // initilise IA for socket creation later
        InetAddress ia;
        try {
            // attempt to get the IA from the submitted ip/host
            ia = InetAddress.getByName("0.0.0.0");
        } catch(UnknownHostException e) { // in case the host/ip is invalid
            System.err.println("Unknown name for ip"); // inform user of error 
            return; // end program
        }

        // initialise socket for client connection to server
        try {
            int streamLen = 256;

            Socket sock = new Socket(ia, Integer.valueOf(args[0])); // create socket connection to server
            ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
            
            ArrayList<Integer> pVals = new ArrayList<Integer>();
            ArrayList<Integer> bVals = new ArrayList<Integer>();

            for (int i=0;i<streamLen;i++) {
                pVals.add(rand.nextInt(2));
                bVals.add(rand.nextInt(2));
                oos.writeUTF(bVals.get(i).toString()+","+pVals.get(i).toString());
                oos.flush();
            }

            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());

            String key = "";

            for (int i=0; i<streamLen; i++) {
                String[] polAndBit = ois.readUTF().split(",");
                if (polAndBit[1].equals(pVals.get(i).toString())) {
                    if (polAndBit[0].equals(bVals.get(i).toString())) {
                        key += polAndBit[0];
                    }
                }
            }

            /* QKE Finished -> Message exchange from here */

            // Message to encrypt
            String message = "01101011"; // more or less arbitrary, as it is assumed to be already converted to binary
   
            // match key to message len for encryption
            while (key.length() != message.length()) {
                if (key.length() > message.length()) {
                    key = key.substring(0, message.length());
                } else {
                    key += key;
                }
            }

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

            oos.writeUTF(output);
            oos.flush();


            sock.close();  // close socket
        } catch (IOException e) {
            System.err.println("IO Exception"+e); // print IO error
            return;  // end program
        }
    }
}
