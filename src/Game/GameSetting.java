package Game;

public class GameSetting {
	
	public static int enemiesDead = 0;
	public static int difficulty= 1;
	
	
	public static boolean hardcore = false;
	public static boolean takeDamage = true;
	

	
	public static int setAmountofEnemies(){
		if (difficulty == 2) {return 20;}
		else if(difficulty == 1){return 10;}
		else if(difficulty == 0){return 5;}else if (difficulty== 3){return 0;}else{
			return 10;
		}
	}
	
	public static int getAmountofEnemies(){
		if (difficulty == 2) {return 20;}
		else if(difficulty == 1){return 10;}
		else if(difficulty == 0){return 5;}else if (difficulty== 3){return 0;}else{
			return 10;
		}
	}
	
}
