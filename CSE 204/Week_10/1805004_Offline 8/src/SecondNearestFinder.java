/* --------------- Implementation of the algorithm to find the second nearest pair  ----------------- */

import java.util.*;

public class SecondNearestFinder {

    private double tempNearestDist = Double.POSITIVE_INFINITY;
    private double nearestDist = Double.POSITIVE_INFINITY, secondNearestDist;
    private House tempNearestH1, tempNearestH2, secondNearestH1, secondNearestH2;

    public SecondNearestFinder(House[] houses) {
        int length = houses.length;
        House[] houses_sorted_By_X = new House[length];
        House[] houses_sorted_By_Y = new House[length];

        System.arraycopy(houses, 0, houses_sorted_By_X, 0, length);

        Arrays.sort(houses_sorted_By_X, Comparator.comparingInt(House::coord_Y));       // sorted by Y coordinate
        Arrays.sort(houses_sorted_By_X, Comparator.comparingInt(House::coord_X));       // sorted by X coordinate

        System.arraycopy(houses_sorted_By_X, 0, houses_sorted_By_Y, 0, length);

        if (length == 3) {
            secondNearest_For_three(houses_sorted_By_X);
            return;
        }

        House[] copy_Arr = new House[length];
        nearest(houses_sorted_By_X.clone(), houses_sorted_By_Y.clone(), copy_Arr, 0, length - 1);

        nearestDist = tempNearestDist;
        if (nearestDist == 0) {
            throw new IllegalArgumentException("Two houses can't be on same coordinate");
        }

        secondNearest(houses_sorted_By_X.clone(), houses_sorted_By_Y.clone());
    }

    /* ------------------------------ Helper methods --------------------------- */
    // whether in strip or not
    private boolean in_strip_By_Y(House[] houses_in_strip, int h1, int h2, double delta) {
        return houses_in_strip[h2].coord_Y() - houses_in_strip[h1].coord_Y() < delta;
    }

    // merge two already sorted halves of the array
    private void merge(House[] arr, House[] copy_arr, int start_id, int mid_id, int end_id) {
        if (end_id + 1 - start_id >= 0) {
            System.arraycopy(arr, start_id, copy_arr, start_id, end_id + 1 - start_id);
        }

        int firstIter = start_id;
        int secondIter = mid_id + 1;
        for (int i = start_id; i <= end_id; i++) {
            if (firstIter > mid_id) {
                arr[i] = copy_arr[secondIter++];
            } else if (secondIter > end_id) {
                arr[i] = copy_arr[firstIter++];
            } else if (copy_arr[firstIter].compareTo(copy_arr[secondIter]) < 0) {
                arr[i] = copy_arr[firstIter++];
            } else {
                arr[i] = copy_arr[secondIter++];
            }
        }
    }

    // clone the original array except one of the nearest house
    private House[] cloned_Array_except_nearest(House[] houses, House nearestHouse) {
        List<House> list = new ArrayList<>();
        for (House house : houses) {
            if (house.equalTo(nearestHouse)) continue;
            list.add(house);
        }

        House[] tempHouses = new House[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempHouses[i] = list.get(i);
        }
        return tempHouses;
    }

    // updating variables
    private void update_Second_Variables(double dist, House h1, House h2) {
        secondNearestDist = dist;
        secondNearestH1 = h1;
        secondNearestH2 = h2;
    }

    /* ----------------------------------- Main functionalities ----------------------------------- */
    // if there are only three houses, manually find second nearest
    private void secondNearest_For_three(House[] houses_X) {
        double min_dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 3; j++) {
                double dist = House.euclideanDistance(houses_X[i], houses_X[j]);
                if (dist < min_dist) {
                    update_Second_Variables(min_dist, tempNearestH1, tempNearestH2);
                    min_dist = dist;
                    tempNearestH1 = houses_X[i];
                    tempNearestH2 = houses_X[j];
                } else if (dist < secondNearestDist && dist != min_dist) {
                    update_Second_Variables(dist, houses_X[i], houses_X[j]);
                }
            }
        }
        nearestDist = min_dist;
    }

    // method to find nearest
    private double nearest(House[] houses_X, House[] houses_Y, House[] copy_arr, int start_id, int end_id) {
        if (end_id <= start_id) return Double.POSITIVE_INFINITY;

        int mid_id = (start_id + end_id) / 2;
        House mid_house = houses_X[mid_id];

        double delta_left = nearest(houses_X, houses_Y, copy_arr, start_id, mid_id);
        double delta_right = nearest(houses_X, houses_Y, copy_arr, mid_id + 1, end_id);
        double delta = Math.min(delta_left, delta_right);

        merge(houses_Y, copy_arr, start_id, mid_id, end_id);     // merging back Y_array

        House[] houses_In_Strip = new House[end_id - start_id + 1];
        int strip_iter = 0;
        for (int i = start_id; i <= end_id; i++) {
            if (Math.abs(mid_house.coord_X() - houses_Y[i].coord_X()) < delta) {
                houses_In_Strip[strip_iter++] = houses_Y[i];
            }
        }

        for (int i = 0; i < strip_iter; i++) {
            for (int j = i + 1; (j < strip_iter) && in_strip_By_Y(houses_In_Strip, i, j, delta); j++) {
                double dist = House.euclideanDistance(houses_In_Strip[i], houses_In_Strip[j]);
                if (dist < delta) {
                    delta = dist;
                    if (dist < tempNearestDist && dist != nearestDist) {
                        tempNearestDist = delta;
                        tempNearestH1 = houses_In_Strip[i];
                        tempNearestH2 = houses_In_Strip[j];
                    }
                }
            }
        }

        return delta;
    }

    // method to find second nearest
    private void secondNearest(House[] houses_X, House[] houses_Y) {
        House temp1 = tempNearestH1;
        House temp2 = tempNearestH2;
        House[] houses_clone_X = cloned_Array_except_nearest(houses_X, temp1);
        House[] houses_clone_Y = cloned_Array_except_nearest(houses_Y, temp1);

        int length = houses_clone_X.length;
        House[] copy_Arr = new House[length];

        tempNearestDist = Double.POSITIVE_INFINITY;
        nearest(houses_clone_X, houses_clone_Y, copy_Arr, 0, length - 1);
        update_Second_Variables(tempNearestDist, tempNearestH1, tempNearestH2);

        houses_clone_X = cloned_Array_except_nearest(houses_X, temp2);
        houses_clone_Y = cloned_Array_except_nearest(houses_Y, temp2);
        length = houses_clone_X.length;
        copy_Arr = new House[length];

        tempNearestDist = Double.POSITIVE_INFINITY;
        nearest(houses_clone_X, houses_clone_Y, copy_Arr, 0, length - 1);
        if (tempNearestDist < secondNearestDist) {
            update_Second_Variables(tempNearestDist, tempNearestH1, tempNearestH2);
        }
    }

    /* -------------------------------------- Public methods ------------------------------- */
    public double getSecondNearestDist() {
        if (secondNearestDist == nearestDist || secondNearestDist == 0) {
            throw new IllegalArgumentException("Second nearest is equal to nearest or 0!!");
        }
        return secondNearestDist;
    }

    public int[] secondNearestHouses() {
        int id1 = Math.min(secondNearestH1.house_ID(), secondNearestH2.house_ID());
        int id2 = Math.max(secondNearestH1.house_ID(), secondNearestH2.house_ID());
        return new int[]{id1, id2};
    }
}