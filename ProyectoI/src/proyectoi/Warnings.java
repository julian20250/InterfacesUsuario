/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *
 * @author ingenieria
 */
public class Warnings {
    public Warnings(String advice){
        Stage warningStage=new Stage();
        Label primera= new Label(advice);
        Button button = new Button();
        GridPane grid = new GridPane();
        button.setText("Lo he entendido");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                warningStage.close();
            }
        });
        grid.add(primera,0,0);
        grid.add(button,1,1);
        warningStage.setTitle("Advertencia");
        Scene scene = new Scene(grid, 800, 250);
        warningStage.setScene(scene);
        warningStage.show();
    }
    
}
