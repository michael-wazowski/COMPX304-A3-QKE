import java.util.*;

class Qubit {
    private Random rand = new Random(); // to randomly gen values
    private int value; // bit value, 0 for off 1 for on 
    private int polarization; // 0 is circular, 1 is linear

    // init qubit fields
    public Qubit() {
        value = rand.nextInt(2);
        polarization = rand.nextInt(2);
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