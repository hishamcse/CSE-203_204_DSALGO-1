#ifndef INC_1805004_C___FRIEND_H
#define INC_1805004_C___FRIEND_H

class Friend {
public:
    int id;
    int startCity;

    Friend(int id, int startCity)
            : id(id), startCity(startCity) {}

    bool operator<(const Friend &f) const {
        return id < f.id;
    }
};

#endif //INC_1805004_C___FRIEND_H