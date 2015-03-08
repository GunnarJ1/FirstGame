package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y;
	protected ID id;
	protected int velx, vely;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	public abstract Rectangle getBounds();
	public abstract void render(Graphics g);
	public abstract void tick();
	
	public ID getId(){
		return id;
	}
	
	protected void setVelx(int velx){
		this.velx = velx;
	}
	
	protected void setVely(int vely){
		this.vely = vely;
	}
	
	protected void setX(int x){
		this.x = x;
	}
	
	protected void setY(int y){
		this.y = y;
	}
	
	protected int getX(){
		return x;
	}
	
	protected int getY(){
		return y;
	}
	
	
}
