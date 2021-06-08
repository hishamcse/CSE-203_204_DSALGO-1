package com.Hisham;

import java.util.Collections;
import java.util.List;

public class DFSTraversal {

    private final boolean[] traversed;
    private final int[] collectedIndividual;
    private int totalCollected;
    private int pieces;

    public DFSTraversal(CustomGraph graph, List<Location> locations, List<Friend> friends) {
        int vertices = graph.totalVertices();
        int noOfFriends = friends.size();
        traversed = new boolean[vertices];
        collectedIndividual = new int[noOfFriends];
        totalCollected = 0;
        pieces = 0;

        Collections.sort(friends);            // as the traversal will be in ascending order
        for (Friend friend : friends) {
            if (!traversed[friend.startCity]) {
                dfs(graph, locations, friend.startCity);
                collectedIndividual[friend.id] += pieces;
                pieces = 0;
            }
        }
    }

    private void dfs(CustomGraph graph, List<Location> locations, int vertex) {
        traversed[vertex] = true;
        int n = hiddenPieces(locations, vertex);
        totalCollected += n;
        pieces += n;
        for (int v : graph.adjacencyList(vertex)) {
            if (!traversed[v]) {
                dfs(graph, locations, v);
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