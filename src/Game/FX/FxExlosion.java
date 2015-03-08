package Game.FX;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameHandler;
import Game.GameObject;
import Game.ID;




public class FxExlosion extends GameObject{
	private float alpha = 1;
	private float life;
	
	private GameHandler handler;
	private Color color;
	
	
	private int width, height;
	

	public FxExlosion(int x, int y,int velx, int vely,Color color,int width , int height,float life, GameHandler handler ,ID id) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.handler = handler;
		this.velx = velx;
		this.vely = vely;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(width, height);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect(x, y, width, height);
		
		g2d.setComposite(makeTransparent(1));
	}

	@Override
	public void tick() {
		y += vely;
		x += velx;
		
		if(alpha > life){
	
				alpha -= life - 0.0001f;
		}else{
			
				handler.removeObject(this);
		
		}
	}

	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}
}
