/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import com.sun.javafx.geom.Vec3d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Mads327b
 */
public class Handler {
    Game game;
    ArrayList<gameObject> object = new ArrayList<>();
    ArrayList<String> keys = new ArrayList<>();
    public Handler(Game game) {
        this.game = game;
        
        object.add(new CUBE(new Vector(0,0,0), new Vector(200,200,200), new Vector(Math.PI/4,Math.PI/4,0), new Vector(0,0,0),game) {
            @Override
            void key() {
                Vector newVel = new Vector(0, 0, 0);
                if(keys.contains(String.valueOf(KeyEvent.VK_W))){
                    if(keys.contains(String.valueOf(KeyEvent.VK_SHIFT))){
                        newVel = newVel.add(new Vector(0, -500, 0));
                    }else{
                        newVel = newVel.add(new Vector(0, 0, 500));
                    }
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_S))){
                    if(keys.contains(String.valueOf(KeyEvent.VK_SHIFT)))
                        newVel = newVel.add(new Vector(0, 500, 0));
                    else
                        newVel = newVel.add(new Vector(0, 0, -500));
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_D))){
                    newVel = newVel.add(new Vector(500, 0, 0));
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_A))){
                    newVel = newVel.add(new Vector(-500, 0, 0));
                }
                vel = newVel;
                Vector newRot = new Vector(0, 0, 0);
                if(keys.contains(String.valueOf(KeyEvent.VK_UP))){
                    if(keys.contains(String.valueOf(KeyEvent.VK_SHIFT))){
                        newRot = newRot.add(new Vector(0, -Math.PI, 0));
                    }else{
                        newRot = newRot.add(new Vector(0, 0, Math.PI));
                    }
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_DOWN))){
                    if(keys.contains(String.valueOf(KeyEvent.VK_SHIFT)))
                        newRot = newRot.add(new Vector(0, Math.PI, 0));
                    else
                        newRot = newRot.add(new Vector(0, 0, -Math.PI));
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_RIGHT))){
                    newRot = newRot.add(new Vector(Math.PI, 0, 0));
                }
                if(keys.contains(String.valueOf(KeyEvent.VK_LEFT))){
                    newRot = newRot.add(new Vector(-Math.PI, 0, 0));
                }
                rotate = newRot;
            }
        });
    }
    void render(){
        BufferStrategy bs = game.getBufferStrategy();
        if(bs == null){
            game.createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.translate(game.getWidth()/2, game.getHeight()/2);
        object.forEach((obj) -> {
            System.out.println(obj.type+" "+obj.pos.z);
            double per = 1000/(1000+obj.pos.z);
            double 
                    w = ((game.getWidth()+obj.size.x*1.5)/2)/per,
                    h = ((game.getHeight()+obj.size.y*1.5)/2)/per;
            if(obj.pos.x > -w && obj.pos.x < w && obj.pos.y > -h && obj.pos.y < h){
                System.out.println("render");
                obj.render(g);
            }
        });
        g.dispose();
        bs.show();
    }

    void tich(double time) {
        object.forEach((obj) -> {
            obj.tich(time);
        });
    }
}
