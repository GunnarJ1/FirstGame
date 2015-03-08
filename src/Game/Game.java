package Game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.entites.BasicEnemy;
import Game.entites.Player;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 3051539836412575012L;
	
	public static int width = 854;
	public static int hieght = 480;

	public static boolean Game_OVER = false;
	
	static Dimension size = new Dimension(width, hieght);
	
	static boolean running = false;
	
	private Thread thread;
	
		private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		private static BufferedImage sprite_sheet = null;
		private static BufferedImage background = null;
		
		private BufferedImage player;
		
		
	
	private GameHandler handler;
	private HUD hud;
	
	public Game(){
		handler = new GameHandler();
		hud = new HUD();
		this.addKeyListener(new KeyInput(handler));
		new Window(size, "Instincted [Gunnar's Game]", this);
		
		Random random = new Random();
		
		for(int i = 0; i < (GameSetting.setAmountofEnemies()); i++)
			handler.addObject(new BasicEnemy(random.nextInt(width-32)+1,random.nextInt(hieght-132)+2,handler, ID.BasicEnemy));
			
		
		
		handler.addObject(new Player(100, 100, handler));
		
		
	}
	
 static void main(String[] args){
		new Game();
	}

	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: "+ frames );
				frames = 0;
			}
		}
		stop();
	}
	
	public static int hitRoomBox(int var, int min, int max){
		if(var <= min){
			return var = min;}
		else if(var >= max){
			return var = max;
		}else{
			return var;
		}
	}
	
	private void tick() {

		if(GameSetting.enemiesDead >= GameSetting.getAmountofEnemies()){
			HUD.SCORE += 1000;
			Game_OVER = true;
		}
		
		handler.tick();
		hud.tick();
	
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g =  bs.getDrawGraphics();
		

	
		//background
		if(HUD.HEALTH >= 20) {
			g.setColor(new Color(10, 10, 100));
		}else if (HUD.HEALTH <= 19) {
		    	g.setColor(new Color(150, 10, 225));
		}
		g.fillRect(0, 0,854, 480);
		
		
		handler.render(g);
		hud.render(g);
	
		
		g.dispose();
		bs.show();
	}
	
	public static int getScoreAmount(){
	    if((!HUD.PAUSED && Game.Game_OVER == false && HUD.HEALTH >= 1)) {
    		return 1;
    		
	    }else{
    		
		return 0;
		
    		}
	    }
	}
	

