package box.main;

import java.awt.Graphics;
import java.util.Random;

import box.main.objects.BasicEnemy;
import box.main.objects.BossEnemy;
import box.main.objects.FastEnemy;
import box.main.objects.SmartEnemy;

public class Spawner {
	
	private Handler handler;
	private HUD hud;
	private int scorekeep=0;
	public Spawner(Handler handler,HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	
	public void tick() {
		scorekeep++;
		if(scorekeep>=400 ) {
			hud.setLevel(hud.getLevel()+1);
			scorekeep=0;
			Random random  = new Random();
			if(hud.getLevel()==1 || hud.getLevel()==2) {
				for (int i = 0; i < 2; i++) {
					handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT-32),handler));
				}
			}
			if(hud.getLevel()==3 || hud.getLevel()==4)
			handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH-16), random.nextInt(Game.HEIGHT-32),handler));

			if(hud.getLevel()==5) 
			handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH-16), random.nextInt(Game.HEIGHT-32),handler)); 
			if(hud.getLevel()==6) {
				handler.clearAllEnemies();
				handler.addObject(new BossEnemy(0,0,handler)); 
			}
		}

				

	}
	
	public void prepareBossFight() {
		
	}
	
	public void render(Graphics g) {
		
	}

}
