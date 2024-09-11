package elgamal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class ElGamal {
    
    private BigInteger prime_q, val_x, val_g, val_h;
    private byte[] c1, c2;
    private final BigInteger one = BigInteger.ONE;
    private final BigInteger two = new BigInteger("2");
    private final int maximumLength = 1024;
    private SecureRandom random;
    
    public void KeyGeneration() {
        random = new SecureRandom();
        prime_q = BigInteger.probablePrime(maximumLength, random);        
        do {
            val_x = new BigInteger(maximumLength, random);
        } while (val_x.compareTo(prime_q.subtract(one)) >= 0);
        
        BigInteger p2 = prime_q.subtract(one).divide(two);
        
        // take a generator g of the group
        do {
            val_g = new BigInteger(maximumLength, random).mod(prime_q);
        } while (val_g.modPow(p2, prime_q).compareTo(one) == 0);
        
        val_h = val_g.modPow(val_x, prime_q);       
    } 
    
    public void Encryption(byte[] plainMessage) {
        BigInteger y, s;
        do {
            y = new BigInteger(maximumLength, random);
        } while (y.compareTo(prime_q) >= 0);
        
        s = val_h.modPow(y, prime_q);
        c1 = val_g.modPow(y, prime_q).toByteArray();
        c2 = new BigInteger(plainMessage).multiply(s).mod(prime_q).toByteArray();
        
        System.out.println("Encrypted message [bytes]: " + byteToString(c1) + byteToString(c2));
        System.out.println("Encrypted message [text]: " + new String(c1, StandardCharsets.UTF_8) + new String(c2, StandardCharsets.UTF_8));      
    } 
    
    public void Decryption() {
        BigInteger s = new BigInteger(c1).modPow(val_x, prime_q);
        BigInteger invS = s.modInverse(prime_q);
        BigInteger m = invS.multiply(new BigInteger(c2)).mod(prime_q);
        
        System.out.println("\nDecrypted message [bytes]: " + byteToString(m.toByteArray()));
        System.out.println("Decrypted message [text]: " + new String(m.toByteArray(), StandardCharsets.UTF_8));              
    }   

    public static void main(String[] args) throws IOException {
        ElGamal elGamal = new ElGamal();
        elGamal.KeyGeneration();        
        
        try (BufferedReader d = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Type the plain message: ");
            String plainMessage = d.readLine();
            
            System.out.println("\nEncrypting the message... ");
            elGamal.Encryption(plainMessage.getBytes(StandardCharsets.UTF_8));
            elGamal.Decryption();       
        }
    }
    
    private static String byteToString(byte[] byteArray) {
        StringBuilder recoveredString = new StringBuilder();
        for (byte byteVal : byteArray) {
            recoveredString.append(Byte.toString(byteVal));
        }
        return recoveredString.toString();
    } 
}