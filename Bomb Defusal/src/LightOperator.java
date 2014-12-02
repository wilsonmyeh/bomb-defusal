import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/*
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
*/



import javax.imageio.ImageIO;
import javax.swing.*;

class LightOperator extends BaseMiniGameClient {
	private final int GAME_ID = 1;
	
	LightOperatorComponents comps;
	Image background;
	
	//MediaPlayer sfx = new MediaPlayer(new Media("Assets\\Light\\ButtonSFX.mp3"));
	
	
	LightOperator(BaseClient bc) {
		super(bc);
		try {
			if(System.getProperty("os.name").toLowerCase().contains("indow")) {
				background = ImageIO.read(new File("Assets\\Light\\OperatorBackground.png"));
			}
			else  {
				background = ImageIO.read(new File("Assets/Light/OperatorBackground.png"));
			}
			background = background.getScaledInstance(500,500,Image.SCALE_SMOOTH);
		} catch(IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		comps = new LightOperatorComponents(bc);
		add(comps);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		
	}
	
	@Override
	public void parseCommand(String command) {
		comps.parseCommand(command);
		repaint();
	}
	
}