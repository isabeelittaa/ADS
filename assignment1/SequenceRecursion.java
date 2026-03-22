package assignment1;
import java.util.Scanner;

public class SequenceRecursion{

    // Task 5. Fibonacci Number
    public static int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Index cannot be negative.");
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Task 6. Power Function
    public static double power(double a, int n) {
        if (n == 0) return 1;
        if (n < 0) return 1 / power(a, -n);
        return a * power(a, n - 1);
    }

    // Task 7. Reverse Output
    public static void reverse(String[] arr, int index) {
    if (index < 0) return;
    System.out.print(arr[index] + " ");
    reverse(arr, index - 1);
}

    public static void main(String[] args) {
        // Task 5
        System.out.println(fibonacci(17));

        // Task 6
        System.out.println(power(2, 10));

        // Task 7
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");
        reverse(numbers, numbers.length - 1);
    }
}