package com.main;

import java.awt.*;

public class VectorSprite {
    float x, y, xspeed, yspeed, angle, speed, rspeed;
    Polygon poly, vpoly;
    Color c, d = Color.GREEN; //c = object color, d = default color

    VectorSprite(int x, int y){
        this.x = x;
        this.y = y;
        c = new Color(Game.r.nextInt(201) + 55, Game.r.nextInt(201) + 55, Game.r.nextInt(201) + 55, 255);
    }

    void draw(Graphics g){
        g.setColor(c);
        g.fillPolygon(vpoly);
        g.setColor(d);
        //g.drawRect(hitbox().x, hitbox().y, hitbox().width, hitbox().height);
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

    Rectangle hitbox(){
        return new Rectangle((int)x - (poly.getBounds().width / 2), (int)y - (poly.getBounds().height / 2), poly.getBounds().width, poly.getBounds().height);
    }
}
