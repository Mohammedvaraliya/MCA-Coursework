// Base Abstract class
abstract class InterestCalculator {
    protected double principal;
    protected double rate;
    protected int time;

    public InterestCalculator(double principal, double rate, int time) {
        this.principal = principal;
        this.rate = rate;
        this.time = time;
    }

    public abstract double calculateInterest();
}

// Derived class for Simple Interest
class SimpleInterestCalculator extends InterestCalculator {

    public SimpleInterestCalculator(double principal, double rate, int time) {
        super(principal, rate, time);
    }

    @Override
    public double calculateInterest() {
        return (principal * rate * time) / 100;
    }
}

// Derived class for Compound Interest
class CompoundInterestCalculator extends InterestCalculator {
    private int numberOfTimesCompounded;

    public CompoundInterestCalculator(double principal, double rate, int time, int numberOfTimesCompounded) {
        super(principal, rate, time);
        this.numberOfTimesCompounded = numberOfTimesCompounded;
    }

    @Override
    public double calculateInterest() {
        return principal * Math.pow((1 + rate / (100 * numberOfTimesCompounded)), numberOfTimesCompounded * time) - principal;
    }
}

// Main class
public class InterestMain {
    public static void main(String[] args) {
        // Given data
        double principal = 25000;
        int time = 5;
        
        // Interest rates
        double simpleInterestRate = 9.25;
        double compoundInterestRate = 8.5;
        int numberOfTimesCompounded = 1; // Compounded annually

        // Calculate Simple Interest
        InterestCalculator simpleInterestCalculator = new SimpleInterestCalculator(principal, simpleInterestRate, time);
        double simpleInterest = simpleInterestCalculator.calculateInterest();
        
        // Calculate Compound Interest
        InterestCalculator compoundInterestCalculator = new CompoundInterestCalculator(principal, compoundInterestRate, time, numberOfTimesCompounded);
        double compoundInterest = compoundInterestCalculator.calculateInterest();
        
        // Output the results
        System.out.println("Deposit Amount: Rs " + principal);
        System.out.println("Time Period: " + time + " years");
        
        System.out.println("\nSimple Interest at " + simpleInterestRate + "% per annum:");
        System.out.println("Interest: Rs " + simpleInterest);
        
        System.out.println("\nCompound Interest at " + compoundInterestRate + "% per annum compounded annually:");
        System.out.println("Interest: Rs " + compoundInterest);
    }
}
