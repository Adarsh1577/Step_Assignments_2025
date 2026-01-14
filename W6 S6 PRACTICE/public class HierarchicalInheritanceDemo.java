import java.util.*;

// ================== BASE CLASS ==================
class Employee {
    protected String employeeId;
    protected String name;
    protected double baseSalary;
    protected String department;
    protected Date joiningDate;

    // Constructor
    public Employee(String employeeId, String name, double baseSalary, String department, Date joiningDate) {
        if (employeeId == null || name == null || department == null || baseSalary < 0) {
            throw new IllegalArgumentException("Invalid Employee parameters");
        }
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
        this.department = department;
        this.joiningDate = joiningDate;
        System.out.println("Employee " + name + " created in " + department);
    }

    // Methods to be overridden
    public double calculateSalary() {
        return baseSalary;
    }

    public String getJobDescription() {
        return "General Employee";
    }

    public void performWork() {
        System.out.println("Employee is working");
    }

    public void attendMeeting() {
        System.out.println("Employee attending meeting");
    }

    // Final methods (cannot be overridden)
    public final String getEmployeeId() {
        return employeeId;
    }

    public final void printEmployeeDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name +
                ", Department: " + department + ", Base Salary: " + baseSalary +
                ", Joining Date: " + joiningDate);
    }

    // Default behavior
    public void takeBreak() {
        System.out.println(name + " is taking a break.");
    }

    public void clockIn() {
        System.out.println(name + " clocked in.");
    }

    public void clockOut() {
        System.out.println(name + " clocked out.");
    }
}

// ================== DEVELOPER CLASS ==================
class Developer extends Employee {
    private String[] programmingLanguages;
    private String experienceLevel; // Junior/Mid/Senior
    private int projectsCompleted;

    public Developer(String employeeId, String name, double baseSalary, String department, Date joiningDate,
                     String[] programmingLanguages, String experienceLevel, int projectsCompleted) {
        super(employeeId, name, baseSalary, department, joiningDate);
        this.programmingLanguages = programmingLanguages;
        this.experienceLevel = experienceLevel;
        this.projectsCompleted = projectsCompleted;
        System.out.println("Developer profile created");
    }

    @Override
    public double calculateSalary() {
        double bonus = 0;
        switch (experienceLevel.toLowerCase()) {
            case "junior": bonus = 2000; break;
            case "mid": bonus = 5000; break;
            case "senior": bonus = 10000; break;
        }
        return baseSalary + bonus + (projectsCompleted * 500);
    }

    @Override
    public String getJobDescription() {
        return "Software Developer";
    }

    @Override
    public void performWork() {
        System.out.println("Developer is coding and debugging");
    }

    @Override
    public void attendMeeting() {
        System.out.println("Developer in technical meeting");
    }

    // Developer specific methods
    public void writeCode() {
        System.out.println("Writing code in " + Arrays.toString(programmingLanguages));
    }

    public void reviewCode() {
        System.out.println("Reviewing team's code");
    }

    public void deployApplication() {
        System.out.println("Deploying application to production");
    }
}

// ================== MANAGER CLASS ==================
class Manager extends Employee {
    private int teamSize;
    private String managementLevel; // Team Lead/Manager/Director
    private double budgetResponsibility;

    public Manager(String employeeId, String name, double baseSalary, String department, Date joiningDate,
                   int teamSize, String managementLevel, double budgetResponsibility) {
        super(employeeId, name, baseSalary, department, joiningDate);
        this.teamSize = teamSize;
        this.managementLevel = managementLevel;
        this.budgetResponsibility = budgetResponsibility;
        System.out.println("Manager profile created");
    }

    @Override
    public double calculateSalary() {
        double bonus = teamSize * 300;
        if (managementLevel.equalsIgnoreCase("Team Lead")) bonus += 3000;
        else if (managementLevel.equalsIgnoreCase("Manager")) bonus += 7000;
        else if (managementLevel.equalsIgnoreCase("Director")) bonus += 15000;
        return baseSalary + bonus;
    }

    @Override
    public String getJobDescription() {
        return "Team Manager";
    }

    @Override
    public void performWork() {
        System.out.println("Manager is coordinating team activities");
    }

    @Override
    public void attendMeeting() {
        System.out.println("Manager leading strategic meeting");
    }

    // Manager specific methods
    public void conductPerformanceReview() {
        System.out.println("Conducting team performance review");
    }

    public void assignTasks() {
        System.out.println("Assigning tasks to team members");
    }

    public void manageBudget() {
        System.out.println("Managing department budget of " + budgetResponsibility);
    }
}

// ================== INTERN CLASS ==================
class Intern extends Employee {
    private String university;
    private int internshipDuration;
    private String mentor;
    private boolean isFullTime;

    public Intern(String employeeId, String name, double stipend, String department, Date joiningDate,
                  String university, int internshipDuration, String mentor, boolean isFullTime) {
        super(employeeId, name, stipend, department, joiningDate);
        this.university = university;
        this.internshipDuration = internshipDuration;
        this.mentor = mentor;
        this.isFullTime = isFullTime;
        System.out.println("Intern onboarded");
    }

    @Override
    public double calculateSalary() {
        return baseSalary; // stipend amount
    }

    @Override
    public String getJobDescription() {
        return "Intern";
    }

    @Override
    public void performWork() {
        System.out.println("Intern is learning and assisting");
    }

    // Intern specific methods
    public void attendTraining() {
        System.out.println("Intern attending training session");
    }

    public void submitReport() {
        System.out.println("Submitting weekly progress report");
    }

    public void seekMentorship() {
        System.out.println("Getting guidance from mentor " + mentor);
    }
}

// ================== DEMO CLASS ==================
public class HierarchicalInheritanceDemo {
    public static void main(String[] args) {
        Date today = new Date();

        Employee[] employees = new Employee[3];
        employees[0] = new Developer("D101", "Alice", 50000, "IT", today,
                new String[]{"Java", "Python"}, "Senior", 12);
        employees[1] = new Manager("M201", "Bob", 60000, "Operations", today,
                10, "Manager", 200000);
        employees[2] = new Intern("I301", "Charlie", 10000, "HR", today,
                "MIT", 12, "M201", false);

        System.out.println("\n=== Polymorphic Behavior ===");
        for (Employee emp : employees) {
            emp.printEmployeeDetails();
            System.out.println("Role: " + emp.getJobDescription());
            emp.performWork();
            emp.attendMeeting();
            System.out.println("Calculated Salary: " + emp.calculateSalary());
            emp.takeBreak();
            System.out.println();
        }

        System.out.println("\n=== Subclass-specific methods via casting ===");
        if (employees[0] instanceof Developer) {
            Developer d = (Developer) employees[0];
            d.writeCode();
            d.deployApplication();
        }

        if (employees[1] instanceof Manager) {
            Manager m = (Manager) employees[1];
            m.assignTasks();
            m.manageBudget();
        }

        if (employees[2] instanceof Intern) {
            Intern i = (Intern) employees[2];
            i.attendTraining();
            i.seekMentorship();
        }
    }
}
