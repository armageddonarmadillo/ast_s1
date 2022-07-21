package com.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game2P extends Scene {
    Ship p1, p2;
    int p1_speed, p2_speed;

    Game2P(){ init(); }

    void init(){
        UI.p1 = 5;
        UI.p2 = 5;

        p1 = new Ship(Game.ww / 8, Game.wh / 2);
        p1.c = Color.YELLOW;
        p2 = new Ship((Game.ww / 8) * 7, Game.wh / 2);
        p2.c = Color.RED;
        p2.angle += Math.PI;
    }

    void update(){
        p1.update();
        p2.update();
        p1.y += p1_speed;
        p2.y += p2_speed;

        for(Bullet b : Game.bullets) {
            if(p1.hitbox().contains(b.hitbox())) { UI.p1--; b.dt = 9999; }
            if(p2.hitbox().contains(b.hitbox())) { UI.p2--; b.dt = 9999; }
            b.update();
        }

        //clean up
        house_keeping();
    }

    void house_keeping(){
        for (Bullet b : Game.bullets) if(!b.active) { Game.bullets.remove(b); break; }
    }

    @Override
    public void paint(Graphics g) {
        update();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.ww, Game.wh);
        g.setColor(Color.GREEN);
        p1.draw(g);
        p2.draw(g);
        for(Bullet b : Game.bullets) b.draw(g);
        UI.draw2P(g);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) p1_speed = -1;
        if(e.getKeyCode() == KeyEvent.VK_S) p1_speed = 1;
        if(e.getKeyCode() == KeyEvent.VK_D) p1.firing = true;
        if(e.getKeyCode() == KeyEvent.VK_P) p2_speed = -1;
        if(e.getKeyCode() == KeyEvent.VK_L) p2_speed = 1;
        if(e.getKeyCode() == KeyEvent.VK_K) p2.firing = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) p1_speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_S) p1_speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_P) p2_speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_L) p2_speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_D) p1.firing = false;
        if(e.getKeyCode() == KeyEvent.VK_K) p2.firing = false;
    }
}
