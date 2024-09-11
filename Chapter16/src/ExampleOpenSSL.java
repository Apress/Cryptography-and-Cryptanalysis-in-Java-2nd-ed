import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExampleOpenSSL {
	public static void main(String[] args) {
        try {
            // Generate Bob's private key
            String priv_key_cmd = "openssl genrsa -out BobPrivKey.pem 2048";
            executeCommand(priv_key_cmd);

            // Extract Bob's public key
            String pub_key_cmd = "openssl rsa -pubout -in BobPrivKey.pem -out BobPubKey.pem";
            executeCommand(pub_key_cmd);

            // Print Bob's public key to console
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("BobPubKey.pem")));
            String l;
            while ((l = br.readLine()) != null) {
                System.out.println(l);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String cmd) throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec(cmd);
        proc.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String l;
        while ((l = br.readLine()) != null) {
            System.out.println(l);
        }
    }
}
