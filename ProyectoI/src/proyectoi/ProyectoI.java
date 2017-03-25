/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;

import java.util.ArrayList;
import java.lang.String;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
                    gridS.add(new Label(""),0,4);
                    gridS.add(new Label("Para repetir un estudiante en otra materia,"
                            + " escriba el nombre idéntico a como lo hizo antes."),0,2,3,2);
                    Scene sceneS=new Scene(gridS,700,300);
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
                comboBox.setVisibleRowCount(5);
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
                    registerGrid.add(asignar, 2, 2);
                    asignar.setText("Asignar");
                    asignar.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Materia subjectAnalyzed=materias.get(nombresMaterias.indexOf(comboBox.getValue()));
                            
                            if(subjectAnalyzed.getEvaluationCriteria().size()==0){
                                boolean newFlag= true;
                                int number=0;
                                try{
                                    number=Integer.parseInt(elements.getText());
                                }catch(Exception e){
                                    new Warnings("Probablemente no ingresó un número,"
                                            + " ¿Tantas ganas hay de reventar el programita?");
                                    newFlag=false;
                                }
                                if(newFlag){
                                    if (number<1)
                                        new Warnings("El número debe ser mayor o igual que 1, obvio");
                                    else{
                                        ArrayList<TextField> campitosTextoP= new ArrayList<>();
                                        ArrayList<TextField> campitosTextoN= new ArrayList<>();
                                        registerGrid.add(new Label("Porcentaje"), 0,3);
                                        registerGrid.add(new Label("Nombre"),1,3);
                                        int ii;
                                        for(ii=0; ii<number;ii++){
                                            campitosTextoP.add(new TextField());
                                            campitosTextoN.add(new TextField());
                                            registerGrid.add(campitosTextoP.get(ii), 0, 4+ii);
                                            registerGrid.add(campitosTextoN.get(ii), 1, 4+ii);                                            
                                        }
                                        Button finalPart= new Button();
                                        finalPart.setText("Ingresar");
                                        finalPart.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                try{
                                                    int counter=0;
                                                    for(int jj=0; jj<campitosTextoP.size(); jj++)
                                                        counter+=Integer.parseInt(campitosTextoP.get(jj).getText());  
                                                    if (counter!=100)
                                                        new Warnings("No te puedo dejar pasar si la suma de "
                                                                + "porcentajes no da 100 exacto, sorry xD.");
                                                    else{
                                                        for(int jj=0; jj<campitosTextoP.size(); jj++){
                                                            subjectAnalyzed.addEvaluation(new Evaluacion(campitosTextoN.get(jj).getText(),Integer.parseInt(campitosTextoP.get(jj).getText()))); 
                                                        }
                                                        registerStage.close();
                                                    }
                                                }catch(Exception e){
                                                    new Warnings("Probablemente algún porcentaje no es"
                                                            + " un número.");
                                                }
                                            }
                                        });
                                        registerGrid.add(finalPart, 2, 5+ii);
                                    }
                                }
                            }else
                                new Warnings("Lo siento, debe reiniciar la aplicación si"
                                        + " desea poner nuevos criterios de evaluación ):");
                        }
                    });
                   
                    
                    Scene registerScene = new Scene(registerGrid,500,600);
                    registerStage.setTitle("Añadir Elementos de Evaluación");
                    registerStage.setScene(registerScene);
                    registerStage.show();
                }
            }
        });
        Button testEvaluation= new Button();
        testEvaluation.setText("Testeo de Materias");
        testEvaluation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (Materia m : materias)
                    System.out.println(m);
            }
        });
        Button notes= new Button();
        notes.setText("Registrar notas");
        notes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage notesStage= new Stage();
                GridPane notesGrid=new GridPane();
                
                ArrayList<String> nombresEstudiantes = new ArrayList<>();
                   for (Estudiante m: estudiantes)
                       nombresEstudiantes.add(m.getNombre());
                   
                ObservableList<String> options =
                    FXCollections.observableArrayList(nombresEstudiantes);
                final ComboBox comboBox = new ComboBox(options);
                comboBox.setVisibleRowCount(5);
                
                notesGrid.add(new Label("Estudiante:"),0,0);
                notesGrid.add(comboBox,1,0);
                comboBox.valueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Estudiante actualStudent=estudiantes.get(nombresEstudiantes.indexOf(comboBox.getValue()));
                        actualStudent.renderNotes();
                        ArrayList<Materia> validOnes= new ArrayList<>();
                        for (Materia m: actualStudent.getNotas())
                            if(m.getEvaluationCriteria().size()!=0)
                                validOnes.add(m);
                        if(validOnes.size()==0){
                            new Warnings("No hay materias con posible "
                                    + "registro de notas para "+comboBox.getValue());
                            notesStage.close();
                        }
                        else{
                            ArrayList<String> nombresMaterias =new ArrayList<>();
                            for (Materia m: validOnes)
                                nombresMaterias.add(m.getNombre());
                            ObservableList<String> options2 =
                                FXCollections.observableArrayList(nombresMaterias);
                            final ComboBox comboBox2 = new ComboBox(options2);
                            comboBox2.setVisibleRowCount(5);
                            notesGrid.add(new Label("Materia:"),0,1);
                            notesGrid.add(comboBox2,1,1);
                            comboBox2.valueProperty().addListener(new ChangeListener() {
                                @Override
                                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                    Materia actualSubject=validOnes.get(nombresMaterias.indexOf(comboBox2.getValue()));
                                    ArrayList<String> nombreEvaluaciones= new ArrayList<>();
                                    for (Evaluacion e: actualSubject.getEvaluationCriteria())
                                        nombreEvaluaciones.add(e.getName());
                                    ObservableList<String> options3 =
                                        FXCollections.observableArrayList(nombreEvaluaciones);
                                    final ComboBox comboBox3 = new ComboBox(options3);
                                    comboBox3.setVisibleRowCount(5);
                                    notesGrid.add(new Label("Evaluación:"),0,2);
                                    notesGrid.add(comboBox3,1,2);
                                    comboBox3.valueProperty().addListener(new ChangeListener() {
                                        @Override
                                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                            Evaluacion actualEva=actualSubject.getEvaluationCriteria().get(nombreEvaluaciones.indexOf(comboBox3.getValue()));
                                            TextField valley = new TextField();
                                            Button ending= new Button();
                                            ending.setText("Poner Nota");
                                            notesGrid.add(new Label("Nota (0-50):"),0,3);
                                            notesGrid.add(valley,1,3);
                                            notesGrid.add(ending, 1, 4);
                                            ending.setOnAction(new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    try{
                                                        int n=Integer.parseInt(valley.getText());
                                                        if (n>50 || n<0)
                                                            new Warnings("La nota debe estar entre 0 y 50.");
                                                        else{     
                                                            actualEva.setNota(n);
                                                            notesStage.close();
                                                        }
                                                    }catch(Exception e){
                                                        new Warnings("Creo que atrapé un objeto no número.");    
                                                        
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            
                Scene notesScene = new Scene(notesGrid,600,300);
                notesStage.setTitle("Registrar Notas");
                notesStage.setScene(notesScene);
                notesStage.show();
            }
        });
        Button mean = new Button();
        mean.setText("Promedio para un Elemento de Evaluación");
        mean.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage meanStage=new Stage();
                GridPane meanGrid= new GridPane();
                ArrayList<Materia> subEv= new ArrayList<>(); /** materias con evaluación */
                ArrayList<String> nombresMaterias = new ArrayList<>();
                for (Materia m: materias)
                    if(m.getEvaluationCriteria().size()!=0){
                        subEv.add(m);
                        nombresMaterias.add(m.getNombre());
                    }
                if (subEv.size()==0){
                    new Warnings("No hay materias con evaluación.");
                    meanStage.close();
                }
                ObservableList<String> options =
                    FXCollections.observableArrayList(nombresMaterias);
                final ComboBox comboBox = new ComboBox(options);
                comboBox.setVisibleRowCount(5);
                meanGrid.add(new Label("Materia: "),0,0);
                meanGrid.add(comboBox, 1, 0);
                comboBox.valueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Materia actualSubject=subEv.get(nombresMaterias.indexOf(comboBox.getValue()));
                        ArrayList<String> nombreEvaluaciones=new ArrayList<>();
                        for(Evaluacion e: actualSubject.getEvaluationCriteria())
                            nombreEvaluaciones.add(e.getName());
                        ObservableList<String> options2 =
                            FXCollections.observableArrayList(nombreEvaluaciones);
                        final ComboBox comboBox2 = new ComboBox(options2);
                        comboBox2.setVisibleRowCount(5);
                        meanGrid.add(new Label("Evaluación: "),0,1);
                        meanGrid.add(comboBox2, 1, 1);
                        comboBox2.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                float promedio=0;
                                int number=0;
                                for (Estudiante e: estudiantes)
                                    for(Materia m: e.getNotas())
                                        if(m.getNombre().equals(comboBox.getValue()))
                                            for (Evaluacion ev: m.getEvaluationCriteria())
                                                if(ev.getName().equals(comboBox2.getValue())){
                                                    number++;
                                                    promedio+=ev.getNota();
                                                }
                                promedio=(float)promedio/number;
                                meanGrid.add(new Label("Promedio: "+promedio),0,2,2,2);             
                                
                            }
                        });
                        
                    }
                });
                
                Scene meanScene= new Scene(meanGrid,400,300);
                meanStage.setTitle("Promedio para un Elemento de Evaluación");
                meanStage.setScene(meanScene);
                meanStage.show();
            }
        });
        Button deviation = new Button();
        deviation.setText("Desviación para un Elemento de Evaluación");
        deviation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage meanStage=new Stage();
                GridPane meanGrid= new GridPane();
                ArrayList<Materia> subEv= new ArrayList<>(); /** materias con evaluación */
                ArrayList<String> nombresMaterias = new ArrayList<>();
                for (Materia m: materias)
                    if(m.getEvaluationCriteria().size()!=0){
                        subEv.add(m);
                        nombresMaterias.add(m.getNombre());
                    }
                if (subEv.size()==0){
                    new Warnings("No hay materias con evaluación.");
                    meanStage.close();
                }
                ObservableList<String> options =
                    FXCollections.observableArrayList(nombresMaterias);
                final ComboBox comboBox = new ComboBox(options);
                comboBox.setVisibleRowCount(5);
                meanGrid.add(new Label("Materia: "),0,0);
                meanGrid.add(comboBox, 1, 0);
                comboBox.valueProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Materia actualSubject=subEv.get(nombresMaterias.indexOf(comboBox.getValue()));
                        ArrayList<String> nombreEvaluaciones=new ArrayList<>();
                        for(Evaluacion e: actualSubject.getEvaluationCriteria())
                            nombreEvaluaciones.add(e.getName());
                        ObservableList<String> options2 =
                            FXCollections.observableArrayList(nombreEvaluaciones);
                        final ComboBox comboBox2 = new ComboBox(options2);
                        comboBox2.setVisibleRowCount(5);
                        meanGrid.add(new Label("Evaluación: "),0,1);
                        meanGrid.add(comboBox2, 1, 1);
                        comboBox2.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                float promedio=0;
                                int number=0;
                                for (Estudiante e: estudiantes)
                                    for(Materia m: e.getNotas())
                                        if(m.getNombre().equals(comboBox.getValue()))
                                            for (Evaluacion ev: m.getEvaluationCriteria())
                                                if(ev.getName().equals(comboBox2.getValue())){
                                                    number++;
                                                    promedio+=ev.getNota();
                                                }
                                promedio=(float)promedio/number;
                                double deviation=0;
                                for (Estudiante e: estudiantes)
                                    for(Materia m: e.getNotas())
                                        if(m.getNombre().equals(comboBox.getValue()))
                                            for (Evaluacion ev: m.getEvaluationCriteria())
                                                if(ev.getName().equals(comboBox2.getValue())){
                                                    number++;
                                                    deviation+=Math.pow(ev.getNota()-promedio,2);
                                                }
                                deviation=Math.sqrt(deviation/number);
                                meanGrid.add(new Label("Desviación: "+deviation),0,2,2,2);             
                                
                            }
                        });
                        
                    }
                });
                
                Scene meanScene= new Scene(meanGrid,400,300);
                meanStage.setTitle("Promedio para un Elemento de Evaluación");
                meanStage.setScene(meanScene);
                meanStage.show();
            }
        });
        GridPane grid = new GridPane();
        grid.add(btn,0,0);
        grid.add(btn2,1,0);
        grid.add(evaluation,2,0);                
        grid.add(notes,0,1);
        grid.add(mean,1,1);
        grid.add(deviation,2,1);
        
        grid.add(new Label(""),0,2);
        grid.add(new Label("Zona de Testeos"),0,3);
        grid.add(testingStudent, 0, 4);
        grid.add(testEvaluation,1,4);
        Scene scene = new Scene(grid, 800, 300);
        
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