package com.main;

import java.awt.*;

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
            Game.ship.powerups++;
        }
    }
}
