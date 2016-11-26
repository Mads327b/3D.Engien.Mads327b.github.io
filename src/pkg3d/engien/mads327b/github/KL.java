/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Mads327b
 */
public class KL implements KeyListener {
    Game game;

    public KL(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = String.valueOf(e.getKeyCode());
        if(!game.handler.keys.contains(key)){
            game.handler.keys.add(key);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String key = String.valueOf(e.getKeyCode());
        game.handler.keys.remove(key);
    }
    
}
