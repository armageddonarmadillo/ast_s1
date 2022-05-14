package com.main;

import java.awt.*;

public class Ship extends VectorSprite{

    Ship(int x, int y) {
        super(x, y);
        poly = new Polygon();
        poly.addPoint(100, 0);
        poly.addPoint(-75, 50);
        poly.addPoint(-50, 0);
        poly.addPoint(-75, -50);
        vpoly = new Polygon();
        vpoly.addPoint(100, 0);
        vpoly.addPoint(-75, 50);
        vpoly.addPoint(-50, 0);
        vpoly.addPoint(-75, -50);
        speed = 0;
        rspeed = 0;
    }
}
