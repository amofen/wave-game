package box.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import box.main.objects.GameObject;
import box.main.objects.ID;

public class KeyInput extends KeyAdapter {
	private boolean [] keyDown = new boolean[4];

	private Handler handler;

	public KeyInput(Handler handler) {
		this.handler = handler;
		keyDown[0]=false;//Z
		keyDown[1]=false;//Q
		keyDown[2]=false;//S
		keyDown[3]=false;//D
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getId() == ID.Player) {
				startBasicZQSDMovementVel(object, key, 10);
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.getObjects().size(); i++) {
			GameObject object = handler.getObjects().get(i);
			if (object.getId() == ID.Player) {
				stopMovement(object,key);
			}
		}

	}

	private void stopMovement(GameObject object,int key) {
		if (key == KeyEvent.VK_Z) {
			keyDown[0]=false;
		}
		if (key == KeyEvent.VK_S) {
			keyDown[2]=false;
		}
		if (key == KeyEvent.VK_Q) {
			keyDown[1]=false;
		}
		if (key == KeyEvent.VK_D) {
			keyDown[3]=false;
		}
		if(!keyDown[0]&!keyDown[2])object.setVelY(0);
		if(!keyDown[1]&!keyDown[3])object.setVelX(0);
	}

	private void startBasicZQSDMovementVel(GameObject object, int key, int vel) {
		if (key == KeyEvent.VK_Z) {
			object.setVelY(-vel);
			keyDown[0]=true;
			return;
		}
		if (key == KeyEvent.VK_Q) {
			keyDown[1]=true;
			object.setVelX(-vel);
			return;
		}
		if (key == KeyEvent.VK_S) {
			keyDown[2]=true;
			object.setVelY(vel);
			return;
		}

		if (key == KeyEvent.VK_D) {
			keyDown[3]=true;
			object.setVelX(vel);
			return;
		}
	}

}
