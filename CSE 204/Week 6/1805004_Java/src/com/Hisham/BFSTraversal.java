package com.Hisham;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSTraversal {

    private final boolean[] traversed;
    private final int[] collectedIndividual;
    private int totalCollected;
    private int pieces;

    public BFSTraversal(CustomGraph graph, List<Location> locations, List<Friend> friends) {
        int vertices = graph.totalVertices();
        int noOfFriends = friends.size();
        traversed = new boolean[vertices];
        collectedIndividual = new int[noOfFriends];
        totalCollected = 0;
        pieces = 0;

        Collections.sort(friends);              // as the traversal will be in ascending order
        for (Friend friend : friends) {
            if (!traversed[friend.startCity]) {
                bfs(graph, locations, friend.startCity);
                collectedIndividual[friend.id] += pieces;
                pieces = 0;
            }
        }
    }

    private void bfs(CustomGraph graph, List<Location> locations, int city) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(city);
        if (!traversed[city]) {
            int n = hiddenPieces(locations, city);
            totalCollected += n;
            pieces += n;
            traversed[city] = true;
        }

        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            for (int v : graph.adjacencyList(vertex)) {
                if (!traversed[v]) {
                    int n = hiddenPieces(locations, v);
                    totalCollected += n;
                    pieces += n;
                    traversed[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    private int hiddenPieces(List<Location> locations, int city) {
        for (Location location : locations) {
            if (location.city == city) {
                return location.noOfPieces;
            }
        }
        return 0;
    }

    public int totalCollected() {
        return totalCollected;
    }

    public int collectedIndividual(int friendId) {
        return collectedIndividual[friendId];
    }
}