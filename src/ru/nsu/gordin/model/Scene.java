package ru.nsu.gordin.model;

import java.awt.*;
import java.util.Vector;

public class Scene {
    private Vector<Primitive> primitives;

    public void initScene() {
        primitives = new Vector<Primitive>();

        Primitive plane1 = new Plane(new Vector3(0, -1,0), 4.4);
        plane1.setName("plane");
        plane1.getMaterial().setReflection(0.0);
        plane1.getMaterial().setDiffuse(1.0);
        plane1.getMaterial().setColor(new Vector3(0.2, 0.2, 0.7));
        primitives.add(plane1);

//        Primitive sphere1 = new Sphere(new Vector3(3, 2, 100), 0.5);
//        sphere1.setName("big sphere");
//        sphere1.getMaterial().setReflection(0.6);
//        sphere1.getMaterial().setColor(new Vector3(0.7, 0.2, 0.7));
//        primitives.add(sphere1);

        Primitive sphere2 = new Sphere(new Vector3(5, 5, 13), 0.1);
        sphere2.setName("small sphere");
        sphere2.getMaterial().setReflection(0.5);
        sphere2.getMaterial().setDiffuse(0.1);
        sphere2.getMaterial().setColor(new Vector3(0.1, 0.1, 0.1));
        primitives.add(sphere2);

        Primitive light1 = new Sphere(new Vector3(8, 8, 1), 0.1);
        light1.setLight(true);
        light1.getMaterial().setColor(new Vector3(0.7, 0.9, 0.2));
        primitives.add(light1);

//        Primitive light2 = new Sphere(new Vector3(2, 5, 1), 0.1);
//        light2.setLight(true);
//        light2.getMaterial().setColor(new Vector3(0.7, 0.7, 0.9));
//        primitives.add(light2);
    }

    public int getNumPrimitives() {
        return primitives.size();
    }

    public Primitive getPrimitive(int index) {
        return primitives.get(index);
    }
}
