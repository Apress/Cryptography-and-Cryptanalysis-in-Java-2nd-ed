import java.util.Random;

public class QuantumParticle {
    private double position;
    private double momentum;
    private static final Random random = new Random();

    public QuantumParticle(double position, double momentum) {
        this.position = position;
        this.momentum = momentum;
    }

    // Simulates measuring the position
    public double measurePosition() {
        double uncertainty = 0.1; // Base uncertainty
        double fluctuation = random.nextDouble() * uncertainty;
        // Introduce fluctuation in momentum when position is measured
        momentum += (random.nextBoolean() ? 1 : -1) * fluctuation;
        return position + (random.nextBoolean() ? 1 : -1) * fluctuation;
    }

    // Simulates measuring the momentum
    public double measureMomentum() {
        double uncertainty = 0.1; // Base uncertainty
        double fluctuation = random.nextDouble() * uncertainty;
        // Introduce fluctuation in position when momentum is measured
        position += (random.nextBoolean() ? 1 : -1) * fluctuation;
        return momentum + (random.nextBoolean() ? 1 : -1) * fluctuation;
    }

    public static void main(String[] args) {
        QuantumParticle particle = new QuantumParticle(5.0, 10.0);
        System.out.println("Initial position: " + particle.position);
        System.out.println("Initial momentum: " + particle.momentum);

        double measuredPosition = particle.measurePosition();
        double measuredMomentum = particle.measureMomentum();

        System.out.println("Measured position: " + measuredPosition);
        System.out.println("Measured momentum: " + measuredMomentum);
        System.out.println("Updated position: " + particle.position);
        System.out.println("Updated momentum: " + particle.momentum);
    }
}
