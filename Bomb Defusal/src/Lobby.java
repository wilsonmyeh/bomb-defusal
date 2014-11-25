import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Lobby extends JPanel{
	private JButton jbCutTheWire,jbFindLocation,jbTwoStage,jbLogicGame;
	

	public Lobby(){
		this.setSize(500,500);	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		jbCutTheWire = new JButton("Go to Cut The Wire Game");
		jbFindLocation = new JButton("Go to Find The Location Game");
		jbTwoStage = new JButton("Go to Two Stage Game");
		jbLogicGame = new JButton("Go to Logic Game");
		this.add(jbTwoStage);
		add(Box.createGlue());
		this.add(jbCutTheWire);
		add(Box.createGlue());
		this.add(jbFindLocation);
		add(Box.createGlue());
		this.add(jbLogicGame);
		add(Box.createGlue());	
		
		
		//add action
				jbCutTheWire.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						//ClientGUI cg
						StartClient.bc.getCardLayout().(cg.getMainPanel,"CutWireGame_Supervisor");
						//send command to server to let operator enter the game
					}
				});
				//add action
				jbFindLocation.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						//ClientGUI cg
						StartClient.bc.getCardLayout().(cg.getMainPanel,"FindLocationGame_Supervisor");
						//send command to server to let operator enter the game
					}
				});
				//add action
				jbLogicGame.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						//ClientGUI cg
						StartClient.bc.getCardLayout().(cg.getMainPanel,"LogicGame_Supervisor");
						//send command to server to let operator enter the game
					}
				});
				//add action
				jbTwoStage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						//ClientGUI cg
						StartClient.bc.getCardLayout().(cg.getMainPanel,"TwoStageGame_Supervisor");
						//send command to server to let operator enter the game
					}
				});
	}
	
	public static void main(String [] args){
		new Lobby();
	}
}