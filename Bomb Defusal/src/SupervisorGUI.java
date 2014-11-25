
import java.awt.CardLayout;
import java.net.Socket;

import javafx.scene.paint.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;


public class SupervisorGUI extends BaseClient{
	private JPanel jpLobby, jpInstruction, jpTwoStageGame_Sup,jpLogicGame_Sup,jpFindLocationGame_Sup,jpCutWireGame_Sup;
	int teamNum;
	Socket s;
	
	SupervisorGUI(int teamNum){
		this.setSize(800,500);
		

		//initiate all panels
		mainPanel = new JPanel();
		jpLobby = new Lobby();
		//create instruction
		this.teamNum = teamNum;
		String teamNumStr = this.teamNum + "";
		String identity = "Supervisor";
		jpInstruction = new Instruction(identity, teamNumStr); 
		
		jpTwoStageGame_Sup = new TwoStageSupervisor();
		jpCutWireGame_Sup = new CutTheWireSupervisor();
		jpFindLocationGame_Sup = new FindTheLocationSupervisor();
		jpLogicGame_Sup = new LogicGameSupervisor();
		
		//cardLayout
		mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
	
		mainPanel.add(jpInstruction,"Intruction");
		mainPanel.add(jpLobby,"Lobby");
	
	
		mainPanel.add(jpTwoStageGame_Sup,"TwoStageGame_Supervisor");
		mainPanel.add(jpCutWireGame_Sup,"CutWireGame_Supervisor");
		mainPanel.add(jpFindLocationGame_Sup,"FindLocationGame_Supervisor");
		mainPanel.add(jpLogicGame_Sup,"LogicGame_Supervisor");
	
		

		add(mainPanel);
		//show instruction page
		mainCardLayout.show(mainPanel,"Instruction");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setVisible(true);
	}
	
	public JPanel getMainpanel(){
		return mainPanel;
	}
	public CardLayout getCardLayout(){
		return mainCardLayout;
	}
}
