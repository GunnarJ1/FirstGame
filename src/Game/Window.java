package Game;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Window extends JFrame{

	private static final long serialVersionUID = 5695906109115313739L;

	public Window(Dimension size, String title, Game game){
		super(title);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		
		
		
		add(game);
		
		setVisible(true);
		
		setLocationRelativeTo(null);
		
		game.start();
	}
}
