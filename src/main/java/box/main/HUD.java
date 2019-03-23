package box.main;

import java.awt.Color;
import java.awt.Graphics;

import box.main.Game.GameState;
import box.util.GameUtil;

public class HUD {
	public static int HEALTH = 500;
	private int greenValue=250;
	private int score=0;
	private int level=1;
	
	public void tick() {
		HEALTH = GameUtil.clamp(HEALTH, 0, 500);
		greenValue=HEALTH*250/500;
		greenValue = GameUtil.clamp(greenValue, 0, 255);
		score++;
		if(HEALTH==0) {
			Game.state=GameState.GameOver;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(10, 10, 200, 30);
		g.setColor(new Color( 100, greenValue, 0));
		g.fillRect(10, 10, HEALTH*2/5, 30);
		g.setColor(Color.white);
		g.drawRect(9, 9, 202, 32);
		g.drawString("Score: "+score, 10, 64);
		g.drawString("Level: "+level, 10, 80);
	}
	
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	

	
}
