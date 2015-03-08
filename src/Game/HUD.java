package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class HUD {
	
	private Random random = new Random();
	
	public static int HEALTH = 100;
	public static int SCORE = 1;
	public static int UPGRADE = 0;
	static int getHealthUpgrade = 3;
	public static int upgradeTimer = 0;
	
	public static boolean PAUSED = true;
	public static boolean showHud = true;
	
	
	//private GameHandler handler = new GameHandler();
	
	
	public HUD() {

	}
	
	
	
	
	
	public  void render(Graphics g){
		
	if(showHud){
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
		g.setColor(Color.gray);
		g.fillRect(0,Game.hieght-100, Game.width, Game.hieght);
		
		g.setColor(Color.black);
		g.fillRect(15,Game.hieght-80, 100*2, 25);
		
			g.setColor(Color.black);
			g.drawString("HEALTH", 100-5, Game.hieght-82);
	
		
			g.setColor(Color.green);
			g.fillRect(15,Game.hieght-80, HEALTH*2, 25);
		
		if(GameSetting.hardcore){hardcoreHud(g);}	
			
			g.setColor(Color.black);
		g.drawRect(0,Game.hieght-100, Game.width, Game.hieght);
			g.drawRect(15,Game.hieght-80, 100*2, 25);
		
		g.setColor(Color.blue);
		g.drawString("Score: ", Game.width-200, Game.hieght-75);
		g.setColor(Color.black);
		g.drawString(""+SCORE,Game.width-150, Game.hieght-75);
		
		String ug="";
		//Upgrade system
		if(UPGRADE == 1){
			ug = "None";
		}else if(UPGRADE == 2){
			ug = "Flame Shield";
		}else if(UPGRADE == 3){
			ug = "Basic Shield";
		}else{
			ug = "None";
			UPGRADE = 1;
		}
		
		g.setColor(Color.BLUE);
		g.drawString("Upgrade: ",Game.width-200, Game.hieght-60);
		g.setColor(Color.black);
		g.drawString(""+ug,Game.width-125, Game.hieght-60);
	}
	//Pause
		if(HUD.PAUSED){
			
			Font font1=  new Font("TimesRoman", Font.PLAIN,72);
			
			g.setColor(Color.BLUE);

			g.fillRoundRect(Game.width/2-248, Game.hieght/2-100+2, 500, 175, 50, 75);
			g.setColor(Color.BLACK);
			g.drawRoundRect(Game.width/2-248, Game.hieght/2-100+2, 500, 175, 50, 75);
			g.setFont(font1);
			g.drawString("PAUSED", Game.width/2-135, Game.hieght/2+10);

		}
		
	//On death
		if(HEALTH < 1){
			showHud = false;
			Font font1=  new Font("TimesRoman", Font.PLAIN,72);
			Font font2=  new Font("TimesRoman", Font.BOLD,35);			
			Font fontScore = new Font("TimesRoman", Font.PLAIN, 28);
			
			g.setColor(Color.BLACK);
			g.fillRoundRect(Game.width/2-248, Game.hieght/2-100+2, 500, 175, 275, 500);

			g.setColor(Color.red);
			g.setFont(font1);
			g.drawString("You Died", Game.width/2-135, Game.hieght/2+10);
			g.setFont(font2);
		
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.drawString("Final Score: "+SCORE, Game.width/2-54, Game.hieght/2+50);
		}
		
		if(Game.Game_OVER){
			showHud = false;
			Font font1=  new Font("TimesRoman", Font.PLAIN,72);
			Font font2=  new Font("TimesRoman", Font.BOLD,23);			
			g.setColor(Color.yellow);
			g.fillRoundRect(Game.width/2-248, Game.hieght/2-100+2, 500, 175, 275, 500);

			g.setColor(Color.green);
			g.setFont(font1);
			g.drawString("You WON!", Game.width/2-155, Game.hieght/2+10);
			g.setFont(font2);
			g.drawString("Score: ", Game.width/2-75, Game.hieght/2+50);
			g.setColor(Color.gray);
			g.drawString(""+SCORE, Game.width/2-5, Game.hieght/2+52);
		}
		
	
	}
	
	
	
	
	public  void tick() {
		if (UPGRADE != 3 && UPGRADE != 2 && upgradeTimer >= 500 && SCORE > 999) {
			UPGRADE = random.nextInt(4);
			upgradeTimer = 0;
			getHealthUpgrade = random.nextInt(3);
		}
		
		if (upgradeTimer > 250 && UPGRADE == 2) {
			UPGRADE = 0;
		}
		
		if (upgradeTimer > 500 && UPGRADE == 3) {
			UPGRADE = 0;
		}
			
		
		if(UPGRADE == 2 && HEALTH < 1) {
			UPGRADE = 0;
		}
		
		
		
	    	SCORE += Game.getScoreAmount();
		
		
		if(HEALTH > 0 && !PAUSED) {
			upgradeTimer += 1;
		}
		
		if(HEALTH < 1 && PAUSED) {
			PAUSED = false;
		}
		
		if (getHealthUpgrade == 1 && HEALTH <= (100 -25)) {
			HEALTH += 25;
			getHealthUpgrade = 4;
		}
	}	
	
	public void hardcoreHud(Graphics g) {
	    g.setColor(Color.DARK_GRAY);
	    g.fillRect(15,Game.hieght-80, 100*2, 25);
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
	    g.setColor(Color.white);
	    g.drawString("HARDCORE MODE", 32, Game.hieght-60);
	    Font font2=  new Font("TimesRoman", Font.BOLD,23);			
	}
}
