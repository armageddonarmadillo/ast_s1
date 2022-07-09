package com.main;

import java.awt.*;

public class UI {
    static int life, score;

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
}
