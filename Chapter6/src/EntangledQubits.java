import java.util.Random;

public class EntangledQubits {
    private boolean stateA;  // State of qubit A
    private boolean stateB;  // State of qubit B

    // Flag to check if measurement has occurred
    private boolean measured = false; 
    
    private static final Random random = new Random();

    // Constructor for entangled qubits
    public EntangledQubits() {
        // Initialize the entangled pair
        stateA = random.nextBoolean();

	  // Ensures they are opposite for a specific Bell state |Ψ+> = (|01⟩ + |10⟩)/√2
  stateB = !stateA;
    }

    // Method to measure qubit A
    public int measureA() {
        if (!measured) {
            measured = true;
        }
        return stateA ? 1 : 0;
    }

    // Method to measure qubit B
    public int measureB() {
        if (!measured) {
            measured = true;

		// Ensure state B is the opposite of A when measured first
            stateB = !stateA;
        }
        return stateB ? 1 : 0;
    }

    public static void main(String[] args) {
        EntangledQubits entangledQubits = new EntangledQubits();
        
        int measurementA = entangledQubits.measureA();
        int measurementB = entangledQubits.measureB();

        System.out.println("Measurement of Qubit A: " + measurementA);
        System.out.println("Measurement of Qubit B: " + measurementB);
    }
}
