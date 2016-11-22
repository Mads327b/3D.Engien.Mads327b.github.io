/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Mads327b
 */
public class Handler {
    Game game;

    public Handler(Game game) {
        this.game = game;
    }
    void render(){
        BufferStrategy bs = game.getBufferStrategy();
        if(bs == null){
            game.createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        
        g.dispose();
        bs.show();
    }

    void tich(double time) {
        
    }
}
