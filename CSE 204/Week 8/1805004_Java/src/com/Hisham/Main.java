package com.Hisham;

/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE'18 Section A1
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int C, R, L, F;
        String[] splits = scanner.nextLine().split(" ");
        if (splits.length != 4) {
            System.err.println("Input Format Not Supported");
            return;
        }
        try {
            C = Integer.parseInt(splits[0]);
            R = Integer.parseInt(splits[1]);
            L = Integer.parseInt(splits[2]);
            F = Integer.parseInt(splits[3]);
            if (C > 1000 || R <= 1 || R > C * (C - 1) / 2 || L <= 1 || L > C || F <= 1 || F > 100) {
                System.err.println("Invalid Input");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return;
        }

        MissionController controller = new MissionController(C, F);

        for (int i = 0; i < R; i++) {
            splits = scanner.nextLine().split(" ");
            try {
                controller.addEdge(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
                return;
            }
        }

        for (int i = 0; i < L; i++) {
            splits = scanner.nextLine().split(" ");
            try {
                controller.addLocation(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
                return;
            }
        }

        for (int i = 0; i < F; i++) {
            splits = scanner.nextLine().split(" ");
            try {
                controller.addFriend(Integer.parseInt(splits[0]), Integer.parseInt(splits[1]));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
                return;
            }
        }

//        String s = controller.solveByDFS();
        String s = controller.solveByBFS();
        System.out.println(s);
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}