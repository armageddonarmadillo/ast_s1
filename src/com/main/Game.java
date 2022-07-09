package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Scene {
    static int ww = 900, wh = 600;  // Window Width, Window Height
    int ix = 0, iy = 0, fx = ww, fy = wh;
    int rock_timer, rock_delay = 1800, current_delay = rock_delay, upgrade, upgrade_limit = 3, upgrade_every = 2;
    static Random r = new Random();
    static Ship ship;

    //Game Lists
    static ArrayList<Line> lines = new ArrayList<>();
    static ArrayList<Rock> rocks = new ArrayList<>();
    static ArrayList<Bullet> bullets = new ArrayList<>();
    static ArrayList<Upgrade> upgrades = new ArrayList<>();

    Game(){
        init();
    }

    void init(){
        UI.life = 5;
        UI.score = 0;
        add_lines(10);
        ship = new Ship(ww / 2, wh / 2);
    }

    public void paint(Graphics g){
        //update the game
        update();

        //draw
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ww, wh);
        g.setColor(Color.GREEN);
        ship.draw(g);
        for(Rock rock : rocks) rock.draw(g);
        for(Bullet b : bullets) b.draw(g);
        for(Upgrade u : upgrades) u.draw(g);
        UI.draw(g);

        //redraw
        repaint();
    }

    void update(){
        //adding elements
        if(rock_timer++ >= current_delay){
            current_delay = (Math.max(current_delay - (current_delay / 10), (rock_delay / 10)));
            rock_timer = 0;
            add_rocks();
//            if(upgrades.size() < upgrade_limit){
//
//            }
            if(upgrade++ >= upgrade_every){
                upgrade = 0;
                add_upgrades();
            }
        }

        //updating elements
        ship.update();
        for(Rock rock : rocks) if(!rock.update_rock()) break;
        for(Bullet b : bullets) b.update();
        for(Upgrade u : upgrades) u.update();

        //removing elements
        house_keeping();
    }

    void house_keeping(){
        for (Bullet b : bullets) if(!b.active) { bullets.remove(b); break; }
        for (Rock r : rocks) if(!r.active) { rocks.remove(r); break; }
        for (Upgrade u : upgrades) if(!u.active) { upgrades.remove(u); break; }
    }

    void add_lines(int q){
        for(int i = 0; i < q; i++) lines.add(new Line(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)), ix, iy, fx, fy));
    }

    void add_rocks(){
        int b = 125;
        int x = r.nextInt();
        int y = r.nextInt() % wh;

        if(x % 2 == 0){
            x = -(x % b);
        } else {
            x = ww + (x % b);
        }

        if(0 < x % 100 && x % 100 < 10) rocks.add(new small_rock(x , y));
        if(11 < x % 100 && x % 100 < 49) rocks.add(new med_rock(x , y));
        if(50 < x % 100 && x % 100 < 99) rocks.add(new big_rock(x , y));
    }

    void add_upgrades(){
        int b = 50;
        int x = r.nextInt(ww - b) + b;
        int y = r.nextInt(wh - b) + b;

        upgrades.add(new Upgrade("boost", x, y));

        //if(0 < x % 100 && x % 100 < 10) rocks.add(new small_rock(x , y));
        //if(11 < x % 100 && x % 100 < 49) rocks.add(new med_rock(x , y));
        //if(50 < x % 100 && x % 100 < 99) rocks.add(new big_rock(x , y));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) ship.rspeed = -0.005f;
        if(e.getKeyCode() == KeyEvent.VK_D) ship.rspeed = 0.005f;
        if(e.getKeyCode() == KeyEvent.VK_W) ship.speed = 1;
        if(e.getKeyCode() == KeyEvent.VK_S) ship.speed = -1;
        if(e.getKeyCode() == KeyEvent.VK_SPACE) ship.firing = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) ship.rspeed = 0;
        if(e.getKeyCode() == KeyEvent.VK_D) ship.rspeed = 0;
        if(e.getKeyCode() == KeyEvent.VK_W) ship.speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_S) ship.speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_SPACE) ship.firing = false;
    }
}
