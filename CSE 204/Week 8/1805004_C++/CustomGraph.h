#ifndef INC_1805004_C___CUSTOMGRAPH_H
#define INC_1805004_C___CUSTOMGRAPH_H

#include <iostream>
#include <vector>

using namespace std;

class CustomGraph {
    int totalVertices;
    vector<vector<int>> adjacencyList;

    bool checkInvalidVertex(int v) const;

public:
    explicit CustomGraph(int totalVertices);
    void edgeAddition(int vertex, int other);
    int getTotalVertices() const;
    vector<int> adjacent(int vertex);
};

CustomGraph::CustomGraph(int totalVertices){
    this->totalVertices = totalVertices;
    for (int i = 0; i < totalVertices; i++) {
        vector<int> v;
        adjacencyList.push_back(v);
    }
}

bool CustomGraph::checkInvalidVertex(int v) const {
    return v < 0 || v >= totalVertices;
}

void CustomGraph::edgeAddition(int vertex, int other) {
    if (checkInvalidVertex(vertex) || checkInvalidVertex(other)) {
        cerr << "Invalid Vertex" << endl;
        return;
    }
    adjacencyList[vertex].push_back(other);
    adjacencyList[other].push_back(vertex);
}

int CustomGraph::getTotalVertices() const {
    return totalVertices;
}

vector<int> CustomGraph::adjacent(int vertex) {
    return adjacencyList[vertex];
}

#endif //INC_1805004_C___CUSTOMGRAPH_H