/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.engien.mads327b.github;

/**
 *
 * @author Mads327b
 */
public class Vector {

    double x,y,z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    Vector add(Vector v){
        double 
                x = this.x+v.x,
                y = this.y+v.y,
                z = this.z+v.z;
        return new Vector(x,y,z);
    }
    Vector sub(Vector v){
        double 
                x = this.x-v.x,
                y = this.y-v.y,
                z = this.z-v.z;
        return new Vector(x,y,z);
    }
    Vector scale(double lambda){
        double 
                x = this.x*lambda,
                y = this.y*lambda,
                z = this.z*lambda;
        return new Vector(x,y,z);
    }
    Vector dotProduct(Vector v){
        double 
                x = this.x*v.x,
                y = this.y*v.y,
                z = this.z*v.z;
        return new Vector(x,y,z);
    }
    double norm(){
        double 
                x = this.x*this.x,
                y = this.y*this.y,
                z = this.z*this.z;
        return Math.sqrt(x+y+z);
    }
    Vector crossProduct(Vector v){
        double 
                x = (this.y*v.z)-(this.z*v.y),
                y = (this.z*v.x)-(this.x*v.z),
                z = (this.x*v.y)-(this.y*v.x);
        return new Vector(x,y,z);
    }
}
