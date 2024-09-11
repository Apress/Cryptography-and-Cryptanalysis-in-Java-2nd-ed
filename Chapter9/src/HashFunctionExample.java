import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Scanner;

public class HashFunctionExample {
    public static void main(String[] args) throws Exception {
        System.out.println("\nType the text:");
        
        try (Scanner scn = new Scanner(System.in)) {
            var input = scn.nextLine(); 
            System.out.println("\nThe input text: " + input);

            var outputSha = MessageDigest.getInstance("SHA-512");
            outputSha.update(input.getBytes());

            byte[] digest = outputSha.digest();
            
            var hexDigest = HexFormat.of().formatHex(digest);
            System.out.println("The hex representation: " + hexDigest);
        }
    }
}