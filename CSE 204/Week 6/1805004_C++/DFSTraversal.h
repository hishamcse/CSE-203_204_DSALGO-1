#ifndef INC_1805004_C___DFSTRAVERSAL_H
#define INC_1805004_C___DFSTRAVERSAL_H

#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include "CustomGraph.h"
#include "Location.h"
#include "Friend.h"

using namespace std;

class DFSTraversal {
    bool *traversed;
    int *collectedIndividual;
    int totalCollected;
    int pieces;

    void dfs(CustomGraph &graph, vector<Location> &locations, int vertex);
    static int hiddenPieces(vector<Location> &locations, int city);

public:
    DFSTraversal(CustomGraph &graph, vector<Location> &locations, vector<Friend> &friends);
    int getTotalCollected() const;
    int getCollectedIndividual(int friendId);
    virtual ~DFSTraversal();
};

DFSTraversal::DFSTraversal(CustomGraph &graph, vector<Location> &locations, vector<Friend> &friends) {
    int vertices = graph.getTotalVertices();
    int noOfFriends = friends.size();
    traversed = new bool[vertices];
    for (int i = 0; i < vertices; i++) {
        traversed[i] = false;
    }
    collectedIndividual = new int[noOfFriends];
    for (int i = 0; i < noOfFriends; i++) {
        collectedIndividual[i] = 0;
    }
    totalCollected = 0;
    pieces = 0;

    sort(friends.begin(), friends.end());
    for (Friend &f:friends) {
        if (!traversed[f.startCity]) {
            dfs(graph, locations, f.startCity);
            collectedIndividual[f.id] += pieces;
            pieces = 0;
        }
    }
}

void DFSTraversal::dfs(CustomGraph &graph, vector<Location> &locations, int vertex) {
    traversed[vertex] = true;
    int n = hiddenPieces(locations, vertex);
    totalCollected += n;
    pieces += n;
    for (int v : graph.adjacent(vertex)) {
        if (!traversed[v]) {
            dfs(graph, locations, v);
        }
    }
}

int DFSTraversal::hiddenPieces(vector<Location> &locations, int city) {
    for (Location &location:locations) {
        if (location.city == city) {
            return location.noOfPieces;
        }
    }
    return 0;
}

int DFSTraversal::getTotalCollected() const {
    return totalCollected;
}

int DFSTraversal::getCollectedIndividual(int friendId) {
    return collectedIndividual[friendId];
}

DFSTraversal::~DFSTraversal() {
    delete traversed;
    delete collectedIndividual;
}

#endif //INC_1805004_C___DFSTRAVERSAL_H