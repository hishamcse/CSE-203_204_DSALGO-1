/* ---- 2D coordinate Point Implementation for house ---- */

public class House implements Comparable<House> {

    private final int house_ID, coord_X, coord_Y;

    public House(int house_ID, int coord_X, int coord_Y) {
        this.house_ID = house_ID;
        this.coord_X = coord_X;
        this.coord_Y = coord_Y;
    }

    public int house_ID() {
        return house_ID;
    }

    public int coord_X() {
        return coord_X;
    }

    public int coord_Y() {
        return coord_Y;
    }

    public static double euclideanDistance(House house1, House house2) {
        double dist_X = Math.abs(house2.coord_X - house1.coord_X);
        double dist_Y = Math.abs(house2.coord_Y - house1.coord_Y);
        return Math.hypot(dist_X, dist_Y);
    }

    public int compareTo(House house) {
        if (this.coord_Y < house.coord_Y) return -1;
        if (this.coord_Y > house.coord_Y) return +1;
        return Integer.compare(this.coord_X, house.coord_X);
    }

    public boolean equalTo(House house) {
        return this.house_ID == house.house_ID || (this.coord_X == house.coord_X && this.coord_Y == house.coord_Y);
    }
}