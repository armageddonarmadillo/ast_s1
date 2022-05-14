package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener {
    static int ww = 900, wh = 600;  // Window Width, Window Height
    int ix = 0, iy = 0, fx = ww, fy = wh;
    static Random r = new Random();
    Ship ship;
    Rock rock;

    //Game Lists
    static ArrayList<Line> lines = new ArrayList<Line>();

    Game(){
        init();
    }

    void init(){
        add_lines(10);
        ship = new Ship(500, 500);
        rock = new Rock(250, 250);
    }

    public void paint(Graphics g){
        update();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ww, wh);
        g.setColor(Color.GREEN);
        ship.draw(g);
        rock.draw(g);
        repaint();
    }

    void update(){
        ship.update();
        rock.update();
        //for(Line l : lines) l.update();
    }

    void add_lines(int q){
        for(int i = 0; i < q; i++) lines.add(new Line(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)), ix, iy, fx, fy));
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) ship.rspeed = 0;
        if(e.getKeyCode() == KeyEvent.VK_D) ship.rspeed = 0;
        if(e.getKeyCode() == KeyEvent.VK_W) ship.speed = 0;
        if(e.getKeyCode() == KeyEvent.VK_S) ship.speed = 0;
    }
}
