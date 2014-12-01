import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lobby extends JPanel{
	private JButton jbCutTheWire,jbFindLocation,jbLightGame,jbLogicGame,jbBack;


	public Lobby(){
		this.setSize(500,500);	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		jbCutTheWire = new JButton("Go to Cut The Wire Game");
		jbCutTheWire.setAlignmentX(CENTER_ALIGNMENT);
		jbFindLocation = new JButton("Go to Find The Location Game");
		jbFindLocation.setAlignmentX(CENTER_ALIGNMENT);
		jbLightGame = new JButton("Go to Light Puzzle Game");
		jbLightGame.setAlignmentX(CENTER_ALIGNMENT);
		jbLogicGame = new JButton("Go to Logic Game");
		jbLogicGame.setAlignmentX(CENTER_ALIGNMENT);
		jbBack = new JButton("Go back to Instruction to review the rules");
		jbBack.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jbLightGame);
		add(Box.createGlue());
		this.add(jbCutTheWire);
		add(Box.createGlue());
		this.add(jbFindLocation);
		add(Box.createGlue());
		this.add(jbLogicGame);
		add(Box.createGlue());	
		this.add(jbBack);

		//add action
		jbFindLocation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"FindLocationGame_Supervisor");
				//send command to server to let operator enter the game
				StartClient.bc.sendCommand("80"); //8 means start game
			}
		});
		//add action
		jbLightGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"LightGame_Supervisor");
				//send command to server to let operator enter the game
				StartClient.bc.sendCommand("81"); //8 means start game
			}
		});
		//add action
		jbCutTheWire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"CutWireGame_Supervisor");
				//send command to server to let operator enter the game
				StartClient.bc.sendCommand("82"); //8 means start game
			}
		});

		//add action
		jbLogicGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"LogicGame_Supervisor");
				//send command to server to let operator enter the game
				StartClient.bc.sendCommand("83"); //8 means start game
			}
		});

		jbBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){

				StartClient.bc.mainCardLayout.first(StartClient.bc.mainPanel);
				//StartClient.bc.mainPanel.revalidate();
				//StartClient.bc.mainPanel.repaint();

			}
		});
	}



	/*
=======
	public static void main(String [] args){
		JFrame test = new JFrame();
		test.setSize(500, 500);
		Lobby lobby = new Lobby();
		test.add(lobby);

		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
>>>>>>> branch 'master' of https://github.com/wpyeh/bomb-defusal
	}
	 */
}
