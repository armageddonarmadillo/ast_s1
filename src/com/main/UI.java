package com.main;

import java.awt.*;

public class UI {
    static int life, score;
    static int p1, p2;

    static void draw(Graphics g){
        g.setFont(g.getFont().deriveFont(24f));
        g.drawString("Life:", 10, 30);
        g.setColor(Color.GREEN);
        for(int i = 0; i < life; i++) g.fillOval(65 + (i * 20), 10, 15, 20);
        g.setColor(Color.CYAN);
        g.drawString("Score: " + score, 10, 580);
        g.setColor(Color.GREEN);
        g.drawString("Power Level: " + Ship.powerups, Game.ww / 2 - g.getFontMetrics().stringWidth("Power Level: " + Ship.powerups) / 2, 580);
        g.setColor(Color.GREEN);
    }

    static void draw2P(Graphics g){
        g.setFont(g.getFont().deriveFont(24f));
        g.setColor(Color.YELLOW);
        for(int i = 0; i < p1; i++) g.fillOval(5 + (i * 20), Game.wh - 30, 15, 20);
        g.setColor(Color.RED);
        for(int i = 0; i < p2; i++) g.fillOval((Game.ww - (p2 * 20)) + (i * 20), Game.wh - 30, 15, 20);
    }
}
