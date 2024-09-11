import java.util.Random;

public class Qubit {
    private double alpha; // Amplitude for state |0>
    private double beta;  // Amplitude for state |1>
    private static final Random random = new Random();
    // Constructor to initialize the qubit in superposition
    public Qubit(double alpha, double beta) {
        double norm = Math.sqrt(alpha * alpha + beta * beta);
        this.alpha = alpha / norm;
        this.beta = beta / norm;
    }
    // Method to "measure" the qubit, collapsing its superposition
    public int measure() {
        // Random number between 0 and 1
        double p = random.nextDouble(); 
        return p < alpha * alpha ? 0 : 1;
    }
    public static void main(String[] args) {
        // Example: Superposition close to even, alpha=1, beta=1
        Qubit qubit = new Qubit(1, 1);
        // Measure the qubit multiple times to see the probabilistic outcome
        int zeroCount = 0, oneCount = 0;
        int trials = 1000;
        for (int i = 0; i < trials; i++) {
            if (qubit.measure() == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        System.out.println("Measured '0': " + zeroCount + " times");
        System.out.println("Measured '1': " + oneCount + " times");
    }
}

