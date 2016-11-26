/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

import com.sun.javafx.geom.Vec3d;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Mads327b
 */
public abstract class gameObject {
    void render(Graphics2D g){
        faces.sort(new Comparator<mesh>() {
            @Override
            public int compare(mesh o1, mesh o2) {
                double dis1 = o1.front();
                double dis2 = o2.front();
                if(dis1 < dis2 )
                    return 1;
                else if(dis1 > dis2 )
                    return -1;
                else
                    return 0;
            }
        });
        double per = 1000/(1000+pos.z),
                x = pos.x*per,
                y = pos.y*per;
        for (int i = 0; i < faces.size(); i++) {
            GeneralPath p = new GeneralPath();
            mesh get = faces.get(i);
            double 
                    perA = 1000/(1000+get.A.z+pos.z),
                    perB = 1000/(1000+get.B.z+pos.z),
                    perC = 1000/(1000+get.C.z+pos.z);
            p.moveTo(get.A.x*perA+x, get.A.y*perA+y);
            p.lineTo(get.B.x*perB+x, get.B.y*perB+y);
            p.lineTo(get.C.x*perC+x, get.C.y*perC+y);
            p.lineTo(get.A.x*perA+x, get.A.y*perA+y);

            g.setColor(get.col);
            g.fill(p);
            g.setColor(Color.BLACK);
            g.draw(p);
            System.out.println(p.getCurrentPoint()+" "+type);
        }
    }
    abstract void tich(double time);
    
    Vector 
            pos     = new Vector(0, 0, 0),
            vel     = new Vector(0, 0, 0),
            rotate  = new Vector(0, 0, 0),
            size    = new Vector(0, 0, 0);
    ArrayList<mesh> faces = new ArrayList<>();
    ObjectType type;
    
    public gameObject(ObjectType type,Vector Pos,Vector size,Vector rotate, Vector Vel) {
        this.pos    = Pos;
        this.vel    = Vel;
        this.size   = size;
        this.type   = type;
        this.rotate = rotate;
        create();
    }
    void create(){
        switch(type){
            case cube:
                {
                    faces.clear();
//                    System.out.println("cube");
                    double 
                            x1 = -size.x/2,
                            x2 =  size.x/2,
                            y1 = -size.y/2,
                            y2 =  size.y/2,
                            z1 = -size.z/2,
                            z2 =  size.z/2;
                    Vector 
                            node1 = rotateX(rotateY(rotateZ(new Vector(x1, y1, z1),rotate.z),rotate.y),rotate.x),
                            node2 = rotateX(rotateY(rotateZ(new Vector(x2, y1, z1),rotate.z),rotate.y),rotate.x),
                            node3 = rotateX(rotateY(rotateZ(new Vector(x2, y2, z1),rotate.z),rotate.y),rotate.x),
                            node4 = rotateX(rotateY(rotateZ(new Vector(x1, y2, z1),rotate.z),rotate.y),rotate.x),
                            node5 = rotateX(rotateY(rotateZ(new Vector(x1, y1, z2),rotate.z),rotate.y),rotate.x),
                            node6 = rotateX(rotateY(rotateZ(new Vector(x2, y1, z2),rotate.z),rotate.y),rotate.x),
                            node7 = rotateX(rotateY(rotateZ(new Vector(x2, y2, z2),rotate.z),rotate.y),rotate.x),
                            node8 = rotateX(rotateY(rotateZ(new Vector(x1, y2, z2),rotate.z),rotate.y),rotate.x),

                            node10 = rotateX(rotateY(rotateZ(new Vector(0, y1, 0),rotate.z),rotate.y),rotate.x),
                            node11 = rotateX(rotateY(rotateZ(new Vector(x1, 0, 0),rotate.z),rotate.y),rotate.x),
                            node12 = rotateX(rotateY(rotateZ(new Vector(0, 0, z1),rotate.z),rotate.y),rotate.x),

                            node13 = rotateX(rotateY(rotateZ(new Vector(0, y2, 0),rotate.z),rotate.y),rotate.x),
                            node14 = rotateX(rotateY(rotateZ(new Vector(x2, 0, 0),rotate.z),rotate.y),rotate.x),
                            node15 = rotateX(rotateY(rotateZ(new Vector(0, 0, z2),rotate.z),rotate.y),rotate.x);
                    //front
                        faces.add(new mesh(node1,node2,node12,Color.BLUE));
                        faces.add(new mesh(node1,node4,node12,Color.BLUE));
                        faces.add(new mesh(node4,node3,node12,Color.BLUE));
                        faces.add(new mesh(node3,node2,node12,Color.BLUE));
                    //left
                        faces.add(new mesh(node1,node5,node11,Color.GREEN));
                        faces.add(new mesh(node5,node8,node11,Color.GREEN));
                        faces.add(new mesh(node8,node4,node11,Color.GREEN));
                        faces.add(new mesh(node4,node1,node11,Color.GREEN));
                    //back
                        faces.add(new mesh(node5,node6,node15,Color.YELLOW));
                        faces.add(new mesh(node6,node7,node15,Color.YELLOW));
                        faces.add(new mesh(node7,node8,node15,Color.YELLOW));
                        faces.add(new mesh(node8,node5,node15,Color.YELLOW));
                    //right
                        faces.add(new mesh(node2,node6,node14,Color.PINK));
                        faces.add(new mesh(node6,node7,node14,Color.PINK));
                        faces.add(new mesh(node7,node3,node14,Color.PINK));
                        faces.add(new mesh(node3,node2,node14,Color.PINK));
                    //top
                        faces.add(new mesh(node1,node2,node10,Color.RED));
                        faces.add(new mesh(node2,node6,node10,Color.RED));
                        faces.add(new mesh(node6,node5,node10,Color.RED));
                        faces.add(new mesh(node5,node1,node10,Color.RED));
                    //bottom
                        faces.add(new mesh(node4,node3,node13,Color.CYAN));
                        faces.add(new mesh(node3,node7,node13,Color.CYAN));
                        faces.add(new mesh(node7,node8,node13,Color.CYAN));
                        faces.add(new mesh(node8,node4,node13,Color.CYAN));
                    break;
                }
            case pane:
                {
                    faces.clear();
                    System.out.println("pane");
                    double 
                            x1 = -size.x/2,
                            x2 =  size.x/2,
                            z1 = -size.z/2,
                            z2 =  size.z/2,
                            y1 = 0;
                    Vector 
                            node1 = rotateX(rotateY(rotateZ(new Vector(x1, y1, z1),rotate.z),rotate.y),rotate.x),
                            node2 = rotateX(rotateY(rotateZ(new Vector(x2, y1, z1),rotate.z),rotate.y),rotate.x),
                            node3 = rotateX(rotateY(rotateZ(new Vector(x2, y1, z2),rotate.z),rotate.y),rotate.x),
                            node4 = rotateX(rotateY(rotateZ(new Vector(x1, y1, z2),rotate.z),rotate.y),rotate.x),

                            node5 = rotateX(rotateY(rotateZ(new Vector(0, 0, 0),rotate.z),rotate.y),rotate.x);
                    //front
                        faces.add(new mesh(node1,node2,node5,Color.BLUE));
                        faces.add(new mesh(node2,node3,node5,Color.BLUE));
                        faces.add(new mesh(node3,node4,node5,Color.BLUE));
                        faces.add(new mesh(node4,node1,node5,Color.BLUE));
                    break;
                }
        }
    }
    void velosity(double time){
        if(vel.x != 0 | vel.y != 0 | vel.z != 0)
            translate(new Vector(time*vel.x, time*vel.y, time*vel.z));
    }
    void translate(Vector move){
        pos = pos.add(move);
        create();
//        System.out.println(pos.x+"|"+pos.y+"|"+pos.z);
    }
    void rotate3D(Vector r){
        rotate = rotate.add(r);
        create();
    }
    Vector rotateZ(Vector node, double theta) {
        double 
                sinTheta = Math.sin(theta),
                cosTheta = Math.cos(theta),
                x = node.x,y = node.y,z = node.z;
        
        return new Vector(x * cosTheta - y * sinTheta, y * cosTheta + x * sinTheta,z);
    }
    Vector rotateY(Vector node, double theta) {
        double 
                sinTheta = Math.sin(theta),
                cosTheta = Math.cos(theta),
                x = node.x,y = node.y,z = node.z;
        
        return new Vector(x * cosTheta - z * sinTheta, y,z * cosTheta + x * sinTheta);
    }
    Vector rotateX(Vector node, double theta) {
        double 
                sinTheta = Math.sin(theta),
                cosTheta = Math.cos(theta),
                x = node.x,y = node.y,z = node.z;
        
        return new Vector(x, y * cosTheta - z * sinTheta,z * cosTheta + y * sinTheta);
    }
    
}
