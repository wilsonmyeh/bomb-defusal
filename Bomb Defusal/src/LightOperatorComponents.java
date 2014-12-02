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

class LightOperatorComponents extends BaseMiniGameClient {
	private final int GAME_ID = 1;
	
	JButton[] buttons = new JButton[6];
	ImageIcon button = new ImageIcon("Assets\\Light\\Button.png");
	
	//MediaPlayer sfx = new MediaPlayer(new Media("Assets\\Light\\ButtonSFX.mp3"));
	
	ActionListener buttonPress = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0;i < buttons.length;i++) {
				if(e.getSource() == buttons[i]) {
					bc.sendCommand(GAME_ID+"LIGHT "+i);
					break;
				}
			}
			//sfx.play();
		}
	};
	
	LightOperatorComponents(BaseClient bc) {
		super(bc);
		setLayout(new GridLayout(2,3));
		for(int i = 0;i < buttons.length;i++) {
			buttons[i] = new JButton(button);
			buttons[i].setOpaque(false);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setBorderPainted(false);
			buttons[i].addActionListener(buttonPress);
			add(buttons[i]);
		}
		
		setSize(500,500);
		setOpaque(false);
	}
	
	@Override
	public void parseCommand(String command) {
		if(command.startsWith("WIN")) {
			bc.switchToWaitingRoom();
		}
	}
	
}