/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import com.sun.javafx.geom.Vec3d;
import java.awt.Color;

/**
 *
 * @author Mads327b
 */
class mesh {
    Vector 
            A,B,C;
    Color col;
    mesh(Vector node1, Vector node2, Vector node3,Color col) {
        A = node1;
        B = node2;
        C = node3;
        this.col =col;
    }

    double front() {
        return (C.z);
    }
}
