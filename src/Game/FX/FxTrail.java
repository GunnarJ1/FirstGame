package Game.FX;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameHandler;
import Game.ID;

public class FxTrail extends Game.GameObject{

	private float alpha = 1;
	private float life;
	
	private GameHandler handler;
	private Color color;
	
	
	private int width, height;
	
	public FxTrail(int x, int y,Color color,int width , int height,float life, GameHandler handler ,ID id) {
		super(x, y, id);
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.handler = handler;
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		return null;
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
		//if(!HUD.PAUSED)
		if(alpha > life){
	
				alpha -= life - 0.001f;
		}else{
			
				handler.removeObject(this);
		
		}
	}
	
	private AlphaComposite makeTransparent(float alpha){
			
			int type = AlphaComposite.SRC_OVER;
			return(AlphaComposite.getInstance(type, alpha));
		
		}

}
