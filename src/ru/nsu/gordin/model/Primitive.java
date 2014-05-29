package ru.nsu.gordin.model;

abstract public class Primitive {
    private Material material;
    private String name;
    private boolean light;

    static enum Type {
        SPHERE, PLANE
    }

    public Primitive(Material m, String n, boolean l) {
        material = m;
        name = n;
        light = l;
    }

    public Primitive() {
        this(new Material(), null, false);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    abstract public Type getType();

    abstract public int Intersect(Ray r, Distance dist);

    abstract public Vector3 getNormal(Vector3 pos);
}
