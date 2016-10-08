package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import util.Global;
import entities.Food;
import entities.Ground;
import entities.Snake;

public class GamePanel extends JPanel {

	private Snake snake;
	private Food food;
	private Ground ground;

	public void disPlay(Snake snake, Food food, Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
		if (snake != null) {
			this.snake.drawMe(g);
		}
		if (food != null) {
			this.food.drawMe(g);
		}
		if (ground != null) {
			this.ground.drawMe(g);
		}
	}
}
