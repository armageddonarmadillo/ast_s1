package com.main;

import java.awt.*;

public class Ship extends VectorSprite{
    boolean firing = false, active = true;
    int durability, counter = 0, delay;
    int spawn_x, spawn_y, scale;

    //powerup stuff
    static int powerups = 25;

    Ship(int x, int y) {
        super(x, y);
        spawn_x = x;
        spawn_y = y;
        poly = new Polygon();
        poly.addPoint(20, 0);
        poly.addPoint(-5, 8);
        poly.addPoint(0, 15);
        poly.addPoint(8, 20);
        poly.addPoint(0,20);
        poly.addPoint(-15,5);
        poly.addPoint(-18,3);
        poly.addPoint(-20,0);
        poly.addPoint(-18,-3);
        poly.addPoint(-15, -5);
        poly.addPoint(0, -20);
        poly.addPoint(8, -20);
        poly.addPoint(0, -15);
        poly.addPoint(-5, -8);
        vpoly = new Polygon();
        vpoly.addPoint(20, 0);
        vpoly.addPoint(-5, 8);
        vpoly.addPoint(0, 15);
        vpoly.addPoint(8, 20);
        vpoly.addPoint(0,20);
        vpoly.addPoint(-15,5);
        vpoly.addPoint(-18,3);
        vpoly.addPoint(-20,0);
        vpoly.addPoint(-18,-3);
        vpoly.addPoint(-15, -5);
        vpoly.addPoint(0, -20);
        vpoly.addPoint(8, -20);
        vpoly.addPoint(0, -15);
        vpoly.addPoint(-5, -8);
        speed = 0;
        rspeed = 0;
        durability = 5;
        delay = 60;
        c = new Color(24, 239, 148);
    }

    void update(){
        super.update();
        if(firing || powerups >= 25) {
            if (counter++ >= delay) {
                counter = 0;
                fire();
            }
        } else counter = delay;

        // keep ship on screen
        if(x >= Game.ww + poly.getBounds().width) x = 0;
        if(x <= -poly.getBounds().width) x = Game.ww;
        if(y >= Game.wh + poly.getBounds().height) y = 0;
        if(y <= -poly.getBounds().height) y = Game.wh;
    }

    void fire(){
        if(powerups >= 25) c = new Color(Game.r.nextInt(256), Game.r.nextInt(256), Game.r.nextInt(256));
        if(powerups >= 10) for(int i = 0; i < powerups; i++) { delay = powerups; Bullet.md = 50 + powerups; Game.bullets.add(new Bullet(vpoly.xpoints[i % poly.npoints], vpoly.ypoints[i % poly.npoints], (Game.r.nextInt() % 360.f))); }
        else if(powerups >= 3) for(int i = 0; i < (Math.min(powerups, poly.npoints)); i++) Game.bullets.add(new Bullet(vpoly.xpoints[i], vpoly.ypoints[i], angle));
        else Game.bullets.add(new Bullet(vpoly.xpoints[0], vpoly.ypoints[0], angle));
    }

    void reset() { x = spawn_x; y = spawn_y; }
    void random_reset() { x = Game.r.nextInt(800) + 50; y = Game.r.nextInt(500) + 50; }
}
