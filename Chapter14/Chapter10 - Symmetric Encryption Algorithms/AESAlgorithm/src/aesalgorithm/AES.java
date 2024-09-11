package aesalgorithm;

import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.spec.*;
import java.nio.charset.*;

public class AES {
    private static final byte[] INITIALIZATION_VECTOR = {54, 34, 7, 3, 23, 78, 31, 68, 32, 40, 96, 43, 23, 54, 23, 76};
    private static final String AES_SECRET_KEY = "cryptoApress";
    private static final String AES_SALT = "apress";

    public static String encrypt(String plainMessage) {
        try {
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainMessage.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    public static String decrypt(String encryptedMessage) {
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }

    private static Cipher initCipher(int mode) throws Exception {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(INITIALIZATION_VECTOR);
        
        //the container for the secret key  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        
        //specification parameters (secret key, salt value, iterations, key length) 
        KeySpec keySpec = new PBEKeySpec(AES_SECRET_KEY.toCharArray(), AES_SALT.getBytes(), 65536, 256);
        
        //generate the secret key based on the parameters set above
        SecretKey tmp = keyFactory.generateSecret(keySpec);
        
        //align the secret key with the AES algorithm
        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        //set the algorithm (e.g., AES) and its mode together with its padding type
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKeySpec, ivParameterSpec);
        
        return cipher;
    }

    public static void main(String[] args) {
    	
    	//set the message that we want to encrypt
        String originalValue = "Welcome to Apress. Enjoy learning cryptography";
        
        //perform the encryption
        String encryptedValue = encrypt(originalValue);
        
        //perform the decryption
        String decryptedValue = decrypt(encryptedValue);

        //show some messages
        System.out.println("Plaintext used for encryption and decryption -> " + originalValue);
        System.out.println("The encryption is -> " + encryptedValue);
        System.out.println("The decryption is -> " + decryptedValue);
    }
}