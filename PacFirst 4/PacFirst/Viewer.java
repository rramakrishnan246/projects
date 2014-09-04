//created by Andy Hutchison and Rohan Ramakrishnan

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;

public class Viewer

{
    public static void main(String[] args)
    {
        final Pacman component = new Pacman();
        component.setFocusable(true);

        class KeyClickListener implements KeyListener
        {
            public void keyPressed(KeyEvent e)
            {

                if (e.getKeyCode() == KeyEvent.VK_DOWN ) component.movePacman(1);
                else if (e.getKeyCode() == KeyEvent.VK_UP) component.movePacman(2);
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) component.movePacman(3);
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) component.movePacman(4);

            }

            public void keyReleased(KeyEvent e){}

            public void keyTyped(KeyEvent e){}
        }

        KeyListener listener = new KeyClickListener();
        component.addKeyListener(listener);
        JFrame frame = new JFrame();
        frame.setSize(750,650);
        frame.setTitle("A movable circle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(component);
        frame.setVisible(true);
        Timer t = new Timer(150, component);
        t.start();

    }
}