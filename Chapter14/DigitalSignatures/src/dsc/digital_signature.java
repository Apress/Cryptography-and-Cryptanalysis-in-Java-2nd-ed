package dsc;

import java.security.*;
import java.util.HexFormat;

public class digital_signature 
{	
	//the function will help to generate the digital signature based on SHA256 and RSA algorithm, using an input and a private key
	public static byte[] GenerateDS(byte[] dataInput, PrivateKey prvKey) throws Exception
	{
		var signSHA256RSA = Signature.getInstance("SHA256withRSA");
		signSHA256RSA.initSign(prvKey);
		signSHA256RSA.update(dataInput);
		return signSHA256RSA.sign();
	}
	
	//the function will generate a asymmetric key pair based on SecureRandom class and using RSA algorithm
	//128 value
	public static KeyPair AKPGenRSA(int size) throws Exception
	{
		var sr = new SecureRandom();
		var kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(size, sr);
		return kpg.generateKeyPair();
	}
	
	//function for verifying the digital signature based on a public key 
	public static boolean CheckDS(byte[] message, byte[] verifyingSignature, PublicKey pubKey) throws Exception
	{
		var signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(pubKey);
		signature.update(message);
		return signature.verify(verifyingSignature);
	}
	
	 //main function
	 public static void main(String args[]) throws Exception
	 {	
	     var message = "Welcome To Apress. Enjoy learning cryptography.";
	     var kp512 = AKPGenRSA(512);
	     var kp1024 = AKPGenRSA(1024);
	     var kp2048 = AKPGenRSA(2048);
		
		 var generatedSignature512 = GenerateDS(message.getBytes(), kp512.getPrivate());
		 var generatedSignature1024 = GenerateDS(message.getBytes(), kp1024.getPrivate());
		 var generatedSignature2048 = GenerateDS(message.getBytes(), kp2048.getPrivate());
		 
		 System.out.println("The message for which the signature will be computed is -> " + message);
		 System.out.println("The length of the message is -> " + message.length());
		 
		 System.out.println("\n");
		 
		 System.out.println("The 512-signature is -> \n " + bytesToHex(generatedSignature512));
		 System.out.println("Status of 512-verification -> " + CheckDS(message.getBytes(), generatedSignature512, kp512.getPublic()));
		 
		 System.out.println("\n");
		 
		 System.out.println("The 1024-signature is -> \n " + bytesToHex(generatedSignature1024));
		 System.out.println("Status of 1024-verification -> " + CheckDS(message.getBytes(), generatedSignature1024, kp1024.getPublic()));		 
		 
		 System.out.println("\n");
		 
		 System.out.println("The 2048-signature is -> \n " + bytesToHex(generatedSignature2048));
		 System.out.println("Status of 2048-verification -> " + CheckDS(message.getBytes(), generatedSignature2048, kp2048.getPublic()));
	 }
	 
	 public static String bytesToHex(byte[] bytes) {
	        return HexFormat.of().formatHex(bytes);
	    }
}