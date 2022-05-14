package com.main;

import java.awt.*;

public class Rock extends VectorSprite {

    Rock(int x, int y) {
        super(x, y);
        poly = new Polygon();
        poly.addPoint(25, 25);
        poly.addPoint(-25, 25);
        poly.addPoint(-25, -25);
        poly.addPoint(25, -25);
        vpoly = new Polygon();
        vpoly.addPoint(25, 25);
        vpoly.addPoint(-25, 25);
        vpoly.addPoint(-25, -25);
        vpoly.addPoint(25, -25);
        rspeed = 0.005f;
    }
}
