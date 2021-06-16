#ifndef INC_1805004_C___MISSIONCONTROLLER_H
#define INC_1805004_C___MISSIONCONTROLLER_H

#include <iostream>
#include <vector>
#include <string>

using namespace std;

class MissionController {
    CustomGraph *graph;
    vector<Location> locations;
    vector<Friend> friends;
    int noOfFriends;
    int noOfTotalPieces;

    bool checkInvalidCity(int city);
    bool checkInvalidFriend(int friendId) const;

public:
    MissionController(int vertices, int noOfFriends);
    void addEdge(int vertex, int other);
    void addLocation(int city, int noOfPieces);
    void addFriend(int city, int friendId);
    string solveByDFS();
    string solveByBFS();
};

MissionController::MissionController(int vertices, int noOfFriends){
    this->noOfFriends = noOfFriends;
    graph = new CustomGraph(vertices);
    noOfTotalPieces = 0;
}

bool MissionController::checkInvalidCity(int city) {
    return city < 0 || city >= graph->getTotalVertices();
}

bool MissionController::checkInvalidFriend(int friendId) const {
    return friendId < 0 || friendId > noOfFriends;
}

void MissionController::addEdge(int vertex, int other) {
    graph->edgeAddition(vertex, other);
}

void MissionController::addLocation(int city, int noOfPieces) {
    if (checkInvalidCity(city)) {
        throw exception("Invalid City");
    }
    Location location(city, noOfPieces);
    locations.push_back(location);
    noOfTotalPieces += noOfPieces;
}

void MissionController::addFriend(int city, int friendId) {
    if (checkInvalidCity(city) || checkInvalidFriend(friendId)) {
        throw exception("Invalid City or Invalid Friend");
    }
    Friend f(friendId, city);
    friends.push_back(f);
}

string MissionController::solveByDFS() {
    DFSTraversal dfs(*graph, locations, friends);
    int totalCollected = dfs.getTotalCollected();
    string sb;
    sb.append(totalCollected == noOfTotalPieces ? "Mission Accomplished" : "Mission Impossible").append("\n");
    sb.append(to_string(totalCollected)).append(" out of ").append(to_string(noOfTotalPieces)).append(
            " pieces are collected").append("\n");
    for (Friend &f : friends) {
        sb.append(to_string(f.id)).append(" collected ").append(to_string(dfs.getCollectedIndividual(f.id))).append(
                " pieces").append("\n");
    }
    return sb;
}

string MissionController::solveByBFS() {
    BFSTraversal bfs(graph, locations, friends);
    int totalCollected = bfs.getTotalCollected();
    string sb;
    sb.append(totalCollected == noOfTotalPieces ? "Mission Accomplished" : "Mission Impossible").append("\n");
    sb.append(to_string(totalCollected)).append(" out of ").append(to_string(noOfTotalPieces)).append(
            " pieces are collected").append("\n");
    for (Friend &f : friends) {
        sb.append(to_string(f.id)).append(" collected ").append(to_string(bfs.getCollectedIndividual(f.id))).append(
                " pieces").append("\n");
    }
    return sb;
}

#endif //INC_1805004_C___MISSIONCONTROLLER_H