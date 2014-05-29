package ru.nsu.gordin.view;

import ru.nsu.gordin.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements KeyListener {
    private double leftBorder;
    private double rightBorder;
    private double topBorder;
    private double bottomBorder;
    private double dx;
    private double dy;
    private double currX;
    private double currY;
    private Engine engine;
    private Vector3 eye;
    private int width;
    private int height;
    private Scene scene;
    private BufferedImage image;

    public DrawPanel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void init() {
        scene = new Scene();
        scene.initScene();
        engine = new Engine(scene);
        leftBorder = 0.0;
        rightBorder = 9.0;
        topBorder = 9.0;
        bottomBorder = 0.0;
        dx = (rightBorder - leftBorder) / width;
        dy = (topBorder - bottomBorder) / height;

        eye = new Vector3((rightBorder - leftBorder)/2.0, (topBorder - bottomBorder)/2.0, -17.0);

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void paintComponent(Graphics g) {
        currY = topBorder;
        for (int i = 0; i < height; i++) {
            currX = leftBorder;
            for (int j = 0; j < width; j++) {
                Vector3 col = new Vector3(0, 0, 0);
                Vector3 dir = new Vector3(currX, currY, 0);
                dir.minus(eye);
                Ray r = new Ray(eye, dir);
                Distance dist = new Distance();
                engine.rayTrace(r, col, 1, 1.0, dist);
                int red = (int)(col.x * 256);
                int green = (int)(col.y * 256);
                int blue = (int)(col.z * 256);

                if(red > 255) red = 255;
                if(green > 255) green = 255;
                if(blue > 255) blue = 255;

//                System.out.println(red+" "+green+" "+blue);
                image.setRGB(j, i, new Color(red, green, blue).getRGB());
                currX += dx;
            }
            currY -= dy;
        }
        System.out.println(Engine.count);
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        double x = eye.x;
        double y = eye.y;
        double z = eye.z;
        double angle = Math.toRadians(1);
//        System.out.println(e.getKeyCode());

        if(code == KeyEvent.VK_DOWN) {
            y = Math.cos(angle) * eye.y - Math.sin(angle) * eye.z;
            z = Math.sin(angle) * eye.y + Math.cos(angle) * eye.z;
        }
        else if(code == KeyEvent.VK_UP) {
            y = Math.cos(-angle) * eye.y - Math.sin(-angle) * eye.z;
            z = Math.sin(-angle) * eye.y + Math.cos(-angle) * eye.z;
        }
        else if(code == KeyEvent.VK_RIGHT) {
            x = Math.cos(angle) * eye.x + Math.sin(angle) * eye.z;
            z = -Math.sin(angle) * eye.x + Math.cos(angle) * eye.z;

        }
        else if(code == KeyEvent.VK_LEFT) {
            x = Math.cos(-angle) * eye.x + Math.sin(-angle) * eye.z;
            z = -Math.sin(-angle) * eye.x + Math.cos(-angle) * eye.z;
        }
        else if(code == KeyEvent.VK_W) {

        }
        else if(code == KeyEvent.VK_S) {

        }
        else if(code == KeyEvent.VK_A) {

        }
        else if(code == KeyEvent.VK_D) {

        }

        eye.x = x;
        eye.y = y;
        eye.z = z;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}