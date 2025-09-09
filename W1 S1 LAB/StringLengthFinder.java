import java.util.Scanner;

public class StringLengthFinder {

    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count); 
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.next(); // next() reads one word

        int myLength = findLength(input);
        int builtInLength = input.length();

        System.out.println("Length (without length() method): " + myLength);
        System.out.println("Length (using length() method): " + builtInLength);

        scanner.close();
    }
}
