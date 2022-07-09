package com.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Scene extends JPanel implements KeyListener {
    public abstract void paint(Graphics g);
    public abstract void keyTyped(KeyEvent e);
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
}
