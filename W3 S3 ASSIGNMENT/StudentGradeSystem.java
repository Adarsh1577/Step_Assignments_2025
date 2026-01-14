import java.util.*;

class Subject {
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }

    public String getSubjectCode() { return subjectCode; }
    public String getSubjectName() { return subjectName; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }

    public void displaySubjectInfo() {
        System.out.println(subjectCode + " - " + subjectName + " | Credits: " + credits + " | Instructor: " + instructor);
    }
}

class Student {
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;
    private double[] marks;
    private double gpa;

    // Static members
    private static int totalStudents = 0;
    private static String schoolName = "Default School";
    private static String[] gradingScale = {"A:90-100", "B:75-89", "C:60-74", "D:40-59", "F:<40"};
    private static double passPercentage = 40.0;

    public Student(String studentName, String className, String[] subjects) {
        this.studentId = "STU" + (++totalStudents);
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length];
    }

    // Add marks
    public void addMarks(String subject, double mark) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                marks[i] = mark;
                return;
            }
        }
        System.out.println("Subject not found for " + studentName);
    }

    // Calculate GPA
    public void calculateGPA() {
        double total = 0;
        for (double m : marks) {
            total += m;
        }
        double avg = total / marks.length;
        this.gpa = avg / 20; // GPA on 5-point scale
    }

    // Generate report card
    public void generateReportCard() {
        System.out.println("\n===== Report Card for " + studentName + " (" + studentId + ") =====");
        System.out.println("Class: " + className);
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + ": " + marks[i] + " (" + getGrade(marks[i]) + ")");
        }
        System.out.println("GPA: " + gpa);
        System.out.println("Promotion Eligibility: " + (checkPromotionEligibility() ? "Eligible" : "Not Eligible"));
        System.out.println("=========================================================");
    }

    // Check promotion
    public boolean checkPromotionEligibility() {
        for (double m : marks) {
            if (m < passPercentage) return false;
        }
        return true;
    }

    // Grade based on marks
    private String getGrade(double mark) {
        if (mark >= 90) return "A";
        else if (mark >= 75) return "B";
        else if (mark >= 60) return "C";
        else if (mark >= 40) return "D";
        else return "F";
    }

    public double getGPA() { return gpa; }
    public String getStudentName() { return studentName; }
    public String getClassName() { return className; }

    // Static methods
    public static void setGradingScale(String[] scale) { gradingScale = scale; }
    public static void setSchoolName(String name) { schoolName = name; }

    public static double calculateClassAverage(Student[] students) {
        double total = 0;
        for (Student s : students) {
            total += s.gpa;
        }
        return total / students.length;
    }

    public static void getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
        System.out.println("\n=== Top " + count + " Performers ===");
        for (int i = 0; i < Math.min(count, students.length); i++) {
            System.out.println((i + 1) + ". " + students[i].studentName + " | GPA: " + students[i].gpa);
        }
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n========= " + schoolName + " School Report =========");
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Class Average GPA: " + calculateClassAverage(students));
        getTopPerformers(students, 3);
        System.out.println("===================================================");
    }
}

public class StudentGradeSystem {
    public static void main(String[] args) {
        // Set school name
        Student.setSchoolName("SRM International School");

        // Subjects
        String[] subjects = {"Math", "Science", "English"};

        // Create students
        Student s1 = new Student("Arjun", "10A", subjects);
        Student s2 = new Student("Priya", "10A", subjects);
        Student s3 = new Student("Rohan", "10B", subjects);

        // Add marks
        s1.addMarks("Math", 95);
        s1.addMarks("Science", 88);
        s1.addMarks("English", 76);

        s2.addMarks("Math", 67);
        s2.addMarks("Science", 72);
        s2.addMarks("English", 81);

        s3.addMarks("Math", 35);
        s3.addMarks("Science", 50);
        s3.addMarks("English", 42);

        // Calculate GPA
        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();

        // Generate report cards
        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        // Generate school report
        Student[] allStudents = {s1, s2, s3};
        Student.generateSchoolReport(allStudents);
    }
}
