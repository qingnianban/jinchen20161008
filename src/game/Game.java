package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import util.Global;
import view.GamePanel;
import controller.Controller;
import entities.Food;
import entities.Ground;
import entities.Snake;

public class Game {
	public static void main(String[] args) {

		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();

		Controller controller = new Controller(snake, food, ground, gamePanel);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 15, Global.HEIGHT * Global.CELL_SIZE + 35);

		frame.add(gamePanel, BorderLayout.CENTER);

		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		frame.addKeyListener(controller);

		frame.setVisible(true);
		controller.newGame();
	}
}
