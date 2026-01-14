import java.util.*;

class Employee {
    private String empId;
    private String empName;
    private String department;
    private String designation;
    private double baseSalary;
    private String joinDate;
    private boolean[] attendanceRecord; // 30 days
    private String empType; // Full-time, Part-time, Contract

    // Static variables
    private static int totalEmployees = 0;
    private static double totalSalaryExpense = 0;
    private static String companyName = "Default Company";
    private static int workingDaysPerMonth = 30;

    public Employee(String empName, String department, String designation,
                    double baseSalary, String joinDate, String empType) {
        this.empId = "E" + (++totalEmployees);
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.empType = empType;
        this.attendanceRecord = new boolean[workingDaysPerMonth];
    }

    public String getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
    public String getEmpType() { return empType; }

    // Mark attendance
    public void markAttendance(int day, boolean present) {
        if (day < 1 || day > workingDaysPerMonth) {
            System.out.println("❌ Invalid day.");
            return;
        }
        attendanceRecord[day - 1] = present;
    }

    // Salary calculation based on type
    public double calculateSalary() {
        int presentDays = 0;
        for (boolean p : attendanceRecord) {
            if (p) presentDays++;
        }

        double salary = 0;
        switch (empType) {
            case "Full-time":
                salary = baseSalary + calculateBonus();
                break;
            case "Part-time":
                salary = (baseSalary / workingDaysPerMonth) * presentDays;
                break;
            case "Contract":
                salary = baseSalary;
                break;
            default:
                salary = baseSalary;
        }

        totalSalaryExpense += salary;
        return salary;
    }

    // Bonus: performance-based (attendance > 90%)
    public double calculateBonus() {
        int presentDays = 0;
        for (boolean p : attendanceRecord) {
            if (p) presentDays++;
        }
        double attendanceRate = (presentDays * 100.0) / workingDaysPerMonth;
        if (attendanceRate >= 90) return 0.1 * baseSalary;
        return 0;
    }

    // Leave request
    public void requestLeave(int day) {
        if (day < 1 || day > workingDaysPerMonth) {
            System.out.println("❌ Invalid leave day.");
            return;
        }
        attendanceRecord[day - 1] = false;
        System.out.println("📝 Leave granted to " + empName + " for day " + day);
    }

    // Payslip
    public void generatePaySlip() {
        double salary = calculateSalary();
        System.out.println("\n===== Pay Slip =====");
        System.out.println("Employee: " + empName + " (" + empId + ")");
        System.out.println("Department: " + department + " | Designation: " + designation);
        System.out.println("Type: " + empType);
        System.out.println("Base Salary: ₹" + baseSalary);
        System.out.println("Bonus: ₹" + calculateBonus());
        System.out.println("Total Salary: ₹" + salary);
        System.out.println("====================\n");
    }

    // Static methods
    public static void setCompanyName(String name) { companyName = name; }
    public static void setWorkingDays(int days) { workingDaysPerMonth = days; }
    public static double getTotalSalaryExpense() { return totalSalaryExpense; }
    public static String getCompanyName() { return companyName; }
}

class Department {
    private String deptId;
    private String deptName;
    private Employee manager;
    private Employee[] employees;
    private int empCount;
    private double budget;

    public Department(String deptId, String deptName, Employee manager, double budget, int capacity) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.budget = budget;
        this.employees = new Employee[capacity];
        this.empCount = 0;
    }

    public void addEmployee(Employee e) {
        if (empCount < employees.length) {
            employees[empCount++] = e;
        }
    }

    public double getDepartmentExpenses() {
        double total = 0;
        for (int i = 0; i < empCount; i++) {
            total += employees[i].calculateSalary();
        }
        return total;
    }

    public void displayDepartmentInfo() {
        System.out.println("\n📌 Department: " + deptName + " (ID: " + deptId + ")");
        System.out.println("Manager: " + manager.getEmpName());
        System.out.println("Employees:");
        for (int i = 0; i < empCount; i++) {
            System.out.println(" - " + employees[i].getEmpName() + " (" + employees[i].getEmpType() + ")");
        }
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        // Company setup
        Employee.setCompanyName("TechSolutions Pvt Ltd");
        Employee.setWorkingDays(30);

        // Create Employees
        Employee e1 = new Employee("Arjun", "IT", "Developer", 50000, "2023-01-01", "Full-time");
        Employee e2 = new Employee("Priya", "IT", "Tester", 30000, "2023-02-01", "Part-time");
        Employee e3 = new Employee("Ravi", "HR", "Consultant", 40000, "2023-03-01", "Contract");

        // Mark attendance (Arjun full attendance, Priya 20 days, Ravi irrelevant for contract)
        for (int i = 1; i <= 30; i++) e1.markAttendance(i, true);
        for (int i = 1; i <= 20; i++) e2.markAttendance(i, true);

        // Departments
        Department d1 = new Department("D1", "IT", e1, 200000, 5);
        d1.addEmployee(e1);
        d1.addEmployee(e2);

        Department d2 = new Department("D2", "HR", e3, 100000, 5);
        d2.addEmployee(e3);

        // Generate PaySlips
        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        // Department info
        d1.displayDepartmentInfo();
        d2.displayDepartmentInfo();

        // Reports
        System.out.println("\n===== Company Report =====");
        System.out.println("Company: " + Employee.getCompanyName());
        System.out.println("Total Salary Expense: ₹" + Employee.getTotalSalaryExpense());
        System.out.println("IT Dept Expenses: ₹" + d1.getDepartmentExpenses());
        System.out.println("HR Dept Expenses: ₹" + d2.getDepartmentExpenses());
    }
}
