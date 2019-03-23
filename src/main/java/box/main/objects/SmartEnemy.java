package box.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import box.main.Handler;
import box.util.GameUtil;

public class SmartEnemy extends GameObject {
	private Handler handler;
	private Player player;
	private int size = 35;
	private Color color = Color.orange;

	public SmartEnemy(int x, int y, Handler handler) {
		super(x, y, ID.BasicEnemy);
		this.handler = handler;
		for (GameObject gameObject : handler.getObjects()) {
			if (gameObject.getId() == ID.Player) {
				player = (Player) gameObject;
			}
		}
		Random random = new Random();
		int factor = 2;
		if (random.nextInt(10) > 5)
			factor *= -2;
		velX = factor * 5;
		if (random.nextInt(10) > 5)
			factor *= -2;
		velY = factor * 5;
	}

	@Override
	public void tick() {
		int distX = player.getX() - this.getX() +3;
		int distY = player.getY() - this.getY() +3;
		if (distX != 0) {
			int dirX = distX / Math.abs(distX);
			velX = GameUtil.clamp(Math.abs(distX), 1, 4) * dirX;
		} else
			velX = 0;

		
		if (distY != 0) {
			int   dirY = distY / Math.abs(distY);
			velY = GameUtil.clamp(Math.abs(distY), 1, 4) * dirY;
		} else
			velY = 0;

		x += velX;
		y += velY;
		handler.addObject(new Trail(x, y,handler,size,color));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);

		g.fillRect(x, y, size, size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, size, size);
	}

}
