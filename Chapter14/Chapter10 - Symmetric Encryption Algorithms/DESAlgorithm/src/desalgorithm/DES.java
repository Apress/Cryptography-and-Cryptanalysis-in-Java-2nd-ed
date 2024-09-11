package desalgorithm;

import java.io.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class DES {
	//instance for encryption  
    private static Cipher encOp;
    
    //instance for decryption  
    private static Cipher decOp;
    
    //path for the file that will be encrypted
    private static final String textFileToEncrypt = "E:/Files/apressFile.txt";
    
    //path for the encrypted file
    private static final String encFile = "E:/Files/apress_enc.txt";
    
    //path for decryptied file
    private static final String decFile = "E:/Files/apress_dec.txt";
    
    //vector for initialization
    private static final byte[] iv = { 25, 38, 15, 43, 59, 92, 66, 73 };
    
    public static void main(String[] args) {
        try {
        	//setting up the key 
            SecretKey secret_key = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec parameters_specs = new IvParameterSpec(iv);
            
            //specify the encryption mode
            encOp = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encOp.init(Cipher.ENCRYPT_MODE, secret_key, parameters_specs);
            
            //specify the decryption mode
            decOp = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decOp.init(Cipher.DECRYPT_MODE, secret_key, parameters_specs);
            
            //encrypt the file
            processFile(encOp, new File(textFileToEncrypt), new File(encFile));
            
            //decrypt the file 
            processFile(decOp, new File(encFile), new File(decFile));
            
            System.out.println("The files for encryption and decryption results have created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void processFile(Cipher cipher, File inputFile, File outputFile) throws IOException {
        try (InputStream in = new FileInputStream(inputFile);
             OutputStream out = new FileOutputStream(outputFile);
             CipherOutputStream cipherOut = new CipherOutputStream(out, cipher)) {
            byte[] buffer = new byte[512];
            int numRead;
            while ((numRead = in.read(buffer)) >= 0) {
                cipherOut.write(buffer, 0, numRead);
            }
        }
    }
}
