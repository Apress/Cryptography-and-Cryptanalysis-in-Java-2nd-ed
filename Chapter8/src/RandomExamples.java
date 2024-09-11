import java.util.random.RandomGenerator;

public class RandomExamples {

    public static void main(String[] args) {

        RandomGenerator randomObject = RandomGenerator.getDefault();

        System.out.println("Generating an integer value < 1000... " + randomObject.nextInt(1000));
        System.out.println("Generating an integer value... " + randomObject.nextInt());
        System.out.println("Generating a long value... " + randomObject.nextLong()); 
        System.out.println("Generating a Boolean value... " + randomObject.nextBoolean());
        System.out.println("Generating a double value... " + randomObject.nextDouble());
        System.out.println("Generating a float value... " + randomObject.nextFloat());
        
        byte[] byteArray = new byte[15];
        randomObject.nextBytes(byteArray);
        System.out.println("Generating a sequence of bytes...");
      
        System.out.println(java.util.Arrays.toString(byteArray));     
    }
}
