package com.main;

import java.awt.*;
import java.util.Random;

public class Line {
    int x, y, fx, fy;
    int speed = 0, xspeed = 0, yspeed = 0;
    int c = 0, d = Game.r.nextInt(50) + 10;
    Color color;
    int[][] lines;

    Line(Color color, int x, int y, int fx, int fy){
        this.color = color;
        this.x = x;
        this.y = y;
        this.fx = fx;
        this.fy = fy;
        speed = Game.r.nextInt(9) + 1; //random picks a number between 0 and it's bounds (INT_MAX if no bounds) so we add 1 to prevent having a speed of 0
    }

    void update(){
        yspeed = y >= Game.wh ? -speed : y <= 0 ? speed : yspeed;
        if(c++ >= d) {
            y += yspeed;
            fy -= yspeed;
            c = 0;
        }
        //lines = split();
    }

    void draw(Graphics g){
        g.setColor(color);
        g.drawLine(x, y, fx, fy);
        //for(int[] l : lines) g.drawLine(l[0], l[1], l[2], l[3]);
    }

    int[][] split(){
        //find the length of the line
        int w = fx - x;
        int h = fy - y;
        float length = (float)Math.sqrt(w * w + h * h);
        float angle = (float)Math.asin(h / length);
        //split the length randomly - how pls help
        int segments = Game.r.nextInt(11) + 15;
        int segment_width = (int)(length  / segments);
        //store line positions
        int[][] pieces = new int[segments][4];
        int pfx = x, pfy = y;
        for(int[] p : pieces) {
            //p = new int[4];
            p[0] = pfx;                                  // x value
            p[1] = pfy;                                  // y value
            p[2] = (int)(Math.cos(angle) * length); // fx value
            p[3] = (int)(Math.sin(angle) * segment_width); // fy value
            pfx = p[2];
            pfy = p[3];
        }
        return pieces;
    }
}
