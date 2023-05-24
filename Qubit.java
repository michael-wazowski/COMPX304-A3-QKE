import java.util.*;

class Qubit {
    private Random rand = new Random();
    private int value;
    private int polarization; // 0 is circular, 1 is linear

    public Qubit() {
        value = rand.nextInt(2);
        polarization = rand.nextInt(2);
    }

    public void set(int val, int pol) {
        value = val;
        polarization = pol;
    }

    public int measure(int pol) {
        if (pol != polarization) {
            set(rand.nextInt(2), pol);
        }
        return value;
    }
}