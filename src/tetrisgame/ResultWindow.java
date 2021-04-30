/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ResultWindow {
    public TableView<ResultRow> table;
    public Tetris t;
    public Scene scene;
    
    public void InsertResult(String name, int score){
        DB.Open();
        
        try{
            Statement s = DB.con.createStatement();
            
            PreparedStatement ps = DB.con.prepareStatement("Insert into Score values (?, ?)");
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        DB.Close();
    }
    
    public void start(Stage stage, Pane group) throws Exception {
        stage.setOnHiding( event -> {System.exit(0);} );
        stage.setTitle("Result Tetris Game");
        
        DB.Open();
        
        try{
            Statement s = DB.con.createStatement();
            ResultSet rs = s.executeQuery("Select * from Score order by Score DESC");
            DefaultTableModel model = null;
            
            int i = 1;
            ObservableList<ResultRow> Results = FXCollections.observableArrayList();
            
            while(rs.next()){
                Results.add(new ResultRow(i, rs.getString(1), rs.getInt(2)));
                i++;
            }
            
            TableColumn<ResultRow, String> rankColumn  = new TableColumn<ResultRow, String>("Rank");
            rankColumn.setMinWidth(150);
            rankColumn.setCellValueFactory(new PropertyValueFactory<ResultRow, String>("rank"));

            TableColumn<ResultRow, String> nameColumn  = new TableColumn<ResultRow, String>("Name");
            nameColumn.setMinWidth(150);
            nameColumn.setCellValueFactory(new PropertyValueFactory<ResultRow, String>("name"));

            TableColumn<ResultRow, String> scoreColumn  = new TableColumn<ResultRow, String>("Score");
            scoreColumn.setMinWidth(150);
            scoreColumn.setCellValueFactory(new PropertyValueFactory<ResultRow, String>("score"));
            
            table = new TableView<>();
            table.setItems(Results);
            table.getColumns().addAll(rankColumn, nameColumn, scoreColumn);
            
            Button btnReplay = new Button("Chơi lại");
            btnReplay.setMinWidth(90);
            btnReplay.setMinHeight(60);
            btnReplay.setLayoutX(170);
            btnReplay.setLayoutY(450);
            
            group.getChildren().clear();
            group.getChildren().addAll(table, btnReplay);
            
            btnReplay.setOnAction(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent event) {
                    Tetris.reStart(stage);
                }
            });
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        DB.Close();
        
    }
    
//    public static void main(String[] args){
//        launch(args);
//    }

}
