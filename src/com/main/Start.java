package com.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Start extends Scene {
    Font f1, f2;
    String title = "It's our last class!";
    String instructions = "PRESS ENTER TO START";

    //effect variables
    String drawn_title = "";
    int c = 0, d = 222, e = 0, t = 333, index = 0;
    Color ct;
    boolean draw_forwards = true, blink = true;
    static ArrayList<Rock> rocks = new ArrayList<>();
    static Ship ship;

    Start(){
        ship = new Ship(Game.ww / 2, Game.wh / 2);
        ct = new Color(Game.r.nextInt(256), Game.r.nextInt(256), Game.r.nextInt(256));
        try {
            f1 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/fonts/valorant.ttf"));
            f2 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/fonts/space.ttf"));
        } catch (IOException|FontFormatException e) {
            System.out.println("Font failed to load...");
        }
    }

    @Override
    public void paint(Graphics g) {
        //timer logic for effects
        d = drawn_title.length() < title.length() ? 50 : 3000;
        if(c++ >= d){
            ct = new Color(Game.r.nextInt(256), Game.r.nextInt(256), Game.r.nextInt(256));
            c = 0;
            if(draw_forwards){
                drawn_title += title.split("")[index++];
            } else drawn_title = title.substring(title.length() - --index);
            if(drawn_title.length() >= title.length() || drawn_title.length() <= 0) draw_forwards = !draw_forwards;
        }
        if(e++ >= t){
            add_rocks();
            e = 0;
            blink = !blink;
        } //timer logic ends

        // clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.ww, Game.wh); //clear screen ends

        for(Rock rock : rocks) rock.draw(g);
        for(Rock rock : rocks) if(!rock.update_rock()) break;
        house_keeping();

        //test split-title draw
        if(f1!=null) g.setFont(f1);
        g.setFont(g.getFont().deriveFont(72f));
        g.setColor(ct);
        String[] letters = drawn_title.split("");
        int start_x = (Game.ww / 2) - (g.getFontMetrics().stringWidth(drawn_title) / 2);
        for(String l : letters) {
            g.drawString(l, start_x, 2 * (Game.wh / 5));
            start_x += g.getFontMetrics().stringWidth(l);
        } //test draw ends

        //draw instructions
        if(blink) {
            if (f2 != null) g.setFont(f2);
            g.setFont(g.getFont().deriveFont(36f));
            g.setColor(Color.RED);
            g.drawString(instructions, (Game.ww / 2) - (g.getFontMetrics().stringWidth(instructions) / 2), 4 * (Game.wh / 5));
        } //draw ends

        //redraw
        repaint();
    }

    void add_rocks(){
        int b = 125;
        int x = Game.r.nextInt();
        int y = Game.r.nextInt() % Game.wh;
        if(x % 2 == 0) x = -(x % b); else  x = Game.ww + (x % b);

        rocks.add(Game.r.nextInt() % 2 == 0 ? new Rock(x, y, 20) :new Star(x, y, 5)); //
//        if(0 < x % 100 && x % 100 < 10) rocks.add(new small_rock(x , y));
//        if(11 < x % 100 && x % 100 < 49) rocks.add(new med_rock(x , y));
//        if(50 < x % 100 && x % 100 < 99) rocks.add(new big_rock(x , y));
    }

    void house_keeping(){
        for (Rock r : rocks) if(!r.active) { rocks.remove(r); break; }
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && Main.window.current_scene == this) Main.swap_scene(new Game());
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && Main.window.current_scene == this) Main.swap_scene(new Game2P());
    }
}
