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
    private ArrayList<Materia> notas;
    private String nombre;
    public Estudiante(String nombre, Materia materia) {
        this.nombre=nombre;
        materiasInscrito=new ArrayList<>();
        materiasInscrito.add(materia);
        notas=new ArrayList<>();
        notas.add(materia.cloneMyself());
    }
    public void renderNotes(){
        for (int ii=0; ii<notas.size();ii++)
            if(notas.get(ii).getEvaluationCriteria().size()==0 &&
                    materiasInscrito.get(ii).getEvaluationCriteria().size()!=0){
                ArrayList<Evaluacion> tmp=new ArrayList<>();
                for (Evaluacion e: materiasInscrito.get(ii).getEvaluationCriteria())
                    tmp.add(e.cloneMyself());
                notas.get(ii).setEvaluationCriteria(tmp);
            }
                
                
    }
    public void addSubject(Materia materia){
        materiasInscrito.add(materia);
        notas.add(materia.cloneMyself());
    }

    public ArrayList<Materia> getMateriasInscrito() {
        return materiasInscrito;
    }

    public ArrayList<Materia> getNotas() {
        return notas;
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
