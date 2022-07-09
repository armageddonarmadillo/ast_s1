package com.main;

import java.awt.*;

public class Bullet extends VectorSprite {
    int dt = 0; //distanced travelled
    static int size, md = 300;
    boolean active = true;

    Bullet(float x, float y, float a){
        super((int)x, (int)y);
        poly = new Polygon();
        poly.addPoint(1 + size, 1 + size);
        poly.addPoint(-1 + size, 1 + size);
        poly.addPoint(-1 + size, -1 + size);
        poly.addPoint(1 + size, -1 + size);
        vpoly = new Polygon();
        vpoly.addPoint(1 + size, 1 + size);
        vpoly.addPoint(-1 + size, 1 + size);
        vpoly.addPoint(-1 + size, -1 + size);
        vpoly.addPoint(1 + size, -1 + size);

        angle = a;
        speed = 3;
    }

    void update(){
        super.update();
        dt += Math.abs(xspeed) + Math.abs(yspeed);
        active = active && !(dt >= md);

        if(Game.ship.powerups >= 5){
            if(x >= Game.ww + poly.getBounds().width) x = 0;
            if(x <= -poly.getBounds().width) x = Game.ww;
            if(y >= Game.wh + poly.getBounds().height) y = 0;
            if(y <= -poly.getBounds().height) y = Game.wh;
        }
    }
}
