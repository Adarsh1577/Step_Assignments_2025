public class Student {
    private String studentId;
    private String name;
    private double grade;
    private String course;

    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }

    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    public void displayStudent() {
        System.out.println("ID: " + studentId + ", Name: " + name + ", Grade: " + grade + ", Course: " + course + ", Letter Grade: " + calculateLetterGrade());
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setStudentId("S101");
        s1.setName("Adarsh");
        s1.setGrade(85.5);
        s1.setCourse("Computer Science");

        Student s2 = new Student("S102", "Garvita", 92.3, "Mathematics");

        System.out.println("Student 1:");
        System.out.println("ID: " + s1.getStudentId());
        System.out.println("Name: " + s1.getName());
        System.out.println("Grade: " + s1.getGrade());
        System.out.println("Course: " + s1.getCourse());
        s1.displayStudent();

        System.out.println("\nStudent 2:");
        System.out.println("ID: " + s2.getStudentId());
        System.out.println("Name: " + s2.getName());
        System.out.println("Grade: " + s2.getGrade());
        System.out.println("Course: " + s2.getCourse());
        s2.displayStudent();
    }
}
