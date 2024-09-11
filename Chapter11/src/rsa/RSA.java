package rsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
	
	private BigInteger prime_p, prime_q, val_n, phi_n, val_e, val_d;
	private final BigInteger one = BigInteger.ONE;
	private final int maximumLength = 1024;
	private SecureRandom random;
	
	public void KeyGeneration() {
		random = new SecureRandom();
		prime_p = BigInteger.probablePrime(maximumLength, random);
		prime_q = BigInteger.probablePrime(maximumLength, random);
		val_n = prime_p.multiply(prime_q);
		phi_n = prime_p.subtract(one).multiply(prime_q.subtract(one));

		val_e = BigInteger.probablePrime(maximumLength, random);
		while (phi_n.gcd(val_e).compareTo(one) != 0 || val_e.compareTo(phi_n) >= 0) {
			val_e = BigInteger.probablePrime(maximumLength, random);
		}
		val_d = val_e.modInverse(phi_n);		
	} 
	
    public byte[] Encryption(byte[] plainMessage, BigInteger e, BigInteger n) {
    	BigInteger messageBigInt = new BigInteger(plainMessage);
    	BigInteger encryptedMessage = messageBigInt.modPow(e, n);
        return encryptedMessage.toByteArray();
    } 
    
    public byte[] Decryption(byte[] encryptedMessage, BigInteger d, BigInteger n) {
    	BigInteger messageBigInt = new BigInteger(encryptedMessage);
    	BigInteger decryptedMessage = messageBigInt.modPow(d, n);
        return decryptedMessage.toByteArray();
    }   
 
    public static void main (String [] arguments) {
        RSA rsa = new RSA();
        rsa.KeyGeneration();        
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Type the plain message: ");
            String plainMessage = reader.readLine();
            
            System.out.println("\nEncrypting the message... ");
            byte[] encryptedMessage = rsa.Encryption(plainMessage.getBytes(), rsa.val_e, rsa.val_n);
            System.out.println("Encrypted message [bytes]: " + byteToString(encryptedMessage));
            System.out.println("Encrypted message [text]: " + new String(encryptedMessage, java.nio.charset.StandardCharsets.UTF_8));
            
            System.out.println("\nDecrypting the message... ");
            byte[] decryptedMessage = rsa.Decryption(encryptedMessage, rsa.val_d, rsa.val_n);
            System.out.println("Decrypted message [bytes]: " + byteToString(decryptedMessage));
            System.out.println("Decrypted message [text]: " + new String(decryptedMessage, java.nio.charset.StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
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