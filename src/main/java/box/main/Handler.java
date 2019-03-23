package box.main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import box.main.objects.GameObject;
import box.main.objects.ID;

public class Handler {
	
	private List<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick(){
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
	}
	

	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}


	public List<GameObject> getObjects() {
		return objects;
	}
	
	public void clearAllEnemies() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject gameObject = objects.get(i);
			if(gameObject.getId()!=ID.Player) {
				objects.remove(gameObject);
				i--;
			}
		}
	}
	
	
	
}
