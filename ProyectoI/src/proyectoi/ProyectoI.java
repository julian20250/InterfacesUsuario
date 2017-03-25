/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.lang.String;
import java.util.Observable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author ingenieria
 */
public class ProyectoI extends Application {
    private ArrayList<Materia> materias = new ArrayList<>();
    private ArrayList<Estudiante> estudiantes=new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {        
        
        Button btn = new Button();
        btn.setText("Insertar nueva materia");
        Button btn2= new Button();
        btn2.setText("Agregar Estudiante");        
        btn2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                ArrayList<String> nombresMaterias = new ArrayList<>();
                   for (Materia m: materias)
                       nombresMaterias.add(m.getNombre());
                   
                ObservableList<String> options =
                    FXCollections.observableArrayList(nombresMaterias);
                    final ComboBox comboBox = new ComboBox(options);
                if(materias.size()==0)
                    new Warnings("No hay materias a las cuales asignar"
                            + " estudiantes.");
                else{
                   Stage subjectStage = new Stage();
                   GridPane gridS=new GridPane();
                   TextField nombrePelao=new TextField();
                   Button miEstudiante= new Button();
                   miEstudiante.setOnAction(new EventHandler<ActionEvent>(){
                       @Override
                       public void handle(ActionEvent event) {
                           String namae= nombrePelao.getText();
                           int c=0;
                           for (Estudiante e: estudiantes){
                               if(namae.equals(e.getNombre()))
                                   break;
                               c++;
                           }
                           if(c==estudiantes.size()){
                               estudiantes.add(new Estudiante(namae,materias.get(nombresMaterias.indexOf(comboBox.getValue()))));
                           }
                           else{
                               boolean flag=false;
                               for (Materia m: estudiantes.get(c).getMateriasInscrito())
                                   if(m.getNombre().equals(materias.get(nombresMaterias.indexOf(comboBox.getValue())).getNombre()))
                                       flag=true;
                               if(flag)
                                   new Warnings("No tiene sentido agregar dos veces al mismo estudiante en"
                                           + " la misma materia, ¿O sí?");
                                else
                                    estudiantes.get(c).addSubject(materias.get(nombresMaterias.indexOf(comboBox.getValue())));
                           }
                        subjectStage.close();
                       }
                       
                   });
                   miEstudiante.setText("Ingresar");
                   
                   gridS.add(new Label("Estudiante"),0,0);
                   gridS.add(new Label("Materia"),1,0);
                   gridS.add(nombrePelao,0,1);
                   gridS.add(miEstudiante,2,1);
                   
                    comboBox.setVisibleRowCount(5);
                    gridS.add(comboBox,1,1);
                    Scene sceneS=new Scene(gridS,600,300);
                    subjectStage.setTitle("Nueva Materia");
                    subjectStage.setScene(sceneS);
                    subjectStage.show();
                }
                            
            }
    });
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Stage secondaryStage= new Stage();
                GridPane child= new GridPane();
                Label label1= new Label("Nombre de la materia: ");
                TextField textField= new TextField();
                Button agregar= new Button();
                agregar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        materias.add(new Materia(textField.getText()));
                        secondaryStage.close();
                    }
                });
                agregar.setText("Agregar");
                child.add(label1,0,0);
                child.add(textField,1,0);
                child.add(agregar,2,0);
                
                Scene escenaAgregar= new Scene(child,400,200);                
                secondaryStage.setTitle("Añadir Materia");
                secondaryStage.setScene(escenaAgregar);
                secondaryStage.show();
                
            }
        });
        Button testingStudent= new Button();
        testingStudent.setText("Testeo de Estudiantes");
        testingStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(Estudiante e: estudiantes)
                    System.out.println(e);
            }
        });
        
        Button evaluation= new Button();
        evaluation.setText("Registrar Elementos de Evaluación");
        evaluation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> nombresMaterias = new ArrayList<>();
                   for (Materia m: materias)
                       nombresMaterias.add(m.getNombre());
                   
                ObservableList<String> options =
                    FXCollections.observableArrayList(nombresMaterias);
                    final ComboBox comboBox = new ComboBox(options);
                if(materias.size()==0)
                    new Warnings("No hay materias a las cuales asignar"
                            + " elementos de evaluación");
                else{
                    Stage registerStage=new Stage();
                    GridPane registerGrid=new GridPane();
                    TextField elements=new TextField();
                    Button asignar=new Button();
                    registerGrid.add(new Label("Elementos a añadir:"), 0, 0);
                    registerGrid.add(elements, 1,0);
                    registerGrid.add(new Label("Materia"),0,1);
                    registerGrid.add(comboBox,1,1);
                    registerGrid.add(asignar, 1, 2);
                    asignar.setText("Asignar");
                    asignar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Materia subjectAnalyzed=materias.get(nombresMaterias.indexOf(comboBox.getValue()));
                            if(subjectAnalyzed.getEvaluationCriteria()==null){
                                
                            }else
                                new Warnings("Lo siento, debe reiniciar la aplicación si"
                                        + " desea poner nuevos criterios de evaluación ):");
                        }
                    });
                   
                    
                    Scene registerScene = new Scene(registerGrid,400,600);
                    registerStage.setTitle("Añadir Elementos de Evaluación");
                    registerStage.setScene(registerScene);
                    registerStage.show();
                }
            }
        });
        GridPane grid = new GridPane();
        grid.add(btn,0,0);
        grid.add(btn2,1,0);
        grid.add(evaluation,2,0);
        grid.add(testingStudent, 0, 3);
        Scene scene = new Scene(grid, 600, 300);
        
        primaryStage.setTitle("Rajatrón Pro");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
