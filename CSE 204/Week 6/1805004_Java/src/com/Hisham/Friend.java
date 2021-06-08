package com.Hisham;

public class Friend implements Comparable<Friend> {

    public int id;
    public int startCity;

    public Friend(int id, int startCity) {
        this.id = id;
        this.startCity = startCity;
    }

    @Override
    public int compareTo(Friend o) {
        return Integer.compare(this.id, o.id);
    }
}