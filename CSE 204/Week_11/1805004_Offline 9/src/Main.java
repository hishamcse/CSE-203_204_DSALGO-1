/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE'18 Section A1
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalPlants = scanner.nextInt();
        int totalFriends = scanner.nextInt();

        int[] original_prices = new int[totalPlants];
        for (int i = 0; i < totalPlants; i++) {
            original_prices[i] = scanner.nextInt();
        }
        Arrays.sort(original_prices);

        long totalCost = 0, j = 0;
        for (int i = totalPlants - 1; i >= 0; i--) {
            totalCost += ((j / totalFriends) + 1) * original_prices[i];
            j++;
        }
        System.out.println(totalCost);
    }
}