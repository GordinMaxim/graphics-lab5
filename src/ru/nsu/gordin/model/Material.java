package ru.nsu.gordin.model;

import java.awt.*;

public class Material {
    private Vector3 color;
    private double refl;
    private double diff;

    public Material() {
        color = new Vector3(0.2,0.2,0.2);
        refl = 0;
        diff = 0.2;
    }

    public Material(Vector3 color, float refl, float diff) {
        this.color = color;
        this.refl = refl;
        this.diff = diff;
    }

    public Vector3 getColor() {
        return color;
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public double getReflection() {
        return refl;
    }

    public void setReflection(double refl) {
        this.refl = refl;
    }

    public double getDiffuse() {
        return diff;
    }

    public double getSpecular() {
        return 1.0 - diff;
    }

    public void setDiffuse(double diff) {
        this.diff = diff;
    }
}
