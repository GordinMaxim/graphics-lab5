package ru.nsu.gordin.model;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(Vector3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double sqrLength() {
        return (x*x + y*y + z*z);
    }

    public void normalize() {
        double l = 1.0/length();
        x *= l;
        y *= l;
        z *= l;
    }
    public double DOT(Vector3 b) {
        return (x*b.x + y*b.y + z*b.z);
    }

    public void plus(Vector3 b) {
        x += b.x;
        y += b.y;
        z += b.z;
    }

    public void minus(Vector3 b) {
        x -= b.x;
        y -= b.y;
        z -= b.z;
    }

    public void mul(Vector3 b) {
        x *= b.x;
        y *= b.y;
        z *= b.z;
    }

    public void mul(double k) {
        x *= k;
        y *= k;
        z *= k;
    }

    static public double DOT(Vector3 a, Vector3 b) {
        return (a.x*b.x + a.y*b.y + a.z*b.z);
    }

    static public Vector3 plus(Vector3 a, Vector3 b) {
        return new Vector3(a.x+b.x, a.y+b.y, a.z+b.z);
    }

    static public Vector3 minus(Vector3 a, Vector3 b) {
        return new Vector3(a.x-b.x, a.y-b.y, a.z-b.z);
    }

    static public Vector3 mul(Vector3 a, Vector3 b) {
        return new Vector3(a.x*b.x, a.y*b.y, a.z*b.z);
    }

    static public Vector3 mul(Vector3 a, double k) {
        return new Vector3(k*a.x, k*a.y, k*a.z);
    }
}
