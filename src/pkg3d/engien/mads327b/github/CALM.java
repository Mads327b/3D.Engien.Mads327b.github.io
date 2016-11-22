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
public class CALM {
    static double dicimal(double value,int dic){
        return Double.valueOf(String.format("%."+dic+"f", value).replace(",", "."));
    }
}
