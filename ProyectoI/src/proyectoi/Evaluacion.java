/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;

/**
 *
 * @author julian
 */
public class Evaluacion {
    private int percentage;
    private String name;
    public Evaluacion(String name,int percentage) {
        this.percentage=percentage;
        this.name=name;
    }
    @Override 
    public String toString(){
        return name+" "+percentage+"%";
    }
    
}
