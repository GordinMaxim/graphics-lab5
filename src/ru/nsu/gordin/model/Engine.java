package ru.nsu.gordin.model;

import java.awt.*;
import java.util.Vector;

import static ru.nsu.gordin.model.Vector3.*;

public class Engine {
    private Scene scene;

    final static int TRACE_DEPTH = 2;
    final static double EPSILON = 0.0001;

    public Engine(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public static int count = 0;
    public Primitive rayTrace(Ray ray, Vector3 color, int depth, double index, Distance distance) {
        if(depth > TRACE_DEPTH) {
            return null;
        }
        distance.setDistance(Double.MAX_VALUE);
        Vector3 pi;
        Primitive prim = null;
        int result;

        for(int i = 0; i < scene.getNumPrimitives(); i++) {
            Primitive pr = scene.getPrimitive(i);
            int res;
            if(Ray.MISS != (res = pr.Intersect(ray, distance))) {
                prim = pr;
                result = res;
            }
        }
        if(null == prim) {
            return null;
        }
        if(prim.isLight()) {
            color.set(1, 1, 1);
        }
        else {
//            count++;
//            color.plus(prim.getMaterial().getColor());
            pi = plus(ray.getOrigin(), mul(ray.getDirection(), distance.getDistance()));
            for(int l = 0; l < scene.getNumPrimitives(); l++) {
                Primitive p = scene.getPrimitive(l);
                if(p.isLight()) {
                    Primitive light = p;
                    double shade = 1.0;
                    if(light.getType() == Primitive.Type.SPHERE) {
                        Vector3 L = minus(((Sphere) light).getCenter(), pi);
                        Distance tdist = new Distance();
                        tdist.setDistance(L.length());
                        L.mul(1.0 / tdist.getDistance());
                        Ray r = new Ray(plus(pi, mul(L, EPSILON)), L);
                        for(int s = 0; s < scene.getNumPrimitives(); s++) {
                            Primitive pr = scene.getPrimitive(s);
                            if((pr != light) && (pr.Intersect(r, tdist) != Ray.MISS)) {
                                shade = 0;
                                break;
                            }
                        }
                    }
//                    Vector3 L = minus(((Sphere) light).getCenter(), pi);
                    Vector3 L = minus(pi, ((Sphere) light).getCenter());
                    L.normalize();
                    Vector3 N = prim.getNormal(pi);
                    if(prim.getMaterial().getDiffuse() > 0) {
                        double dot = DOT(L, N);

                        if(dot > 0) {
//                            if(prim.getType() == Primitive.Type.SPHERE)
//                                System.out.println("sphere");
                            double diff = dot * prim.getMaterial().getDiffuse() * shade;
                            color.plus(mul(light.getMaterial().getColor(), diff));
                        }
                    }
                }
            }
//            double refl = prim.getMaterial().getReflection();
//            if(refl > 0) {
//                Vector3 N = prim.getNormal(pi);
//                Vector3 R = minus(ray.getDirection(), mul(N, 2.0 * DOT(ray.getDirection(), N)));
//                if(depth < TRACE_DEPTH) {
//                    Vector3 rcol = new Vector3(0,0,0);
//                    Distance distance1 = new Distance();
//                    rayTrace(new Ray(plus(pi, mul(R, EPSILON)), R), rcol, depth+1, index, distance1);
//                    color.plus(mul(mul(prim.getMaterial().getColor(), rcol), refl));
//                }
//            }
        }

        return prim;
    }
}
