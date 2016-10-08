package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import listener.SnakeListener;
import view.GamePanel;
import entities.Food;
import entities.Ground;
import entities.Snake;

public class Controller extends KeyAdapter implements SnakeListener {

	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;

	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		System.out.println("hellowords");
		System.out.println("helloword");
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}

	public void snakeMoved(Snake snake) {
		if (food.isSnakeEatFood(snake)) {
			snake.eatFood();
			//获取一个食物的新坐标
			food.newFood(ground.getPoint());
		}
		if (ground.isSnakeEatRock(snake)) {
			snake.die();
		}
		if (snake.isEatBody()) {
			snake.die();
		}

		gamePanel.disPlay(snake, food, ground);
	}

	public void newGame() {
		snake.start();
		food.newFood(ground.getPoint());
	}
}
