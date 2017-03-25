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
    private int nota;
    public Evaluacion(String name,int percentage) {
        this.percentage=percentage;
        this.name=name;
    }
    public Evaluacion(String name,int percentage,int nota){
        this(name,percentage);
        this.nota=nota;
    }
    public Evaluacion cloneMyself(){
        Evaluacion e= new Evaluacion(name, percentage,0);
        return e;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getName() {
        return name;
    }

    public int getNota() {
        return nota;
    }
    
    @Override 
    public String toString(){
        return name+" "+percentage+"%";
    }
    
}
