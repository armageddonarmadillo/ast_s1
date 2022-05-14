package com.main;

import java.awt.*;

public class VectorSprite {
    float x, y, xspeed, yspeed, angle, speed, rspeed;
    Polygon poly, vpoly;

    VectorSprite(int x, int y){
        this.x = x;
        this.y = y;
    }

    void draw(Graphics g){
        g.drawPolygon(vpoly);
    }

    void update(){
        angle += rspeed;
        xspeed = (float)Math.cos(angle);
        yspeed = (float)Math.sin(angle);
        x += xspeed * speed;
        y += yspeed * speed;
        for(int i = 0; i < poly.npoints; i++){
            int x2 = (int)Math.round(poly.xpoints[i] * Math.cos(angle) - poly.ypoints[i] * Math.sin(angle));
            int y2 = (int)Math.round(poly.xpoints[i] * Math.sin(angle) + poly.ypoints[i] * Math.cos(angle));
            vpoly.xpoints[i] = x2;
            vpoly.ypoints[i] = y2;
        }
        vpoly.translate((int)x, (int)y);
    }
}
