package assignment1;
public class NumbersRecursion {

    // Task 1. Print Digits of a Number
    public static void printDigits(int n) {
        if (n == 0) return;

        printDigits(n / 10);
        System.out.println(n % 10);
    }

    // Task 2. Average of Elements
    public static int sum(int[] arr, int index) {
        if (index == arr.length) return 0;
        return arr[index] + sum(arr, index + 1);
    } 

    public static double average(int[] arr) {
        return (double) sum(arr, 0) / arr.length;
    }

    // Task 3. Prime Number Check
    public static boolean primeCheck(int n, int divisor) {
        if (n <= 2) return n == 2;
        if (n % divisor == 0) return false;
        if (divisor * divisor > n) return true;

        return primeCheck(n, divisor + 1);
    }

    // Task 4. Factorial
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        // Task 1
        printDigits(5481); 
        
        // Task 2
        int[] arr = {3, 2, 4, 1};
        System.out.println(average(arr));

        // Task 3
        int n = 1;
        if (primeCheck(n,2))
            System.out.println("Prime");
        else
            System.out.println("Composite");

        // Task 4
        System.out.println(factorial(5));
    }
}