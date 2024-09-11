import java.security.SecureRandom;
import java.util.Arrays;

public class SecureRandomExample {

    public static void main(String[] args) {
        var secureRandomObject = new SecureRandom();

        System.out.println("Securely generating an integer value < 1000... " + secureRandomObject.nextInt(1000));
        System.out.println("Securely generating an integer value... " + secureRandomObject.nextInt());
        System.out.println("Securely generating a long value... " + secureRandomObject.nextLong());
        System.out.println("Securely generating a Boolean value... " + secureRandomObject.nextBoolean());
        System.out.println("Securely generating a double value... " + secureRandomObject.nextDouble());
        System.out.println("Securely generating a float value... " + secureRandomObject.nextFloat());
        System.out.println("Securely generating a normally distributed double value... " + secureRandomObject.nextGaussian());

        byte[] byteArray = new byte[15];
        secureRandomObject.nextBytes(byteArray);
        System.out.println("Securely generating a sequence of bytes...");
        System.out.println(Arrays.toString(byteArray));
    }
}
