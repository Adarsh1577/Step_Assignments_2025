import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.lang.reflect.*;

public class EmployeeBean implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private LocalDate hireDate;
    private boolean isActive;

    public EmployeeBean() {
    }

    public EmployeeBean(String employeeId, String firstName, String lastName, double salary, String department, LocalDate hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary > 0 ? salary : 0.0;
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { if (salary >= 0) this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getFullName() { return firstName + " " + lastName; }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(salary);
    }

    public void setFullName(String fullName) {
        String[] parts = fullName.split(" ", 2);
        if (parts.length >= 2) {
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", active=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public static void main(String[] args) {
        EmployeeBean e1 = new EmployeeBean();
        e1.setEmployeeId("E001");
        e1.setFullName("Alice Johnson");
        e1.setSalary(60000);
        e1.setDepartment("HR");
        e1.setHireDate(LocalDate.of(2018, 5, 10));
        e1.setActive(true);

        EmployeeBean e2 = new EmployeeBean("E002", "Bob", "Smith", 75000, "IT", LocalDate.of(2020, 3, 15), true);
        EmployeeBean e3 = new EmployeeBean("E003", "Carol", "White", 50000, "Finance", LocalDate.of(2015, 7, 20), false);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);

        List<EmployeeBean> employees = new ArrayList<>(Arrays.asList(e1, e2, e3));
        employees.sort(Comparator.comparingDouble(EmployeeBean::getSalary));
        System.out.println("\nSorted by Salary:");
        employees.forEach(System.out::println);

        System.out.println("\nActive Employees:");
        employees.stream().filter(EmployeeBean::isActive).forEach(System.out::println);

        System.out.println("\nReflection Introspection:");
        JavaBeanProcessor.printAllProperties(e1);

        System.out.println("\nCopying Properties from e2 to e1:");
        JavaBeanProcessor.copyProperties(e2, e1);
        System.out.println(e1);
    }
}

class JavaBeanProcessor {
    public static void printAllProperties(EmployeeBean emp) {
        try {
            for (Method m : emp.getClass().getMethods()) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    if (m.getParameterCount() == 0) {
                        Object value = m.invoke(emp);
                        System.out.println(m.getName() + " -> " + value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            for (Method m : source.getClass().getMethods()) {
                if (m.getName().startsWith("get") && m.getParameterCount() == 0) {
                    Object value = m.invoke(source);
                    String setterName = "set" + m.getName().substring(3);
                    try {
                        Method setter = target.getClass().getMethod(setterName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {}
                } else if (m.getName().startsWith("is") && m.getParameterCount() == 0) {
                    Object value = m.invoke(source);
                    String setterName = "set" + m.getName().substring(2);
                    try {
                        Method setter = target.getClass().getMethod(setterName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
