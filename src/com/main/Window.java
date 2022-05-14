package com.main;

import javax.swing.*;

public class Window extends JFrame {
    Window(Game instance){
        add(instance);
        pack();
        setVisible(true);
        setSize(Game.ww, Game.wh);
        setTitle("Asteroids");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(instance);
    }
}
