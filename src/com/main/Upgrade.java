package com.main;

import java.awt.*;

class Health extends Upgrade {

    Health(int x, int y){
        super("health", x, y);
        poly = new Polygon();
        poly.addPoint(5, 5);
        poly.addPoint(-7, 7);
        poly.addPoint(-5, -5);
        poly.addPoint(7, -7);
        vpoly = new Polygon();
        vpoly.addPoint(5, 5);
        vpoly.addPoint(-7, 7);
        vpoly.addPoint(-5, -5);
        vpoly.addPoint(7, -7);
    }

    void update(){
        super.update();

        if(!active) UI.life++;
    }
}

public class Upgrade extends VectorSprite {
    String type;
    boolean active = true;

    Upgrade(String type, int x, int y) {
        super(x, y);
        this.type = type;
        poly = new Polygon();
        poly.addPoint(5, 5);
        poly.addPoint(-5, 5);
        poly.addPoint(-5, -5);
        poly.addPoint(5, -5);
        vpoly = new Polygon();
        vpoly.addPoint(5, 5);
        vpoly.addPoint(-5, 5);
        vpoly.addPoint(-5, -5);
        vpoly.addPoint(5, -5);
        rspeed = .004f;
    }

    void update(){
        super.update();

        if(Game.ship.hitbox().contains(hitbox())) {
            active = false;
            Ship.powerups++;
        }
    }
}
