import java.time.Instant;
import java.util.Arrays;
import java.util.Random;


public class TestCode {

    public static void main(String[] args) {
        Random random = new Random();
        int[] a = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            a[i] = random.nextInt();
        }

        int[] c = new int[1000000];
        System.arraycopy(a, 0, c, 0, a.length);
        Arrays.sort(a);
//        CustomMergeSort s = new CustomMergeSort();
        CustomQuickSort s = new CustomQuickSort();
        double t1 = Instant.now().toEpochMilli();
        s.sort(c);
        double t2 = Instant.now().toEpochMilli();
        System.out.println(t2-t1);
        for (int i = 0; i < 1000000; i++) {
            if (a[i] != c[i]) {
                System.err.println(a[i] + " " + c[i]);
            }
        }
    }
}