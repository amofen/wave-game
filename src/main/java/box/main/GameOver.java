package box.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver {
	private int score;
	private String title;
	private String expression;
	private Handler handler;
	private HUD hud;
	
	public GameOver(Handler handler,HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		Font font1= new Font("arial", Font.BOLD, 20);
		g.setFont(font1);
		g.drawString("Game Over", 270, 200);
		Font font3= new Font("arial", Font.PLAIN, 20);
		g.setFont(font3);
		g.drawString("Score : "+hud.getScore(), 270, 240);
		Font font2= new Font("arial", Font.ITALIC, 16);
		g.setFont(font2);
		g.drawString("Binatna, haw hatha score ?", 240, 280);
	}

}
