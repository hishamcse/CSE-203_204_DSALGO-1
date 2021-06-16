#ifndef INC_1805004_C___BFSTRAVERSAL_H
#define INC_1805004_C___BFSTRAVERSAL_H

#include <iostream>
#include <utility>
#include <vector>
#include <queue>
#include <algorithm>
#include "CustomGraph.h"
#include "Location.h"
#include "Friend.h"

using namespace std;

class BFSTraversal {
    bool *traversed;
    int *collectedIndividual;
    int totalCollected;
    int pieces;

    void bfs(CustomGraph *graph, vector<Location> &locations, int city);
    static int hiddenPieces(vector<Location> &locations, int city);

public:
    BFSTraversal(CustomGraph *graph, vector<Location> &locations, vector<Friend> &friends);
    int getTotalCollected() const;
    int getCollectedIndividual(int friendId);
    virtual ~BFSTraversal();
};

BFSTraversal::BFSTraversal(CustomGraph *graph, vector<Location> &locations, vector<Friend> &friends) {
    int vertices = graph->getTotalVertices();
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
            bfs(graph, locations, f.startCity);
            collectedIndividual[f.id] += pieces;
            pieces = 0;
        }
    }
}

void BFSTraversal::bfs(CustomGraph *graph, vector<Location> &locations, int city) {
    queue<int> queue;
    queue.push(city);
    if (!traversed[city]) {
        int n = hiddenPieces(locations, city);
        totalCollected += n;
        pieces += n;
        traversed[city] = true;
    }

    while (!queue.empty()) {
        int vertex = queue.front();
        queue.pop();
        for (int v : graph->adjacent(vertex)) {
            if (!traversed[v]) {
                int n = hiddenPieces(locations, v);
                totalCollected += n;
                pieces += n;
                traversed[v] = true;
                queue.push(v);
            }
        }
    }
}

int BFSTraversal::hiddenPieces(vector<Location> &locations, int city) {
    for (Location &location:locations) {
        if (location.city == city) {
            return location.noOfPieces;
        }
    }
    return 0;
}

int BFSTraversal::getTotalCollected() const {
    return totalCollected;
}

int BFSTraversal::getCollectedIndividual(int friendId) {
    return collectedIndividual[friendId];
}

BFSTraversal::~BFSTraversal() {
    delete collectedIndividual;
    delete traversed;
}

#endif //INC_1805004_C___BFSTRAVERSAL_H