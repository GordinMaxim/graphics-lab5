package ru.nsu.gordin.model;


import static ru.nsu.gordin.model.Vector3.DOT;
import static ru.nsu.gordin.model.Vector3.minus;

public class Sphere extends Primitive {
    private Vector3 center;
    private double radius;

    public Sphere(Vector3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Type getType() {
        return Type.SPHERE;
    }

    @Override
    public int Intersect(final Ray r, Distance dist) {
        Vector3 v = minus(r.getOrigin(), center);
        Vector3 dir = new Vector3(r.getDirection());
        double a = DOT(dir, dir);
        double b = -DOT(v, dir);
        double c = DOT(v, v);
        double det = b * b - a * (c - radius*radius);
        int retval = Ray.MISS;
        if(det > 0) {
            det = 2 * Math.sqrt(det);
            double i1 = (b - det) / (2 * DOT(dir, dir));
            double i2 = (b + det) / (2 * DOT(dir, dir));
            if(i2 > 0) {
                if(i1 < 0) {
                    if(i2 < dist.getDistance()) {
                        dist.setDistance(i2);
                        retval = Ray.INPRIM;
                    }
                }
                else {
                    if(i1 < dist.getDistance()) {
                        dist.setDistance(i1);
                        retval = Ray.HIT;
                    }
                }
            }
        }
        return retval;
    }

//    @Override
//    public int Intersect(final Ray r, Distance dist) {
//        Vector3 v = minus(r.getOrigin(), center);
//        double b = -DOT(v, r.getDirection());
//        double det = b*b - DOT(v, v) + radius*radius;
//        int retval = Ray.MISS;
//        if(det > 0) {
//            det = Math.sqrt(det);
//            double i1 = b - det;
//            double i2 = b + det;
//            if(i2 > 0) {
//                if(i1 < 0) {
//                    if(i2 < dist.getDistance()) {
//                        dist.setDistance(i2);
//                        retval = Ray.INPRIM;
//                    }
//                }
//                else {
//                    if(i1 < dist.getDistance()) {
//                        dist.setDistance(i1);
//                        retval = Ray.HIT;
//                    }
//                }
//            }
//        }
//        return retval;
////    }
//
    @Override
    public Vector3 getNormal(Vector3 pos) {
        Vector3 normal = minus(pos, center);
//        normal.mul(radius);
        return normal;
    }
}
