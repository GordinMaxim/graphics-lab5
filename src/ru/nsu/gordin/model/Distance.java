package ru.nsu.gordin.model;

public class Distance {
    private double dist;

    public Distance() {
        dist = Double.MAX_VALUE;
    }

    public double getDistance() {
        return dist;
    }

    public void setDistance(double dist) {
        this.dist = dist;
    }
}
