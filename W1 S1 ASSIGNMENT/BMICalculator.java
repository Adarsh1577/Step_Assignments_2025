import java.util.Scanner;

public class BMICalculator {

    // Method to calculate BMI and Status
    public static String[] calculateBMI(double weight, double heightCm) {
        double heightM = heightCm / 100.0;  // convert cm to m
        double bmi = weight / (heightM * heightM);

        String status;
        if (bmi <= 18.4) {
            status = "Underweight";
        } else if (bmi <= 24.9) {
            status = "Normal";
        } else if (bmi <= 39.9) {
            status = "Overweight";
        } else {
            status = "Obese";
        }

        return new String[] {
            String.format("%.2f", weight),
            String.format("%.2f", heightCm),
            String.format("%.2f", bmi),
            status
        };
    }

    // Method to process all persons
    public static String[][] processBMI(double[][] data) {
        String[][] results = new String[data.length][4];

        for (int i = 0; i < data.length; i++) {
            results[i] = calculateBMI(data[i][0], data[i][1]);
        }

        return results;
    }

    // Method to display results in tabular form
    public static void display(String[][] results) {
        System.out.printf("%-10s %-10s %-10s %-15s%n", "Weight(kg)", "Height(cm)", "BMI", "Status");
        System.out.println("----------------------------------------------------------");

        for (String[] row : results) {
            System.out.printf("%-10s %-10s %-10s %-15s%n", row[0], row[1], row[2], row[3]);
        }
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] data = new double[10][2]; // 10 persons, weight & height

        // Taking input
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter details for Person " + (i + 1));
            System.out.print("Weight (kg): ");
            data[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            data[i][1] = sc.nextDouble();
        }

        // Processing
        String[][] results = processBMI(data);

        // Displaying
        display(results);

        sc.close();
    }
}
