package com.main;

import javax.swing.*;

public class Window extends JFrame {
    Scene current_scene;

    Window(Scene instance){
        current_scene = instance;
        add(instance);
        pack();
        setVisible(true);
        setSize(Game.ww + 12, Game.wh + 37);
        setLocationRelativeTo(null);
        setTitle("Asteroids");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(instance);
    }
}
