package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import listener.SnakeListener;
import util.Global;

public class Snake {
	//表示方向
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = -2;
	public static final int RIGHT = 2;

	private boolean live;
	//表示新旧的方向
	private int oldDirection, newDirection;
	
	private Point oldTail;

	private LinkedList<Point> body = new LinkedList<Point>();

	private Set<SnakeListener> listener = new HashSet<SnakeListener>();

	public Snake() {
		init();
	}

	public void init() {
		live = true;
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		for (int i = 0; i < 2; i++) {
			body.addLast(new Point(x--, y));
		}
		oldDirection = newDirection = RIGHT;
	}

	public void die() {
		live = false;
	}

	public void move() {
		if (!(oldDirection + newDirection == 0)) {
			oldDirection = newDirection;
		}
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch (oldDirection) {
		case UP:
			y--;
			if (y < 0) {
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			if (y >= Global.HEIGHT) {
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if (x < 0) {
				x = Global.WIDTH - 1;
			}
			break;
		case RIGHT:
			x++;
			if (x >= Global.WIDTH) {
				x = 0;
			}
			break;
		}
		Point newHead = new Point(x, y);
		body.addFirst(newHead);
		oldTail = body.removeLast();
	}

	public void changeDirection(int direction) {
		newDirection = direction;
	}

	public void eatFood() {
		body.addLast(oldTail);
	}

	public boolean isEatBody() {
		for (int i = 1; i < body.size(); i++) {
			if (body.get(i).equals(this.getHead())) {
				return true;
			}
		}
		return false;
	}

	public void drawMe(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for (Point p : body) {
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
	}

	public Point getHead() {
		return body.getFirst();
	}

	private class SnakeDriver implements Runnable {

		public void run() {
			while (live) {
				move();
				for (SnakeListener l : listener) {
					l.snakeMoved(Snake.this);
					System.out.println(Snake.this);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void start() {
		new Thread(new SnakeDriver()).start();
	}

	public void addSnakeListener(SnakeListener l) {
		if (l != null) {
			this.listener.add(l);
		}
	}
}
