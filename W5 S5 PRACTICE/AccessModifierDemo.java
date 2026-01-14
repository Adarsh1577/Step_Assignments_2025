// File: AccessModifierDemo.java

public class AccessModifierDemo {
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;

    public AccessModifierDemo(int privateField, String defaultField,
                              double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    public void testInternalAccess() {
        System.out.println("\n--- Inside testInternalAccess() ---");
        System.out.println("privateField: " + privateField);
        System.out.println("defaultField: " + defaultField);
        System.out.println("protectedField: " + protectedField);
        System.out.println("publicField: " + publicField);
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(10, "Hello", 3.14, true);
        System.out.println("Public field: " + obj.publicField);
        obj.publicMethod();
        System.out.println("Protected field: " + obj.protectedField);
        obj.protectedMethod();
        System.out.println("Default field: " + obj.defaultField);
        obj.defaultMethod();
        obj.testInternalAccess();

        SamePackageTest.testAccess();
    }
}

class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo obj = new AccessModifierDemo(20, "World", 6.28, false);
        System.out.println("\n--- Inside SamePackageTest ---");
        System.out.println("Public field: " + obj.publicField);
        obj.publicMethod();
        System.out.println("Protected field: " + obj.protectedField);
        obj.protectedMethod();
        System.out.println("Default field: " + obj.defaultField);
        obj.defaultMethod();
    }
}
