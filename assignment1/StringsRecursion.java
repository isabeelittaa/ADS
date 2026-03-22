package assignment1;

public class StringsRecursion {

    // Task 8. Check Digits in String
    public static boolean Digits(String s) {
        if (s == null || s.isEmpty()) return true;
        if (!Character.isDigit(s.charAt(0))) return false;
        return Digits(s.substring(1));
    }

    // Task 9. Count Characters in a String
    public static int Characters(String s) {
        if (s == null || s.isEmpty()) return 0;
        return 1 + Characters(s.substring(1));
    }

    // Task 10. Greatest Common Divisor (GCD)
    public static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        // Task 8
        System.out.println(Digits("123a12"));

        // Task 9
        System.out.println(Characters("hello"));

        // Task 10
        System.out.println(gcd(32, 48));
    }
}