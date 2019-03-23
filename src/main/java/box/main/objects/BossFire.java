package box.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import box.main.Game;
import box.main.Handler;

public class BossFire extends GameObject{
	private Handler handler;
	private int size = 5;
	private Color color = Color.yellow;
	public BossFire(int x,int y,int size, Color color,Handler handler) {
		super(x, y, ID.BasicEnemy);
		this.handler = handler;
		Random random = new Random();
		int factor = 1;
		velX = random.nextInt(2)+1;
		velY=random.nextInt(2)+1;
		if(random.nextInt(10)>=5) factor =-1;
		if(random.nextBoolean()==true) velX*=factor;
		if(random.nextBoolean()==true) velY*=factor;
		this.size=size;
		this.color=color;

	}
	
	@Override
	public void tick() {
		handler.addObject(new Trail(x, y,handler,size,color));
		x+=velX;
		y+=velY;
		if(y<=0||y>=Game.HEIGHT-41) handler.removeObject(this);;
		if(x<=0||x>=Game.WIDTH-21) handler.removeObject(this);;
		
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
