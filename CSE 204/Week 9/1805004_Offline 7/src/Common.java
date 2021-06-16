/* ---------------- Common Functions and variables to use in various classes ---------------- */

import java.util.Random;

public class Common {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void generateAscending(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
    }

    public static void generateDescending(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
    }

    public static void generateRandomly(int[] arr) {
        int n = arr.length;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt();
        }
    }
}