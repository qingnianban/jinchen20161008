package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import util.Global;

public class Food extends Point {

	public void newFood(Point p) {
		this.setLocation(p);
	}

	public boolean isSnakeEatFood(Snake snake) {
		return this.equals(snake.getHead());
	}

	public void drawMe(Graphics g) {
		g.setColor(Color.RED);
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
