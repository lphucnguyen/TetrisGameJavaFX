/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import java.awt.Component;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static tetrisgame.Tetris.group;

/**
 *
 * @author ADMIN
 */
public class WelcomeWindow extends Application {
    public void start(Stage stage) throws Exception {
        stage.setOnHiding( event -> {System.exit(0);} );
        
        stage.setTitle("Tetris Game Start");
        
        Text name = new Text("Họ tên: Lê Phúc Nguyên");
        name.setLayoutX(30);
        name.setLayoutY(30);
        Text mssv = new Text("MSSV: B1805794");
        mssv.setLayoutX(30);
        mssv.setLayoutY(80);
        
        
        TextField txtName = new TextField();
        txtName.setMinWidth(100);
        txtName.setLayoutX(30);
        txtName.setLayoutY(100);
        
        Button btnStart = new Button("Start");
        btnStart.setMinWidth(100);
        btnStart.setLayoutX(30);
        btnStart.setLayoutY(150);
        
        Alert alert = new Alert(AlertType.WARNING);

        Pane group = new Pane();
        group.getChildren().addAll(txtName, btnStart, name, mssv);
        
        Scene scene = new Scene(group, 400, 200);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        
        btnStart.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if(txtName.getText().isEmpty()){
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Chưa nhập tên");
                    alert.showAndWait();
                    return;
                }
                
                Tetris t = new Tetris();
                try {
                    t.start(stage, txtName.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
