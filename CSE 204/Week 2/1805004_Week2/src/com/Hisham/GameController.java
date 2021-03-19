package com.Hisham;

/*
 * Controller of the whole game and different operations of it
 */

public class GameController {

    CustomLinkedList<Player> players_List;
    private boolean reverseTraverse;      // traversing in reverse direction
    private boolean endGame;              // winner is found
    private int prevTime;                 // time when current player gets pillow
    private int last_player_id;           // controlling the id's of new players

    public GameController(int[] reflexTimes) {
        players_List = new CustomLinkedList<>();

        int n = reflexTimes.length;
        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {
            players[i] = new Player(i + 1, reflexTimes[i]);
        }
        players_List.insertAll(players);

        reverseTraverse = false;
        endGame = false;
        prevTime = 0;
        last_player_id = n;
    }

    // private helper method
    private Player find_Current_Player(int time) {          // at time, which player has the pillow
        Player requested = null;
        if (!endGame) {
            if (time - prevTime <= players_List.getCurrent().getReflexTime()) {
                requested = players_List.getCurrent();
            } else {
                prevTime = prevTime + players_List.getCurrent().getReflexTime();
                while (true) {
                    Player player = !reverseTraverse ? players_List.getNext() : players_List.getPrev();
                    if (time - prevTime <= player.getReflexTime()) {
                        requested = player;
                        break;
                    } else {
                        prevTime = prevTime + player.getReflexTime();
                    }
                }
            }
        }
        return requested;
    }

    // public methods
    public void stop_music(int time) {         // M operation
        if (!endGame) {
            Player eliminated = find_Current_Player(time);
            players_List.setPointer(reverseTraverse);
            prevTime = time;
            players_List.remove(eliminated);
            System.out.println(eliminated + " has been eliminated at t=" + time);

            if (players_List.getLength() == 1) {
                endGame = true;
            }
        }
    }

    public void toggleDirection(int time) {       // R operation
        if (!endGame) {
            find_Current_Player(time);
            reverseTraverse = !reverseTraverse;
        }
    }

    public void add_player(int time, int reflexTime) {     // I operation
        if (!endGame) {
            find_Current_Player(time);
            last_player_id++;
            Player newPlayer = new Player(last_player_id, reflexTime);
            if (reverseTraverse) {
                players_List.insertAfter(players_List.getCurrent(), newPlayer);
            } else {
                players_List.insertBefore(players_List.getCurrent(), newPlayer);
            }
        }
    }

    public void print_Current_Player(int time) {          // P operation
        if (!endGame) {
            System.out.println(find_Current_Player(time) + " is holding the pillow at t=" + time);
        }
    }

    public void finish(int time) {                        // F operation
        if (endGame) {
            System.out.println("Game over : " + players_List.getCurrent() + " wins!!");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Game over: ");
            sb.append(find_Current_Player(time)).append(" is holding the pillow at t=").append(time);
            sb.append(", pillow passing sequence = Player ");
            sb.append(players_List.getCurrent().getId()).append(", ");
            int i = 0;
            if (!reverseTraverse) {
                while (i < players_List.getLength() - 1) {
                    sb.append(players_List.getNext().getId()).append(", ");
                    i++;
                }
            } else {
                while (i < players_List.getLength() - 1) {
                    sb.append(players_List.getPrev().getId()).append(", ");
                    i++;
                }
            }
            sb.delete(sb.length()-2,sb.length()-1);
            System.out.println(sb.toString());
        }
    }
}