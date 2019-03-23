package box.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import box.main.Game;
import box.main.Handler;

public class BasicEnemy extends GameObject{
	private Handler handler;
	private int size = 16;
	private Color color = Color.red;
	public BasicEnemy(int x,int y,Handler handler) {
		super(x, y, ID.BasicEnemy);
		this.handler = handler;
		Random random = new Random();
		int factor = 1;
		if(random.nextInt(10)>5) factor*=-1;
		velX=factor*5;
		if(random.nextInt(10)>5) factor*=-1;
		velY=factor*5;
	}
	
	@Override
	public void tick() {
		handler.addObject(new Trail(x, y,handler,size,color));
		x+=velX;
		y+=velY;
		if(y<=0||y>=Game.HEIGHT-41) velY*=-1;
		if(x<=0||x>=Game.WIDTH-21) velX*=-1;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, size, size);
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,size,size);
	}

}
