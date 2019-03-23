package box.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import box.main.Game;
import box.main.HUD;
import box.main.Handler;
import box.util.GameUtil;

public class Player extends GameObject{
	private Handler handler;
	private int size = 30;
	private Color color = Color.white;

	
	public Player(int x,int y,Handler handler) {
		super(x, y, ID.Player);
		this.handler=handler;
		velX=0;
		velY=0;
	}
	
	@Override
	public void tick() {
		x+=velX;
		y+=velY;
		x = GameUtil.clamp(x, 0, Game.WIDTH-37);
		y = GameUtil.clamp(y, 0, Game.HEIGHT-59);
		checkForCollision();
		handler.addObject(new Trail(x, y,handler,size,color));
	}

	private void checkForCollision() {
		for (GameObject gameObject : handler.getObjects()) {
			if((gameObject.getId()==ID.BasicEnemy)&& gameObject.getBounds().intersects(this.getBounds())) {
				HUD.HEALTH-=2;
			}
			
			if(gameObject.getId()==ID.Boss) {
				HUD.HEALTH-=10;
			}
			
			if(gameObject.getId()==ID.BossFire) {
				HUD.HEALTH-=1;
			}
		}
		
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
