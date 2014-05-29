package ru.nsu.gordin.model;

import static ru.nsu.gordin.model.Vector3.DOT;

public class Plane extends Primitive {
    private Vector3 normal;
    private double D;

    public Plane(Vector3 normal, double D) {
        this.normal = normal;
        this.D = D;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public double getD() {
        return D;
    }

    @Override
    public Type getType() {
        return Type.PLANE;
    }

    @Override
    public int Intersect(Ray r, Distance dist) {
        double d = DOT(normal, r.getDirection());
        if(d != 0) {
            double distance = -(DOT(normal, r.getOrigin()) + this.D) / d;
            if(distance > 0) {
                if(distance < dist.getDistance()) {
                    dist.setDistance(distance);
                    return Ray.HIT;
                }
            }
        }
        return Ray.MISS;
    }

    @Override
    public Vector3 getNormal(Vector3 pos) {
        return normal;
    }
}
