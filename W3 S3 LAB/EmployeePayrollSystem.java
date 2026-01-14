class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private static int empCounter = 0;

    // Constructor for Full-Time Employee
    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    // Constructor for Part-Time Employee
    public Employee(String empName, String department, double hourlyRate, int hoursWorked) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-Time";
        totalEmployees++;
    }

    // Constructor for Contract Employee
    public Employee(String empName, String department, double fixedAmount, boolean isContract) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = fixedAmount;
        this.empType = "Contract";
        totalEmployees++;
    }

    private static String generateEmpId() {
        empCounter++;
        return String.format("E%03d", empCounter);
    }

    // Overloaded calculateSalary()
    public double calculateSalary(double bonus) { // For full-time
        if (empType.equals("Full-Time")) {
            return baseSalary + bonus;
        }
        return baseSalary;
    }

    public double calculateSalary(int hours, double hourlyRate) { // For part-time
        if (empType.equals("Part-Time")) {
            return hours * hourlyRate;
        }
        return baseSalary;
    }

    public double calculateSalary() { // For contract
        return baseSalary;
    }

    // Overloaded calculateTax()
    public double calculateTax(double salary, double taxRate) {
        return salary * taxRate;
    }

    public double calculateTax(double salary) {
        if (empType.equals("Full-Time")) {
            return salary * 0.20; // 20% tax
        } else if (empType.equals("Part-Time")) {
            return salary * 0.10; // 10% tax
        } else {
            return salary * 0.15; // 15% tax for contract
        }
    }

    public void generatePaySlip(double salary, double tax) {
        System.out.println("\n--- Pay Slip ---");
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax Deducted: " + tax);
        System.out.println("Net Salary: " + (salary - tax));
    }

    public void displayEmployeeInfo() {
        System.out.println("[" + empId + "] " + empName + " | Dept: " + department + " | Type: " + empType);
    }

    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + totalEmployees);
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        // Full-Time Employee
        Employee emp1 = new Employee("Alice", "IT", 50000);
        double salary1 = emp1.calculateSalary(10000); // base + bonus
        double tax1 = emp1.calculateTax(salary1);
        emp1.displayEmployeeInfo();
        emp1.generatePaySlip(salary1, tax1);

        // Part-Time Employee
        Employee emp2 = new Employee("Bob", "Finance", 200, 80); // hourly rate * hours
        double salary2 = emp2.calculateSalary(80, 200);
        double tax2 = emp2.calculateTax(salary2);
        emp2.displayEmployeeInfo();
        emp2.generatePaySlip(salary2, tax2);

        // Contract Employee
        Employee emp3 = new Employee("Charlie", "HR", 60000, true);
        double salary3 = emp3.calculateSalary();
        double tax3 = emp3.calculateTax(salary3);
        emp3.displayEmployeeInfo();
        emp3.generatePaySlip(salary3, tax3);

        // Company Report
        System.out.println("\n=== Company Payroll Report ===");
        Employee.displayTotalEmployees();
    }
}
