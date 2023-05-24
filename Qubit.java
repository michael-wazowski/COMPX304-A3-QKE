class Qubit {
    private int value;
    private int polarization; // 0 is circular, 1 is linear

    public void newVal(int val, int pol) {
        //
    }

    public void set(int val, int pol) {
        //
    }

    public int measure(int pol) {
        if (pol == polarization) {
            return value;
        } else {
            polarization = pol;
            return 0; // still need to randomly determine 0 or 1 for value and return that.
        }
    }
}