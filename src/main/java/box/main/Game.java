package box.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import box.main.objects.BasicEnemy;
import box.main.objects.Player;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6701278500613891255L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;

	private Boolean running = false;

	private Handler handler;

	private Random random;

	private HUD hud;

	private Spawner spawner;

	private GameOver gameOver;

	static GameState state = GameState.Game;

	enum GameState {
		Game(), GameOver();
	}

	public Game() {

		handler = new Handler();
		handler.addObject(new Player(WIDTH / 2 - 30, HEIGHT / 2 - 30, handler));
		hud = new HUD();
		spawner = new Spawner(handler, hud);
		Random random = new Random();
		handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT - 32), handler));
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Box Game", this);
		gameOver = new GameOver(handler, hud);

	}

	public void run() {
		this.requestFocus();
		loop();

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loop() { // game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render(frames);
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
			try {
				Thread.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		stop();
	}

	private void tick() {
		if (state == GameState.Game) {
			handler.tick();
			hud.tick();
			spawner.tick();
		} else {
			gameOver.tick();
		}
	}

	private void render(int fps) {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.drawString("FPS : " + fps, WIDTH - 80, 10);

		if (state == GameState.Game) {
			handler.render(g);
			hud.render(g);
		} else {
			gameOver.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}

}
