package com.main;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    static int ww = 900, wh = 600;  // Window Width, Window Height
    int ix = 0, iy = 0, fx = ww, fy = wh;
    int xspeed = 0, yspeed = 0;

    Game(){
        init();
    }

    void init(){
        yspeed = 5;
    }

    public void paint(Graphics g){
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, ww, wh);
        g.setColor(new Color(0, 255, 0));
        g.drawLine(ix, iy, fx, fy);
        repaint();
        update();
    }

    void update(){
        if(iy == wh || iy == 0) yspeed = -yspeed;
        iy += yspeed;
        fy -= yspeed;
    }
}
