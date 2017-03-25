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

    public void setEvaluationCriteria(ArrayList<Evaluacion> evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public ArrayList<Evaluacion> getEvaluationCriteria() {
        return evaluationCriteria;
    }
    
    
}
