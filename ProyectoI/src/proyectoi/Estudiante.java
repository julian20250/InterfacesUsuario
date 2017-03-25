/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;

import java.util.ArrayList;

/**
 *
 * @author julian
 */
public class Estudiante {
    private ArrayList<Materia> materiasInscrito;
    private String nombre;
    public Estudiante(String nombre, Materia materia) {
        this.nombre=nombre;
        materiasInscrito=new ArrayList<>();
        materiasInscrito.add(materia);
    }
    public void addSubject(Materia materia){
        materiasInscrito.add(materia);
    }

    public ArrayList<Materia> getMateriasInscrito() {
        return materiasInscrito;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    @Override
    public String toString(){
        String a="Estudiante: "+getNombre();
        a+="\nMaterias Inscrito:";
        for (Materia m: materiasInscrito)
            a+="\n"+m.getNombre();
        return a;
    }
    
    
}
