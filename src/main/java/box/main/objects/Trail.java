package box.main.objects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import box.main.Handler;

public class Trail extends GameObject{
	private float reduction = 0.08f;
	private float alpha = 0.99f;
	private int size;
	private Color color;
	private Handler handler ;

	public Trail(int x, int y,Handler handler,int size, Color color) {
		super(x, y,ID.Trail);
		this.handler=handler;
		this.size= size;
		this.color=color;
	}

	@Override
	public void tick() {	
		this.alpha-=reduction;
		if(this.alpha<=0) this.handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g.setColor(color);
		g.fillRect(x, y, size, size);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,size,size);
	}
	

}
