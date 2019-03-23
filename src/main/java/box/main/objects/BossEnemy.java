package box.main.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import box.main.Game;
import box.main.Handler;

public class BossEnemy extends GameObject{
	private Handler handler;
	private int size = 50;
	private Color color = Color.red;
	private BufferedImage bossLeft;
	private BufferedImage bossRight;
	private String [] bossSpeech = new String[10]; 
	private int speechIndex=0;
	private int roundKeeper=0;
	public BossEnemy(int x,int y,Handler handler) {
		super(x, y, ID.BasicEnemy);
		this.handler = handler;
		try {
			bossLeft=ImageIO.read(getClass().getClassLoader().getResource("bossleft.png"));
			bossRight=ImageIO.read(getClass().getClassLoader().getResource("bossright.png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.x=280;
		this.y=-50;
		this.velY=1;
		this.velX=3;
		initBossSpeech();
	}
	
	private void initBossSpeech() {
		bossSpeech[0]="Tenkhla3 !";
		bossSpeech[1]="Lyoum nechwiiiiik !";
		bossSpeech[2]="Chikh Mejaji aaaaa !";
		bossSpeech[3]="Hfdq !";
		bossSpeech[4]="3ami ali khoya w machi sahbi !";
		bossSpeech[5]="Oumba3d l Oubma3d l !";
		bossSpeech[6]="aaaaaaaaaaaa!";
		bossSpeech[7]="aya ha sayi!";
		bossSpeech[8]="sayi jeux slak dir échape, bzaf m smata!";
		
	}

	@Override
	public void tick() {
		roundKeeper++;
		if(roundKeeper ==300) {
			roundKeeper=0;
			speechIndex++;
		}
		if(speechIndex>=8) speechIndex=8;
		if(y<100) {
			velY=1;
		}else {
			velY=0;
			this.x+=velX;
			if(x<=0||x>=Game.WIDTH-50) velX*=-1;
			if(speechIndex<=4)handler.addObject(new BossFire(x+25, y+25,5,Color.yellow, handler));
			else if(speechIndex>5&&speechIndex<=7)handler.addObject(new BossFire(x+25, y+25,20,Color.red, handler));
		}
		this.y+=velY;
		
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		if(velX<0) {
			g.drawImage(bossLeft, x, y, null);
		}else {
			g.drawImage(bossRight, x, y, null);
		}
		g.setColor(Color.white);
		g.drawString(bossSpeech[speechIndex],x-30,y+10);
		
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,size,size);
	}

}
