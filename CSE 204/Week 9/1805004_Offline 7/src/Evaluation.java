/* ------------------- Steps to follow during offline evaluation ----------------- */

import java.time.Instant;
import java.util.Scanner;

public class Evaluation {

    private static final Scanner scanner = new Scanner(System.in);
    private final CustomMergeSort mergeSort = new CustomMergeSort();
    private final CustomQuickSort quickSort = new CustomQuickSort();

    private String orderInstruction() {
        return '\n' + Common.ANSI_GREEN + "Press q to exit" + '\n' + '\n'
                + "Enter any one number between 1 and 3 to define order:" + '\n' +
                "1. Ascending" + '\n' + "2. Descending" + '\n' + "3. Random Order";
    }

    private void operation_Sort(int[] arr, String type) {
        double start_time = Instant.now().toEpochMilli();
        if (type.equals("merge sort")) {
            mergeSort.sort(arr);
        } else {
            quickSort.sort(arr);
        }
        double end_time = Instant.now().toEpochMilli();
        System.out.println(Common.ANSI_CYAN + "Time passed during " + type + " is: " + (end_time - start_time) + " ms");
    }

    private void print_sorted_arrays(int[] merge_Arr, int[] quick_Arr) {
        System.out.println();
        System.out.println(Common.ANSI_RESET + "Merge Sorted" + " | " + "Quick Sorted");
        for (int i = 0; i < merge_Arr.length; i++) {
            System.out.printf("%8d     | %6d\n", merge_Arr[i], quick_Arr[i]);
        }
    }

    public void run() {
        while (true) {
            System.out.println(orderInstruction());

            // taking user input
            int order_id = 0, n;
            String str = scanner.next();
            try {
                order_id = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                if (str.equals("q")) break;
            }
            if (order_id < 1 || order_id > 3) {
                System.err.println("Please define number between 1 and 3");
                continue;
            }
            System.out.println(Common.ANSI_GREEN + "Enter how many numbers to generate:");
            str = scanner.next();
            try {
                n = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                if (str.equals("q")) break;
                else System.err.println("Please enter a valid number");
                continue;
            }

            if (n < 1) {
                System.err.println("Please define number greater than 0");
                continue;
            }

            // generating numbers according to the order
            int[] numbers = new int[n];
            if (order_id == 1) Common.generateAscending(numbers);
            else if (order_id == 2) Common.generateDescending(numbers);
            else Common.generateRandomly(numbers);

            // sorting and time recording
            int[] numbers_for_merge_sort = numbers.clone();
            int[] numbers_for_quick_sort = numbers.clone();

            System.out.println();
            operation_Sort(numbers_for_merge_sort, "merge sort");
            operation_Sort(numbers_for_quick_sort, "quick sort");

            // printing sorted arrays
            print_sorted_arrays(numbers_for_merge_sort, numbers_for_quick_sort);
        }
    }
}