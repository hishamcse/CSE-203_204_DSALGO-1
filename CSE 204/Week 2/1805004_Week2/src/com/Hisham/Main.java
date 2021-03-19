package com.Hisham;

/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE' 18 SECTION A1
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total_players = scanner.nextInt();
        int[] reflexTimes = new int[total_players];
        for (int i = 0; i < total_players; i++) {
            reflexTimes[i] = scanner.nextInt();
        }
        scanner.nextLine();

        GameController game = new GameController(reflexTimes);
        int prevTime = 0;
        while (true) {
            String s = scanner.nextLine();
            String[] strings = s.split(" ");
            int time = Integer.parseInt(strings[0]);
            if (time < prevTime) {
                System.err.println("Invalid Input");         // not allowing decreasing value
                continue;
            }
            int flag = 0;
            switch (strings[1]) {
                case "M" -> game.stop_music(time);
                case "R" -> game.toggleDirection(time);
                case "I" -> game.add_player(time, Integer.parseInt(strings[2]));
                case "P" -> game.print_Current_Player(time);
                case "F" -> {
                    game.finish(time);
                    flag = 1;
                }
                default -> System.err.println("Warning!! Invalid Operation Requested");
            }
            if (flag == 1) break;
            prevTime = time;
        }
    }
}