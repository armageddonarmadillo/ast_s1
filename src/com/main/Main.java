package com.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Game game = new Game();
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(Game.ww, Game.wh);
        frame.setTitle("Asteroids");
    }
}
