package com.main;

import javax.swing.*;

public class Main {
    static Window window;

    public static void main(String[] args) {
        window = new Window(new Start());
    }

    public static void swap_scene(Scene scene){
        window.remove(window.current_scene);
        window.add(scene);
        window.current_scene = scene;
        window.addKeyListener(scene);

        //bad logic for resizing the window to discard old buffer
        window.setBounds(window.getBounds().x, window.getBounds().y, window.getBounds().width + 1, window.getBounds().height + 1);
        window.setBounds(window.getBounds().x, window.getBounds().y, window.getBounds().width - 1, window.getBounds().height - 1);
    }
}
