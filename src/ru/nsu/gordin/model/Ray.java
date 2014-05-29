package ru.nsu.gordin.model;

public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    public static int MISS = 0;
    public static int HIT = 1;
    public static int INPRIM = -1;

    public Ray(Vector3 o, Vector3 d) {
        origin = o;
        direction = d;
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }
}
