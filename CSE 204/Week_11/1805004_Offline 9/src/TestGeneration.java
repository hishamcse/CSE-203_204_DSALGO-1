import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class TestGeneration {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int n = 1 + random.nextInt(100);
        int k = 1 + random.nextInt(n);
        sb.append(n).append(" ").append(k).append('\n');

        int[] original_prices = new int[n];
        for (int i = 0; i < n; i++) {
            original_prices[i] = 1 + random.nextInt((int) 10e6);
            sb.append(original_prices[i]).append(' ');
        }
        Arrays.sort(original_prices);

        long totalCost = 0, j = 0;
        for (int i = n - 1; i >= 0; i--) {
            totalCost += ((j / k) + 1) * original_prices[i];
            j++;
        }
        System.out.println(totalCost);

        sb.deleteCharAt(sb.length() - 1);
        FileWriter writer = new FileWriter("test 5.txt");
        writer.write(sb.toString());
        writer.close();
    }
}