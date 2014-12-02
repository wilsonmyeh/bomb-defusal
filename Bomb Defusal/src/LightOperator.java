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
	
	JButton[] buttons = new JButton[6];
	ImageIcon button = new ImageIcon("Assets\\Light\\Button.png");
	Image background;
	
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
	
	LightOperator(BaseClient bc) {
		super(bc);
		try {
			background = ImageIO.read(new File("Assets\\Light\\OperatorBackground.png"));
			background = background.getScaledInstance(500,500,Image.SCALE_SMOOTH);
		} catch(IOException e) {
			e.printStackTrace();
		}
		setLayout(null);
		add(new LightOperatorComponents(bc));
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		
	}
	
	@Override
	public void parseCommand(String command) {
		// No parsing needed
	}
	
}