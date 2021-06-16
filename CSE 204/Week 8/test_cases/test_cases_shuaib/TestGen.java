import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestGen {
    public static void main(String[] args) throws IOException {
        Random random = new Random(1);
        int c = 999;
        int connections = 500;
        int l_ = 999;
        int l = 0;
        int f = 15;
        int r = 0;
        int maxPiece=50;
        boolean[][] roads = new boolean[c][c];
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/tests.txt"));
        for (int i = 0; i < connections; i++) {
            int x = random.nextInt(c);
            int y = random.nextInt(c);
            if (!roads[x][y]) {
                roads[x][y] = true;
                roads[y][x] = true;
                bw.write(String.format("%d %d\n", x, y));
                r++;
            }
        }
        ArrayList<Integer> ara = new ArrayList<>();
        for (int i = 0; i < l_; i++) {
            int x = random.nextInt(c);
            if (!ara.contains(x)) {
                ara.add(x);
                bw.write(String.format("%d %d\n", x, 1 + random.nextInt(maxPiece)));
                l++;
            }
        }
        ara.clear();
        for (int i = 0; i < f; i++) {
            int x=random.nextInt(c);
            bw.write(String.format("%d %d\n", x, i));
        }
        bw.close();
        System.out.printf("%d %d %d %d\n", c, r, l, f);
    }
}
