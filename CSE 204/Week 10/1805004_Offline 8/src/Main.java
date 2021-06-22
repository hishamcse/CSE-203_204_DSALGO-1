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
        int n = Integer.parseInt(scanner.nextLine());
        if (n < 3 || n > 10e5) {
            System.err.println("Out of constraints!! Number is not valid");
            return;
        }

        House[] houses = new House[n];
        for (int i = 0; i < n; i++) {
            String[] str = scanner.nextLine().split(" ");
            houses[i] = new House(i, Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        SecondNearestFinder finder = new SecondNearestFinder(houses);
        int[] second_nearest_indices = finder.secondNearestHouses();
        System.out.println(second_nearest_indices[0] + " " + second_nearest_indices[1]);
        System.out.printf("%.4f", finder.getSecondNearestDist());
        scanner.close();
    }
}