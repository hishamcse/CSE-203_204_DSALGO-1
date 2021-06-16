/*
 * Implementation of user defined Max Binary Heap
 *
 * Created on Sunday April 4 12:12:41 2021
 * @author: Syed Jarullah Hisham
            Roll: 1805004
            CSE '18 Section A1
 */

#ifndef HEAP_H
#define HEAP_H

#include<string>
#include<vector>
using namespace std;

class Heap {
    int *customPQ;
    int noOfNodes;
    int maxCapacity;

    void arrayResizer(int newCapacity);
    void swap(int i, int j);
    void bottomUpHeapify(int index);
    void topDownHeapify(int index);

public:
    explicit Heap(int capacity);
    int size() const;
    void insert(int item);
    int getMax();
    void deleteKey();
    ~Heap();
};

// constructor
Heap::Heap(int capacity) {
    customPQ = new int[capacity + 1];
    noOfNodes = 0;
    maxCapacity = capacity;
}

// private helper functions
void Heap::arrayResizer(int newCapacity) {
    int *temp = new int[newCapacity + 1];
    for (int i = 1; i <= noOfNodes; i++) {
        temp[i] = customPQ[i];
    }
    customPQ = temp;
}

void Heap::swap(int i, int j) {
    int temp = customPQ[i];
    customPQ[i] = customPQ[j];
    customPQ[j] = temp;
}

void Heap::bottomUpHeapify(int index) {
    while (index > 1) {
        if (customPQ[index / 2] >= customPQ[index]) break;
        swap(index / 2, index);
        index /= 2;
    }
}

void Heap::topDownHeapify(int index) {
    while (2 * index <= noOfNodes) {
        int childId = 2 * index;
        if (childId < noOfNodes && customPQ[childId] < customPQ[childId + 1]) childId++;
        if (customPQ[index] >= customPQ[childId]) break;
        swap(index, childId);
        index = childId;
    }
}

// public functions
int Heap::size() const {
    return noOfNodes;
}

void Heap::insert(int item) {
    if (noOfNodes == maxCapacity) {
        maxCapacity *= 2;
        arrayResizer(maxCapacity);
    }
    customPQ[++noOfNodes] = item;               // as it will be 1 indexed. 0 index will be empty
    bottomUpHeapify(noOfNodes);
}

int Heap::getMax() {
    if (noOfNodes == 0) {
        cerr << "Empty Binary Heap!! Please Try again Later" << endl;
        return -1;
    }
    return customPQ[1];
}

void Heap::deleteKey() {
    if (noOfNodes == 0) {
        cerr << "Empty Binary Heap!! Please Try again Later" << endl;
        return;
    }
    swap(1, noOfNodes--);
    topDownHeapify(1);
    if (noOfNodes == maxCapacity / 4) {
        maxCapacity /= 2;
        arrayResizer(maxCapacity);
    }
}

Heap::~Heap() {
    delete customPQ;
}

void heapsort(vector<int> &v) {
    int len = v.size();
    Heap heap(len);
    for (int i = 0; i < len; i++) {
        heap.insert(v.at(i));
    }
    for (int i = 0; i < len; i++) {
        v[i] = heap.getMax();
        heap.deleteKey();
    }
}

#endif      //HEAP_H
