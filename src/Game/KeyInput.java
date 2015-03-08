package Game;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.FX.FxExlosion;

public class KeyInput extends KeyAdapter{
	
	private GameHandler handler;
	
	public GameObject exlosion;
	Random random = new Random();
	
	public KeyInput(GameHandler handler){
		this.handler = handler;
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				
				
				int speed = 5;
				
				
				if(key == KeyEvent.VK_W ){tempObject.setVely(-1*speed);}
				if(key == KeyEvent.VK_S){tempObject.setVely(speed);}
				if(key == KeyEvent.VK_D){tempObject.setVelx(speed);}
				if(key == KeyEvent.VK_A){tempObject.setVelx(-1*speed);}
				
				if(key == KeyEvent.VK_UP){tempObject.setVely(-1*speed);}
				if(key == KeyEvent.VK_DOWN){tempObject.setVely(speed);}
				if(key == KeyEvent.VK_RIGHT){tempObject.setVelx(speed);}
				if(key == KeyEvent.VK_LEFT){tempObject.setVelx(-1*speed);}
			
				
			}
			
		}
		
			if(key == KeyEvent.VK_ESCAPE) System.exit(1);
			if(key == KeyEvent.VK_P) {
				if(HUD.PAUSED){
					HUD.PAUSED = false;
				}else if(!HUD.PAUSED && HUD.HEALTH > 0){
					HUD.PAUSED = true;
				}
			}
			
			if(key == KeyEvent.VK_U){
				
					exlosion = new FxExlosion(random.nextInt(100), random.nextInt(100), random.nextInt(5), random.nextInt(5), Color.red, 8, 8, 0.3f, handler, ID.FxExplosion);
				for (int i = 0; i < random.nextInt(1000); i++)
				handler.addObject(exlosion);
			}
			
			
		}
			

	
	
	@Override
	public void keyReleased(KeyEvent event) {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				int key = event.getKeyCode();
				if(key == KeyEvent.VK_W){tempObject.setVely(0);}
				if(key == KeyEvent.VK_S){tempObject.setVely(0);}
				if(key == KeyEvent.VK_D){tempObject.setVelx(0);}
				if(key == KeyEvent.VK_A){tempObject.setVelx(0);}
			
				if(key == KeyEvent.VK_UP){tempObject.setVely(0);}
				if(key == KeyEvent.VK_DOWN){tempObject.setVely(0);}
				if(key == KeyEvent.VK_RIGHT){tempObject.setVelx(0);}
				if(key == KeyEvent.VK_LEFT){tempObject.setVelx(0);}
			}
			
		}
	}
	
}
