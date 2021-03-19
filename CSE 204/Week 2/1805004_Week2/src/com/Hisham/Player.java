package com.Hisham;

public class Player {

    private final int id;
    private final int reflexTime;

    public Player(int id, int reflexTime) {
        this.id = id;
        this.reflexTime = reflexTime;
    }

    public int getId() {
        return id;
    }

    public int getReflexTime() {
        return reflexTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            throw new IllegalArgumentException("Invalid equals operation");
        }
        Player o = (Player) obj;
        return this.id == o.id;
    }

    @Override
    public String toString() {
        return "Player " + id;
    }
}