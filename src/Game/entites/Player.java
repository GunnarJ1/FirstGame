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

public class Player extends GameObject{
	
	private GameHandler handler;
	//private BufferedImage playerIcon;
	
	


	
	
	public Player(int x, int y,  GameHandler handler) {
		super(x, y, ID.Player);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,32,32);
	}	
	
	
	public void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					//Collision code
					if(HUD.UPGRADE != 2 && HUD.UPGRADE != 3 && HUD.SCORE > 50 && GameSetting.takeDamage == true){
					    
					    HUD.HEALTH -= 2;
					    if(GameSetting.hardcore) {
						HUD.HEALTH = 0;
					    }
					}
					if(HUD.UPGRADE == 2 || HUD.UPGRADE == 3 && GameSetting.takeDamage == true){HUD.HEALTH -= 1;}
				}
			}
			
			
		}
	}
	
	

	@Override
	public void tick() {
		
		
		if(HUD.HEALTH < 1){
			handler.removeObject(this);
		}
		
		
		
		if(!HUD.PAUSED){
			x += velx;
			y += vely;
			collision();
		}
		
		x = Game.Game.hitRoomBox(x, 0, Game.Game.width-38);
		y = Game.Game.hitRoomBox(y, 0, Game.Game.hieght-133);
			
		
		if(HUD.UPGRADE == 1){
			handler.addObject(new FxTrail(x + 1, y + 1, Color.DARK_GRAY, 31, 31, 0.2f, handler, ID.FxTrail));
		}else if(HUD.UPGRADE == 2){
			handler.addObject(new FxTrail(x + 1, y + 1, Color.red, 31, 31, 0.07f, handler, ID.FxTrail));
		}else if(HUD.UPGRADE == 3){
			handler.addObject(new FxTrail(x+1, y+1, Color.magenta, 31, 31, 0.1f, handler, ID.FxTrail));
		}
	
	
	}
	
	@Override
	public void render(Graphics g) {

			g.setColor(Color.darkGray);
		g.fillRect(x, y, 32, 32);

			g.setColor(Color.black);
		g.drawRect(x, y, 32, 32);
	}

	
}
