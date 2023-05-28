import java.io.Serializable;
import java.util.*;

class Qubit implements Serializable{
    private Random rand = new Random(); // to randomly gen values
    private int value; // bit value, 0 for off 1 for on 
    private int polarization; // 0 is circular, 1 is linear

    // init qubit fields
    public Qubit(int bVal, int polVal) {
        value = bVal;
        polarization = polVal;
    }

    public void set(int val, int pol) { // sets value and polarisation to submitted values
        value = val;
        polarization = pol;
    }

    public int measure(int pol) { // measures polarisation against another.
        if (pol != polarization) { // if they dont match
            set(rand.nextInt(2), pol); // get new bit value, and update polarisation with submitted one
        }
        return value; // return value
    }
}