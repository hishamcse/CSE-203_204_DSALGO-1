/* ------------------ Steps to follow for report generation -------------- */

import java.time.Instant;

public class ReportGeneration {

    private static final int TOTAL_ITERATION = 10;
    private static final int START_VALUE_n = 10;
    private static final int END_VALUE_n = 1000000;

    private final CustomMergeSort mergeSort = new CustomMergeSort();
    private final CustomQuickSort quickSort = new CustomQuickSort();

    private void operation_mergeSort(int[] arr) {
        double total_time = 0, start_time, end_time;
        for (int i = 0; i < TOTAL_ITERATION; i++) {
            start_time = Instant.now().toEpochMilli();
            mergeSort.sort(arr.clone());
            end_time = Instant.now().toEpochMilli();
            total_time += (end_time - start_time);
        }
        double avg_time = total_time / (double) (TOTAL_ITERATION);
        System.out.println(Common.ANSI_RESET + "for n = " + arr.length + ", " +
                "Time spent to accomplish merge sort: " + avg_time + " ms");
    }

    private void operation_quickSort(int[] arr) {
        double total_time = 0, start_time, end_time;
        for (int i = 0; i < TOTAL_ITERATION; i++) {
            start_time = Instant.now().toEpochMilli();
            quickSort.sort(arr.clone());
            end_time = Instant.now().toEpochMilli();
            total_time += (end_time - start_time);
        }
        double avg_time = total_time / (double) (TOTAL_ITERATION);
        System.out.println(Common.ANSI_RESET + "for n = " + arr.length + ", " +
                "Time spent to accomplish Quick sort: " + avg_time + " ms");
    }

    public void run() {
        for (int n = START_VALUE_n; n <= END_VALUE_n; n *= 10) {
            int[] ascending_Arr = new int[n];
            int[] descending_Arr = new int[n];
            int[] random_Arr = new int[n];

            Common.generateAscending(ascending_Arr);
            Common.generateDescending(descending_Arr);
            Common.generateRandomly(random_Arr);

            System.out.println();
            System.out.println(Common.ANSI_CYAN + "Generating result for Ascending Order Numbers:" + '\n');
            operation_mergeSort(ascending_Arr);
            operation_quickSort(ascending_Arr);

            System.out.println();
            System.out.println(Common.ANSI_CYAN + "Generating result for Descending Order Numbers:" + '\n');
            operation_mergeSort(descending_Arr);
            operation_quickSort(descending_Arr);

            System.out.println();
            System.out.println(Common.ANSI_CYAN + "Generating result for Random Order Numbers:" + '\n');
            operation_mergeSort(random_Arr);
            operation_quickSort(random_Arr);
        }
    }
}