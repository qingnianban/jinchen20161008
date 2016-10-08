package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import util.Global;

public class Ground {

	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];

	public Ground() {
		for (int x = 0; x < Global.WIDTH; x++) {
			rocks[x][0] = 1;
			rocks[x][Global.HEIGHT - 1] = 1;
		}
		for (int y = 0; y < Global.HEIGHT; y++) {
			rocks[0][y] = 1;
			rocks[Global.WIDTH - 1][y] = 1;
		}
	}

	public Point getPoint() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		} while (rocks[x][y] == 1);
		Point p = new Point(x, y);
		return p;
	}

	public boolean isSnakeEatRock(Snake snake) {
		for (int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (rocks[x][y] == 1 && x == snake.getHead().x && y == snake.getHead().y) {
					return true;
				}
			}
		}
		return false;
	}

	public void drawMe(Graphics g) {
		g.setColor(Color.GRAY);
		for (int x = 0; x < Global.WIDTH; x++) {
			for (int y = 0; y < Global.HEIGHT; y++) {
				if (rocks[x][y] == 1) {
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
}
