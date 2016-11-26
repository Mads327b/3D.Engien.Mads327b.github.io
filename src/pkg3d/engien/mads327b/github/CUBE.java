/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Comparator;

/**
 *
 * @author Mads327b
 */
public abstract class CUBE extends gameObject{
    Game game;
    abstract void key();
    Vector rotate = new Vector(0, 0, 0);
    public CUBE(Vector Pos, Vector size, Vector rotate, Vector Vel,Game game) {
        super(ObjectType.cube, Pos, size, rotate, Vel);
        this.game = game;
    }
    @Override
    void tich(double time) {
        key();
        rotate3D(new Vector(rotate.x*time, rotate.y*time, rotate.z*time));
        velosity(time);
    }
    
}
