/*
 * Created By:
 * Syed Jarullah Hisham
 * Roll: 1805004
 * CSE'18 Section A1
 */

#include <iostream>
#include "Location.h"
#include "Friend.h"
#include "CustomGraph.h"
#include "DFSTraversal.h"
#include "BFSTraversal.h"
#include "MissionController.h"

using namespace std;

int main() {
    int C, R, L, F, first, second;
    cin >> C >> R >> L >> F;
    if (C > 1000 || R <= 1 || R > C * (C - 1) / 2 || L <= 1 || L > C || F <= 1 || F > 100) {
        throw exception("Invalid Input");
    }

    MissionController controller(C, F);
    for (int i = 0; i < R; i++) {
        cin >> first >> second;
        controller.addEdge(first, second);
    }
    for (int i = 0; i < L; i++) {
        cin >> first >> second;
        controller.addLocation(first, second);
    }
    for (int i = 0; i < F; i++) {
        cin >> first >> second;
        controller.addFriend(first, second);
    }

    cout << controller.solveByDFS() << endl;
    cout << controller.solveByBFS() << endl;
}