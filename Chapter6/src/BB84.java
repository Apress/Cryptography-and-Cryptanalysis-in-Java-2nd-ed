import java.util.Random;

public class BB84 {
    private static final Random random = new Random();

    // Method to generate a random binary string of a given length
    public static String generateRandomBits(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(random.nextBoolean() ? '1' : '0');
        }
        return result.toString();
    }

    // Method to simulate the key exchange
    public static String keyExchange(String bits, String aliceBases, String bobBases) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            // Keep the bit if Alice and Bob used the same base
            if (aliceBases.charAt(i) == bobBases.charAt(i)) {
                key.append(bits.charAt(i));
            }
        }
        return key.toString();
    }

    public static void main(String[] args) {
        int length = 100; // Length of the bit string
        String aliceBits = generateRandomBits(length);
        String aliceBases = generateRandomBits(length);
        String bobBases = generateRandomBits(length);

        // Alice sends bits, Bob measures them
        String finalKey = keyExchange(aliceBits, aliceBases, bobBases);

        System.out.println("Alice's original bits: " + aliceBits);
        System.out.println("Alice's bases:        " + aliceBases);
        System.out.println("Bob's bases:          " + bobBases);
        System.out.println("Generated Key:        " + finalKey);
    }
}
