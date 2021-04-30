 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ADMIN
 */
public class Controller {
    public static int XMAX = Tetris.XMAX;
    public static int YMAX = Tetris.YMAX;
    public static int SIZE = Tetris.SIZE;
    public static int MOVE = Tetris.MOVE;
    public static int [][] MESH = Tetris.MESH;
    
    //Di chuyển Form
    
    public static void moveRight(Form form) {
        if(form.a.getX() + MOVE <= XMAX - SIZE && form.b.getX() + MOVE <= XMAX - SIZE && form.c.getX() + MOVE <= XMAX - SIZE
                && form.d.getX() + MOVE <= XMAX - SIZE){
            int moveA = MESH[((int)form.a.getX() / SIZE) + 1][(int)form.a.getY() / SIZE];
            int moveB = MESH[((int)form.b.getX() / SIZE) + 1][(int)form.b.getY() / SIZE];
            int moveC = MESH[((int)form.c.getX() / SIZE) + 1][(int)form.c.getY() / SIZE];
            int moveD = MESH[((int)form.d.getX() / SIZE) + 1][(int)form.d.getY() / SIZE];
            
            if(moveA == 0 && moveB == 0 && moveC == 0 && moveD == 0){
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }
        }
    }
    
    public static void moveLeft(Form form) {
        if(form.a.getX() - MOVE >= 0 && form.b.getX() - MOVE >= 0 && form.c.getX() - MOVE >= 0
                && form.d.getX() - MOVE >= 0 ){
            int moveA = MESH[((int)form.a.getX() / SIZE) - 1][(int)form.a.getY() / SIZE];
            int moveB = MESH[((int)form.b.getX() / SIZE) - 1][(int)form.b.getY() / SIZE];
            int moveC = MESH[((int)form.c.getX() / SIZE) - 1][(int)form.c.getY() / SIZE];
            int moveD = MESH[((int)form.d.getX() / SIZE) - 1][(int)form.d.getY() / SIZE];
            
            if(moveA == 0 && moveB == 0 && moveC == 0 && moveD == 0){
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
            }
        }
    }
    
    public static void moveDown(Form form){
        if (form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE
				|| form.d.getY() == YMAX - SIZE || checkmoveRect(form.a) || checkmoveRect(form.b) || checkmoveRect(form.c) || checkmoveRect(form.d)) {
                MESH[(int) form.a.getX() / SIZE][(int) form.a.getY() / SIZE] = 1;
                MESH[(int) form.b.getX() / SIZE][(int) form.b.getY() / SIZE] = 1;
                MESH[(int) form.c.getX() / SIZE][(int) form.c.getY() / SIZE] = 1;
                MESH[(int) form.d.getX() / SIZE][(int) form.d.getY() / SIZE] = 1;
                RemoveRows(Tetris.group);

                Form a = Tetris.nextObject;
                Tetris.nextObject = Controller.makeRect();
                Tetris.object = a;
                Tetris.group.getChildren().addAll(a.a, a.b, a.c, a.d);
                Tetris.moveObject(a);
        }

        if (form.a.getY() + MOVE < YMAX && form.b.getY() + MOVE < YMAX && form.c.getY() + MOVE < YMAX
                        && form.d.getY() + MOVE < YMAX) {
                int movea = MESH[(int) form.a.getX() / SIZE][((int) form.a.getY() / SIZE) + 1];
                int moveb = MESH[(int) form.b.getX() / SIZE][((int) form.b.getY() / SIZE) + 1];
                int movec = MESH[(int) form.c.getX() / SIZE][((int) form.c.getY() / SIZE) + 1];
                int moved = MESH[(int) form.d.getX() / SIZE][((int) form.d.getY() / SIZE) + 1];
                if (movea == 0 && movea == moveb && moveb == movec && movec == moved) {
                        form.a.setY(form.a.getY() + MOVE);
                        form.b.setY(form.b.getY() + MOVE);
                        form.c.setY(form.c.getY() + MOVE);
                        form.d.setY(form.d.getY() + MOVE);
                }
        }
    }
    
    public static void moveTurn(Form form) {
		int f = form.form;
		Rectangle a = form.a;
		Rectangle b = form.b;
		Rectangle c = form.c;
		Rectangle d = form.d;
		switch (form.name) {
		case "j":
			if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
				moveRectRight(form.a);
				moveRectDown(form.a);
				moveRectDown(form.c);
				moveRectLeft(form.c);
				moveRectDown(form.d);
				moveRectDown(form.d);
				moveRectLeft(form.d);
				moveRectLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				moveRectLeft(form.d);
				moveRectLeft(form.d);
				moveRectUp(form.d);
				moveRectUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
				moveRectLeft(form.a);
				moveRectUp(form.a);
				moveRectUp(form.c);
				moveRectRight(form.c);
				moveRectUp(form.d);
				moveRectUp(form.d);
				moveRectRight(form.d);
				moveRectRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectRight(form.c);
				moveRectDown(form.c);
				moveRectRight(form.d);
				moveRectRight(form.d);
				moveRectDown(form.d);
				moveRectDown(form.d);
				form.changeForm();
				break;
			}
			break;
		case "l":
			if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
				moveRectRight(form.a);
				moveRectDown(form.a);
				moveRectUp(form.c);
				moveRectRight(form.c);
				moveRectUp(form.b);
				moveRectUp(form.b);
				moveRectRight(form.b);
				moveRectRight(form.b);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectRight(form.b);
				moveRectRight(form.b);
				moveRectDown(form.b);
				moveRectDown(form.b);
				moveRectRight(form.c);
				moveRectDown(form.c);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
				moveRectLeft(form.a);
				moveRectUp(form.a);
				moveRectDown(form.c);
				moveRectLeft(form.c);
				moveRectDown(form.b);
				moveRectDown(form.b);
				moveRectLeft(form.b);
				moveRectLeft(form.b);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectLeft(form.b);
				moveRectLeft(form.b);
				moveRectUp(form.b);
				moveRectUp(form.b);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				form.changeForm();
				break;
			}
			break;
		case "o":
			break;
		case "s":
			if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				moveRectUp(form.d);
				moveRectUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectRight(form.c);
				moveRectDown(form.c);
				moveRectDown(form.d);
				moveRectDown(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				moveRectUp(form.d);
				moveRectUp(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectRight(form.c);
				moveRectDown(form.c);
				moveRectDown(form.d);
				moveRectDown(form.d);
				form.changeForm();
				break;
			}
			break;
		case "t":
			if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectDown(form.d);
				moveRectLeft(form.d);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
				moveRectRight(form.a);
				moveRectDown(form.a);
				moveRectLeft(form.d);
				moveRectUp(form.d);
				moveRectUp(form.c);
				moveRectRight(form.c);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectUp(form.d);
				moveRectRight(form.d);
				moveRectRight(form.c);
				moveRectDown(form.c);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
				moveRectLeft(form.a);
				moveRectUp(form.a);
				moveRectRight(form.d);
				moveRectDown(form.d);
				moveRectDown(form.c);
				moveRectLeft(form.c);
				form.changeForm();
				break;
			}
			break;
		case "z":
			if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
				moveRectUp(form.b);
				moveRectRight(form.b);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				moveRectLeft(form.d);
				moveRectLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
				moveRectDown(form.b);
				moveRectLeft(form.b);
				moveRectRight(form.c);
				moveRectDown(form.c);
				moveRectRight(form.d);
				moveRectRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
				moveRectUp(form.b);
				moveRectRight(form.b);
				moveRectLeft(form.c);
				moveRectUp(form.c);
				moveRectLeft(form.d);
				moveRectLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
				moveRectDown(form.b);
				moveRectLeft(form.b);
				moveRectRight(form.c);
				moveRectDown(form.c);
				moveRectRight(form.d);
				moveRectRight(form.d);
				form.changeForm();
				break;
			}
			break;
		case "i":
			if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
				moveRectUp(form.a);
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectRight(form.a);
				moveRectUp(form.b);
				moveRectRight(form.b);
				moveRectDown(form.d);
				moveRectLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
				moveRectDown(form.a);
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectLeft(form.a);
				moveRectDown(form.b);
				moveRectLeft(form.b);
				moveRectUp(form.d);
				moveRectRight(form.d);
				form.changeForm();
				break;
			}
			if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
				moveRectUp(form.a);
				moveRectUp(form.a);
				moveRectRight(form.a);
				moveRectRight(form.a);
				moveRectUp(form.b);
				moveRectRight(form.b);
				moveRectDown(form.d);
				moveRectLeft(form.d);
				form.changeForm();
				break;
			}
			if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
				moveRectDown(form.a);
				moveRectDown(form.a);
				moveRectLeft(form.a);
				moveRectLeft(form.a);
				moveRectDown(form.b);
				moveRectLeft(form.b);
				moveRectUp(form.d);
				moveRectRight(form.d);
				form.changeForm();
				break;
			}
			break;
		}
	}
    
    public static boolean cB(Rectangle rect, int x, int y) {
            boolean xb = false;
            boolean yb = false;
            if (x >= 0)
                    xb = rect.getX() + x * MOVE <= XMAX - SIZE;
            if (x < 0)
                    xb = rect.getX() + x * MOVE >= 0;
            if (y >= 0)
                    yb = rect.getY() - y * MOVE > 0;
            if (y < 0)
                    yb = rect.getY() + y * MOVE < YMAX;
            return xb && yb && MESH[((int) rect.getX() / SIZE) + x][((int) rect.getY() / SIZE) - y] == 0;
    }
    
    //Di chuyển Rectangle A, B, C, D
    
    public static void moveRectDown(Rectangle a){
        if(a.getY() + SIZE < YMAX){
            a.setY(a.getY() + SIZE);
        }
    }
    
    public static void moveRectUp(Rectangle a){
        if(a.getY() - SIZE >= 0){
            a.setY(a.getY() - SIZE);
        }
    }
    
    public static void moveRectLeft(Rectangle a){
        if(a.getX() - SIZE >= 0){
            a.setX(a.getX() - SIZE);
        }
    }
    
    public static void moveRectRight(Rectangle a){
        if(a.getX() + SIZE < YMAX){
            a.setX(a.getX() + SIZE);
        }
    }
    
    //Kiểm tra khi di chuyển Rectangle A, B, C, D khi Di chuyển form xuống
    
    public static boolean checkmoveRect(Rectangle a){
        return MESH[((int)a.getX() / SIZE)][((int)a.getY() / SIZE) + 1] == 1;
    }
    
    public static Form makeRect() {
        int block = (int) (Math.random() * 100);
        String name;
        Rectangle a = new Rectangle(SIZE-1, SIZE-1), b = new Rectangle(SIZE-1, SIZE-1), c = new Rectangle(SIZE-1, SIZE-1),
                        d = new Rectangle(SIZE-1, SIZE-1);
        if (block < 15) { 
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                name = "j";
        } else if (block < 30) { 
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2 - SIZE);
                b.setY(SIZE);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                d.setY(SIZE);
                name = "l";
        } else if (block < 45) { 
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 - SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2);
                d.setY(SIZE);
                name = "o";
        } else if (block < 60) { 
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 - SIZE);
                d.setY(SIZE);
                name = "s";
        } else if (block < 75) { 
                a.setX(XMAX / 2 - SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE);
                name = "t";
        } else if (block < 90) { 
                a.setX(XMAX / 2 + SIZE);
                b.setX(XMAX / 2);
                c.setX(XMAX / 2 + SIZE);
                c.setY(SIZE);
                d.setX(XMAX / 2 + SIZE + SIZE);
                d.setY(SIZE);
                name = "z";
        } else { 
                a.setX(XMAX / 2 - SIZE - SIZE);
                b.setX(XMAX / 2 - SIZE);
                c.setX(XMAX / 2);
                d.setX(XMAX / 2 + SIZE);
                name = "i";
        }
        return new Form(a, b, c, d, name);
    }
    
    //Xóa row
    public static void RemoveRows(Pane pane) {
        ArrayList<Node> rects = new ArrayList<Node>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        ArrayList<Node> newrects = new ArrayList<Node>();
        int full = 0;
        for (int i = 0; i < MESH[0].length; i++) {
                for (int j = 0; j < MESH.length; j++) {
                        if (MESH[j][i] == 1)
                                full++;
                }
                if (full == MESH.length)
                lines.add(i);
                //lines.add(i + lines.size());
                full = 0;
        }
        if (lines.size() > 0)
                do {
                        for (Node node : pane.getChildren()) {
                                if (node instanceof Rectangle)
                                        rects.add(node);
                        }
                        Tetris.score += 50;
                        Tetris.linesNo++;

                        for (Node node : rects) {
                                Rectangle a = (Rectangle) node;
                                if (a.getY() == lines.get(0) * SIZE) {
                                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                                        pane.getChildren().remove(node);
                                } else
                                        newrects.add(node);
                        }

                        for (Node node : newrects) {
                                Rectangle a = (Rectangle) node;
                                if (a.getY() < lines.get(0) * SIZE) {
                                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                                        a.setY(a.getY() + SIZE);
                                }
                        }
                        lines.remove(0);
                        rects.clear();
                        newrects.clear();
                        for (Node node : pane.getChildren()) {
                                if (node instanceof Rectangle)
                                        rects.add(node);
                        }
                        for (Node node : rects) {
                                Rectangle a = (Rectangle) node;
                                try {
                                        MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 1;
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                        }
                        rects.clear();
                } while (lines.size() > 0);
	}
    
}
