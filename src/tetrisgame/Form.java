/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ADMIN
 */
public class Form {
    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;
    
    public Color color;
    
    public String name;
    public int form = 1;
    
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public Form(Rectangle a, Rectangle b, Rectangle c, Rectangle d, String name){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
        
        color = Color.DARKGOLDENROD;
        
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }
    
    public void changeForm() {
        if(form != 4){
            form++;
        }else{
            form = 1;
        }
    }
    
    
}
