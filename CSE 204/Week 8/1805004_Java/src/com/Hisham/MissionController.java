package com.Hisham;

/*
 * Controller for searching mission
 */

import java.util.ArrayList;
import java.util.List;

public class MissionController {

    private final CustomGraph graph;
    private final List<Location> locations;
    private final List<Friend> friends;
    private final int noOfFriends;
    private int noOfTotalPieces;

    public MissionController(int vertices, int noOfFriends) {
        graph = new CustomGraph(vertices);
        locations = new ArrayList<>();
        friends = new ArrayList<>();
        this.noOfFriends = noOfFriends;
    }

    private boolean checkInvalidCity(int city) {
        return city < 0 || city >= graph.totalVertices();
    }

    private boolean checkInvalidFriend(int friend) {
        return friend < 0 || friend > noOfFriends;
    }

    public void addEdge(int vertex, int other) {
        graph.edgeAddition(vertex, other);
    }

    public void addLocation(int city, int noOfPieces) {
        if (checkInvalidCity(city)) {
            throw new IllegalArgumentException("Invalid City");
        }
        locations.add(new Location(city, noOfPieces));
        noOfTotalPieces += noOfPieces;
    }

    public void addFriend(int city, int friend) {
        if (checkInvalidCity(city) || checkInvalidFriend(friend)) {
            throw new IllegalArgumentException("Invalid City or Invalid Friend");
        }
        friends.add(new Friend(friend, city));
    }

    public String solveByDFS() {
        DFSTraversal dfs = new DFSTraversal(graph, locations, friends);
        int totalCollected = dfs.totalCollected();
        StringBuilder sb = new StringBuilder();
        sb.append(totalCollected == noOfTotalPieces ? "Mission Accomplished" : "Mission Impossible").append("\n");
        sb.append(totalCollected).append(" out of ").append(noOfTotalPieces).append(" pieces are collected").append("\n");
        for (Friend friend : friends) {
            sb.append(friend.id).append(" collected ").append(dfs.collectedIndividual(friend.id)).append(" pieces").append("\n");
        }
        return sb.toString();
    }

    public String solveByBFS() {
        BFSTraversal bfs = new BFSTraversal(graph, locations, friends);
        int totalCollected = bfs.totalCollected();
        StringBuilder sb = new StringBuilder();
        sb.append(totalCollected == noOfTotalPieces ? "Mission Accomplished" : "Mission Impossible").append("\n");
        sb.append(totalCollected).append(" out of ").append(noOfTotalPieces).append(" pieces are collected").append("\n");
        for (Friend friend : friends) {
            sb.append(friend.id).append(" collected ").append(bfs.collectedIndividual(friend.id)).append(" pieces").append("\n");
        }
        return sb.toString();
    }
}