package com.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameOver extends Scene {
    Font f1, f2;
    String title = "GAME OVER";
    String instructions = "PRESS ENTER TO RETURN TO START";

    GameOver(){
        try {
            f1 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/fonts/valorant.ttf"));
            f2 = Font.createFont(Font.TRUETYPE_FONT, new File("./assets/fonts/space.ttf"));
        } catch (IOException|FontFormatException e) {
            System.out.println("Font failed to load...");
        }
    }

    @Override
    public void paint(Graphics g) {
        // clear screen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.ww, Game.wh); //clear screen ends

        //draw title
        if(f1!=null) g.setFont(f1);
        g.setFont(g.getFont().deriveFont(72f));
        g.setColor(Color.RED);
        g.drawString(title, (Game.ww / 2) - (g.getFontMetrics().stringWidth(title) / 2), 75);

        int score_x, score_y, score_w, score_h;
        score_x = Game.ww / 10;
        score_y = Game.wh / 10 + 25;
        score_w = Game.ww - (2 * (Game.ww / 8));
        score_h = Game.wh - (2 * (Game.wh / 8));

        g.setColor(Color.GREEN);
        g.drawRect(Game.ww / 10, Game.wh / 10 + 25, Game.ww - (2 * (Game.ww / 8)), Game.wh - (2 * (Game.wh / 8)));

        g.setColor(Color.RED);
        int line_distance = score_h / 5;
        g.setFont(g.getFont().deriveFont(60f));
        for(int i = 0; i < 5; i ++){
            g.drawString("PLAYER"+i+": \t"+(i*123), score_x + 15, (line_distance  += (score_h / 5)) - 20);
            if (i < 4) g.drawLine(score_x + 15, line_distance, score_x + score_w - 15, line_distance);
        }

        //draw instructions
        if (f2 != null) g.setFont(f2);
        g.setFont(g.getFont().deriveFont(32f));
        g.setColor(Color.RED);
        g.drawString(instructions, (Game.ww / 2) - (g.getFontMetrics().stringWidth(instructions) / 2), Game.wh - 30);

        //redraw
        repaint();
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
    }
}