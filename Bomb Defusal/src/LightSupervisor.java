import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.scene.media.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

class LightSupervisor extends BaseMiniGameClient {
	private final int GAME_ID = 1;
	
	boolean[] lights = new boolean[6];
	JLabel[] lightLabels = new JLabel[6];
	ImageIcon lightOff = new ImageIcon("Assets/Light/LightOff.png");
	ImageIcon lightOn = new ImageIcon("Assets/Light/LightOn.png");
	Image background;
	
	MediaPlayer sfx = new MediaPlayer(new Media("Assets/Light/LightSFX.mp3"));
	
	LightSupervisor(BaseClient bc) {
		super(bc);
		setLayout(new FlowLayout());
		for(int i = 0;i < lightLabels.length;i++) {
			lightLabels[i] = new JLabel();
			add(lightLabels[i]);
		}
		try {
			background = ImageIO.read(new File("Assets/Light/SupervisorBackground.png"));
			background = background.getScaledInstance(500,500,Image.SCALE_SMOOTH);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		setVisible(true);
	}

	@Override
	public void parseCommand(String command) {
		if(command.startsWith("LIGHT")) {
			String[] inp = command.split(" ");
			for(int i = 0;i < inp[1].length();i++) {
				lights[i] = (inp[1].charAt(i) == '0') ? false : true;
				if(lights[i])
					lightLabels[i].setIcon(lightOn);
				else lightLabels[i].setIcon(lightOff);
			}
			sfx.play();
			if(solved()) {
				//TODO: Other win stuff?
				bc.sendCommand(GAME_ID+"WIN");
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	private boolean solved() {
		for(int i = 0;i < lights.length;i++)
			if(!lights[i])
				return false;
		return true;
	}
}