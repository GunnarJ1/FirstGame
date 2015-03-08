package Game.entites;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Game.GameHandler;
import Game.GameObject;
import Game.GameSetting;
import Game.HUD;
import Game.ID;
import Game.FX.FxTrail;

public class BasicEnemy extends GameObject{
	
	private GameHandler handler;
	
	public BasicEnemy(int x, int y, GameHandler handler,ID id) {
		super(x, y, id);
		this.handler = handler;
		
		
			vely = 5;
			velx = 5;
	}
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				if(getBounds().intersects(tempObject.getBounds())){
					//Collision code
					if(HUD.UPGRADE == 2){
						GameSetting.enemiesDead += 1;
						handler.removeObject(this);
					}
					
					if(HUD.SCORE <= 50){
						GameSetting.enemiesDead += 1;
						handler.removeObject(this);
					}
					
				}
			}
			
			
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,16,16);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}

	@Override
	public void tick() {
		
		if(Game.Game.Game_OVER){
			handler.removeObject(this);
		}
		
		if(HUD.HEALTH < 1){
			
			handler.removeObject(this);
		}
		collision();
		
		if(y <= 0-2 || y >= Game.Game.hieght-120) vely *= -1;
		if(x <= 0 || x >= Game.Game.width-19) velx *= -1;
		
		if(!HUD.PAUSED){
			x += velx;
			y += vely;
		}
		
		handler.addObject(new FxTrail(x, y, Color.red, 16, 16, 0.15f, handler, ID.FxTrail));
		
	}

}
