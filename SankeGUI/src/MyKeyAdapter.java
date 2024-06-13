/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author thippeswamy
 */
public class MyKeyAdapter extends KeyAdapter {

    private final SnakeGameFrame outer;

    public MyKeyAdapter(final SnakeGameFrame outer) {
        this.outer = outer;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (outer.direction != 'R') {
                    outer.direction = 'L';
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (outer.direction != 'L') {
                    outer.direction = 'R';
                }
            }
            case KeyEvent.VK_UP -> {
                if (outer.direction != 'D') {
                    outer.direction = 'U';
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (outer.direction != 'U') {
                    outer.direction = 'D';
                }
            }
        }
    }
}
