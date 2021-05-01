/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class Tetris {
    public static int MOVE = 25;
    public static int SIZE = 25;
    public static int XMAX = SIZE * 12;
    public static int YMAX = SIZE * 24;
    public static int [][] MESH = new int [XMAX/SIZE][YMAX/SIZE];
    public static Pane group = new Pane();
    public static Scene scene = new Scene(group, XMAX + 150, YMAX);
    public static Form object;
    public static Form nextObject = Controller.makeRect();
    public static int score = 0;
    public static boolean game = true;
    public static int linesNo = 0;
    public static int loop = 0;
    
    public static Text levelText;
    public static Line line;
    public static Text scoreText;
    
    public static Timer time;
    public static TimerTask taskTimer;
    
    public static String name;
    
    
    public void start(Stage stage, String name) throws Exception {
        stage.setOnHiding( event -> {System.exit(0);} );
        
        this.name = name;
        
        levelText = new Text("Lines: ");
        line = new Line(XMAX, 0, XMAX, YMAX);
        scoreText = new Text("Score: ");
        
        
        scoreText.setStyle("-fx-font: 25 arials;");
        scoreText.setY(50);
        scoreText.setX(XMAX + 5);
        levelText.setStyle("-fx-font: 25 arials;");
        levelText.setY(100);
        levelText.setX(XMAX + 5);
        
        reStart(stage);
        
        stage.setScene(scene);
        stage.setTitle("TETRIS GAME");
        stage.show();
    }
    
    public static void createTimer(Stage stage) {
        time = new Timer();
        taskTimer = new TimerTask(){
            public void run(){
                Platform.runLater(new Runnable(){
                    public void run(){
                        if(object.a.getY() == 0 || object.b.getY() == 0 || object.c.getY() == 0 || object.d.getY() == 0)
                            loop++;
                        else loop = 0;

                        if(loop == 3){
                            Text gameOver = new Text("Game Over");
                            gameOver.setStyle("-fx-font: 50 arials;");
                            gameOver.setY(250);
                            gameOver.setX(20);
                            group.getChildren().add(gameOver);
                            game = false;
                        }

                        if(loop == 5){
                            time.cancel();
                            time.purge();
                            
                            ResultWindow resultForm = new ResultWindow();
                            resultForm.InsertResult(name, score);
                            try {
                                resultForm.start(stage, group);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                        if(game){
                            Controller.moveDown(object);
                            scoreText.setText("Score: " + Integer.toString(score));
                            levelText.setText("Lines: " + Integer.toString(linesNo));
                        }
                    }
                });
            }
        };
        
        time.schedule(taskTimer,0 , 300);
    }
    
    public static void moveObject(Form form) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case RIGHT:
                        Controller.moveRight(object);
                        break;
                    case LEFT:
                        Controller.moveLeft(object);
                        break;
                    case DOWN:
                        Controller.moveDown(form);
                        score++;
                        break;
                    case UP:
                        break;
                    case SPACE:
                        Controller.moveTurn(form);
                        break;
                }
            }
        });
    }
    
    public static void reStart(Stage stage){
        group.getChildren().clear();
        
        for(int[] a:MESH) {
            Arrays.fill(a, 0);
        }
        
        score = 0;
        linesNo = 0;

        group.getChildren().addAll(line, scoreText, levelText);

        Form a = nextObject;
        group.getChildren().addAll(a.a, a.b, a.c, a.d);
        moveObject(a);
        object = a;
        nextObject = Controller.makeRect();
        
        game = true;
        createTimer(stage);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }
}
