package com.main;

import java.awt.*;

public class Star extends Rock{
    int rmin = 3, rmax = 10;

    Star(int x, int y, int n) {
        super(x, y);
        double angle_step = Math.PI * 2 / n;
        poly = new Polygon();
        vpoly = new Polygon();
        for(int i = 0; i < n; i++){
            double angle = (angle_step * i) + (Game.r.nextDouble() - 0.5) * angle_step * 0.25;
            double radius = rmin + Game.r.nextDouble() * (rmax - rmin);
            poly.addPoint((int)(Math.cos(angle) * radius), (int)(Math.sin(angle) * radius));
            vpoly.addPoint((int)(Math.cos(angle) * radius), (int)(Math.sin(angle) * radius));
        }
        s = 1.f;
    }
}
