import javax.imageio.ImageIO;
import javax.swing.*;

/*
import javafx.scene.media.*;
*/

import java.awt.*;
import java.io.File;
import java.io.IOException;

class LightSupervisor extends BaseMiniGameClient {
	private final int GAME_ID = 1;
	
	Image background;
	
	//MediaPlayer sfx = new MediaPlayer(new Media("Assets\\Light\\LightSFX.mp3"));
	
	LightSupervisor(BaseClient bc) {
		super(bc);
		try {
			background = ImageIO.read(new File("Assets\\Light\\SupervisorBackground.jpg"));
			background = background.getScaledInstance(500,500,Image.SCALE_SMOOTH);
		} catch(IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		add(new LightSupervisorComponents(bc));
		
		setVisible(true);
	}

	@Override
	public void parseCommand(String command) {
		//LightSupervisorComponents
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		
	}
	
}
