/* ------------ Class for test case generation ---------- */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestGeneration {

    private static final int HIGHEST_N = 100000;
    private static final int LOW_BOUND = -10000;
    private static final int HIGH_BOUND = 10000;

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        FileWriter writer = new FileWriter("./test cases/input_test 16.txt");
        StringBuilder sb = new StringBuilder();
        int n = Math.abs(random.nextInt(HIGHEST_N - 2) + 3);
//        int n = 10124;
        System.out.println(n);
        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            int a = random.nextInt(HIGH_BOUND - LOW_BOUND) + LOW_BOUND;
            int b = random.nextInt(HIGH_BOUND - LOW_BOUND) + LOW_BOUND;
            sb.append(a).append(" ").append(b).append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        writer.write(sb.toString());
        writer.close();
    }
}