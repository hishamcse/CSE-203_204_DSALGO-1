/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE'18 Section A1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Test.txt");
        Scanner scanner = new Scanner(file);
        String[] str = scanner.nextLine().split(" ");
        int noOfDices = Integer.parseInt(str[0]);
        int sum = Integer.parseInt(str[1]);

        int[] faces = new int[noOfDices + 1];
        str = scanner.nextLine().split(" ");
        for (int i = 0; i < noOfDices; i++) {
            faces[i + 1] = Integer.parseInt(str[i]);
        }

        WaysCounter counter = new WaysCounter();
        System.out.println(counter.diceSumCounter(noOfDices, sum, faces));
        scanner.close();
    }
}