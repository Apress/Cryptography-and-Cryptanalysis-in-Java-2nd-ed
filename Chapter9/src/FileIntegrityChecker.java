import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HexFormat;

public class FileIntegrityChecker {

    public static String calculateFileHash(String filePath) throws Exception {
        
        var fileContent = Files.readAllBytes(Paths.get(filePath));
        var digest = MessageDigest.getInstance("SHA-256");
        var hashBytes = digest.digest(fileContent);
        
        return HexFormat.of().formatHex(hashBytes);
    }

    public static void main(String[] args) throws Exception {
        var filePath = "E:/Files/TestFile.txt";
        var fileHash = calculateFileHash(filePath);

        System.out.println("SHA-256 Hash: " + fileHash);
    }
}