package ciphers;

import java.util.Scanner;

class Utils {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    int getCharIndex(char c) {
        return alphabet.indexOf(c);
    }

    char getCharAtIndex(int index) {
        return alphabet.charAt(index);
    }
}

public class HillCipher {

    Utils util = new Utils();
    int keySize;
    int[][] keyMatrix;
    Scanner scanner = new Scanner(System.in);

    HillCipher(int keySize) {
        this.keySize = keySize;
        this.keyMatrix = new int[keySize][keySize];
    }

    void keyInitialize(String message) {
        System.out.println(message);
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }
    }

    String blockEncryption(String plainBlock) {
        StringBuilder encryptedBlock = new StringBuilder();
        int[] indexMatrix = new int[keySize];
        int[] columnMatrix = new int[keySize];

        for (int i = 0; i < keySize; i++) {
            indexMatrix[i] = util.getCharIndex(plainBlock.charAt(i));
        }

        for (int i = 0; i < keySize; i++) {
            int sumElem = 0;
            for (int k = 0; k < keySize; k++) {
                sumElem += keyMatrix[i][k] * indexMatrix[k];
            }
            columnMatrix[i] = sumElem % 26;
            encryptedBlock.append(util.getCharAtIndex(columnMatrix[i]));
        }
        return encryptedBlock.toString();
    }

    String messageEncryption(String plainMessage) throws Exception {
        StringBuilder encryptedMessage = new StringBuilder();
        keyInitialize("Enter the values for key matrix:");
        System.out.println("\n[Encrypting...]\n");

        while (plainMessage.length() % keySize != 0) {
            plainMessage += "Z";
        }

        for (int i = 0; i < plainMessage.length(); i += keySize) {
            encryptedMessage.append(blockEncryption(plainMessage.substring(i, i + keySize)));
        }
        return encryptedMessage.toString();
    }

    String blockDecryption(String encryptedBlock) {
        StringBuilder decryptedBlock = new StringBuilder();
        int[] indexMatrix = new int[keySize];
        int[] columnMatrix = new int[keySize];

        for (int i = 0; i < keySize; i++) {
            indexMatrix[i] = util.getCharIndex(encryptedBlock.charAt(i));
        }

        for (int i = 0; i < keySize; i++) {
            int sumElem = 0;
            for (int k = 0; k < keySize; k++) {
                sumElem += keyMatrix[i][k] * indexMatrix[k];
            }
            sumElem = (sumElem % 26 + 26) % 26;  // Normalize negative values
            columnMatrix[i] = sumElem;
            decryptedBlock.append(util.getCharAtIndex(columnMatrix[i]));
        }
        return decryptedBlock.toString();
    }

    String messageDecryption(String encryptedMessage) throws Exception {
        StringBuilder decryptedMessage = new StringBuilder();
        keyInitialize("\n---\n\n Enter the values for inverted key matrix:");

        System.out.println("\n[Decrypting...]\n");

        for (int i = 0; i < encryptedMessage.length(); i += keySize) {
            decryptedMessage.append(blockDecryption(encryptedMessage.substring(i, i + keySize)));
        }
        return decryptedMessage.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Enter the plain message:");
        String plainMessage = new Scanner(System.in).nextLine();

        System.out.println("Enter the size of the matrix key:");
        int keySize = new Scanner(System.in).nextInt();

        HillCipher cipher = new HillCipher(keySize);

        plainMessage = plainMessage.replaceAll(" ", "").toUpperCase();

        String encryptedMessage = cipher.messageEncryption(plainMessage);
        System.out.println("Obtained encrypted message: \n" + encryptedMessage);

        String decryptedMessage = cipher.messageDecryption(encryptedMessage);
        System.out.println("Obtained decrypted message:\n" + decryptedMessage);
    }
}