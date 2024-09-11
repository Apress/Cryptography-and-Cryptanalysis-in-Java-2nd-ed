package ciphers;

import java.util.Scanner;

public class Vigenere {
    static String ExtendKey(String message, String initialKey) {
        StringBuilder extendedKey = new StringBuilder(initialKey);
        int messageLength = message.length();
        int keyLength = initialKey.length();

        for (int i = 0; extendedKey.length() < messageLength; i++) {
            extendedKey.append(initialKey.charAt(i % keyLength));
        }
        return extendedKey.toString();
    }

    static String Encrypt(String text, String extendedKey) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            int charIndex = (text.charAt(i) - 'A' + extendedKey.charAt(i) - 'A') % 26 + 'A';
            result.append((char) charIndex);
        }
        return result.toString();
    }

    static String Decrypt(String text, String extendedKey) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            int charIndex = (text.charAt(i) - extendedKey.charAt(i) + 26) % 26 + 'A';
            result.append((char) charIndex);
        }
        return result.toString();
    }

    static String ProcessText(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c != ' ') {
                result.append(Character.toUpperCase(c));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("VIGENERE CIPHER\n");

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Type the message: ");
            String plainMessage = sc.nextLine();
            System.out.print("Type the key: ");
            String key = sc.nextLine();

            String processedText = ProcessText(plainMessage);
            String keyword = ProcessText(key);

            String extendedKey = ExtendKey(processedText, keyword);
            String encryptedText = Encrypt(processedText, extendedKey);

            System.out.println("The encrypted message is: " + encryptedText);
            System.out.println("\nDecrypting...");
            String recoveredMessage = Decrypt(encryptedText, extendedKey);
            System.out.println("The decrypted message is: " + recoveredMessage);
        }
    }
}