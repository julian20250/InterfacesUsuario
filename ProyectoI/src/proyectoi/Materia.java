/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;

import java.util.ArrayList;

/**
 *
 * @author ingenieria
 */
public class Materia {
    private String nombre;
    private ArrayList<Evaluacion> evaluationCriteria =new ArrayList<>();
    public Materia(String nombre) {
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }


    public void addEvaluation(Evaluacion e){
        evaluationCriteria.add(e);
    }

    public ArrayList<Evaluacion> getEvaluationCriteria() {
        return evaluationCriteria;
    }
    @Override
    public String toString(){
        String a="Materia "+this.getNombre();
        a+="\nCriterios de Evaluación:";
        for (Evaluacion e: evaluationCriteria)
            a+="\n"+e.toString();
        return a;
    }
    
    
}
