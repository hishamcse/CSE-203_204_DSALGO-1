import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestGeneration {

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        FileWriter writer = new FileWriter("./test cases//Test 6.txt");
        int n = 100;
        int s = 10000;
        sb.append(n).append(" ").append(s).append('\n');

        int[] faces = new int[n + 1];
        for (int i = 0; i < n; i++) {
            faces[i + 1] = Math.abs(random.nextInt((int) 1e7)) + 1;
            sb.append(faces[i + 1]).append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        writer.write(sb.toString());
        writer.close();

        WaysCounter counter = new WaysCounter();
        System.out.println(counter.diceSumCounter(n, s, faces));
    }
}