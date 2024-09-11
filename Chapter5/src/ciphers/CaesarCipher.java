package ciphers;

import java.util.Scanner;

public class CaesarCipher {

    public static String Encrypt(String text, int positions) {
        StringBuilder toEncrypt = new StringBuilder();
        // Format the text to be encrypted
        for (char c : text.toCharArray()) {
            // Remove space characters
            if (c != ' ') {
                // If the character is lowercase, make it uppercase
                toEncrypt.append(Character.toUpperCase(c));
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < toEncrypt.length(); i++) {
            // Shift the current letter of the message with given positions to the right
            char shiftedLetter = (char) (toEncrypt.charAt(i) + positions);
            // If the ASCII code exceeds 'Z', then bring it back in the interval A..Z
            if (shiftedLetter > 'Z') {
                shiftedLetter = (char) (shiftedLetter - 26);
            }
            result.append(shiftedLetter);
        }
        return result.toString();
    }

    public static String Decrypt(String text, int positions) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            // Shift the current letter of the message with given positions to the left
            char shiftedLetter = (char) (c - positions);
            // If the ASCII code goes below 'A', then bring it back in the interval A..Z
            if (shiftedLetter < 'A') {
                shiftedLetter = (char) (shiftedLetter + 26);
            }
            result.append(shiftedLetter);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("CAESAR CIPHER\n");

        try (Scanner sc = new Scanner(System.in)) {
            // Reading the input: plain message and the secret key
            System.out.print("Type the message: ");
            String message = sc.nextLine();
            System.out.print("Type the key: ");
            int key = sc.nextInt();

            // Encrypting the plain message
            System.out.println("\nEncrypting...");
            String encryptedMessage = Encrypt(message, key);
            System.out.println("The encrypted message is: " + encryptedMessage);

            // Decrypting the encrypted message
            System.out.println("\nDecrypting...");
            String recoveredMessage = Decrypt(encryptedMessage, key);
            System.out.println("The decrypted message is: " + recoveredMessage);
        }
    }
}