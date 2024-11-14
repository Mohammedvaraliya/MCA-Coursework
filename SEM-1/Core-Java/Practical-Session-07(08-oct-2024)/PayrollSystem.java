import java.util.Scanner;

// Interface for Net Pay Calculation
interface NetPayCalculation {
    void grossPayCalculation();

    void taxCalculation();

    float netPayCalculation();
}

class Employee {
    int ID_No;
    String name;
    String designation;
    String PAN_No;
    String department;
    float basicPay;

    void getData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        ID_No = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        name = scanner.nextLine();
        System.out.print("Enter Designation: ");
        designation = scanner.nextLine();
        System.out.print("Enter PAN Number: ");
        PAN_No = scanner.nextLine();
        System.out.print("Enter Department: ");
        department = scanner.nextLine();
        System.out.print("Enter Basic Pay: ");
        basicPay = scanner.nextFloat();

        scanner.close();
    }
}

class Payroll extends Employee implements NetPayCalculation {
    float grossPay;
    float taxableAmount;
    float netPay;

    // Calculate gross pay
    @Override
    public void grossPayCalculation() {
        float DA = 0.50f * basicPay; // DA = 50% of basic pay
        float HRA = 0.20f * basicPay; // HRA = 20% of basic pay
        grossPay = basicPay + DA + HRA; // Gross Pay = Basic Pay + DA + HRA
    }

    @Override
    public void taxCalculation() {
        float annualIncome = grossPay * 12; // Total annual income
        if (annualIncome >= 100000 && annualIncome < 200000) {
            taxableAmount = 0.20f * grossPay; // 20% tax
        } else if (annualIncome >= 200000) {
            taxableAmount = 0.30f * grossPay; // 30% tax
        } else {
            taxableAmount = 0; // No tax for income below 100000
        }
    }

    @Override
    public float netPayCalculation() {
        netPay = grossPay - taxableAmount; // Net Pay = Gross Pay - Taxable Amount
        return netPay;
    }

    void displayDetails() {
        System.out.println("\nEmployee Details:");
        System.out.println("ID: " + ID_No);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("PAN No: " + PAN_No);
        System.out.println("Department: " + department);
        System.out.println("Basic Pay: " + basicPay);
        System.out.println("Gross Pay: " + grossPay);
        System.out.println("Taxable Amount: " + taxableAmount);
        System.out.println("Net Pay: " + netPay);
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Payroll payroll = new Payroll();
        payroll.getData();
        payroll.grossPayCalculation();
        payroll.taxCalculation();
        payroll.netPayCalculation();
        payroll.displayDetails();
    }
}