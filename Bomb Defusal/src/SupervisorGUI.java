
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.net.Socket;

import javafx.scene.paint.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;


public class SupervisorGUI extends BaseClient{
	private JPanel jpLobby, jpInstruction, jpTwoStageGame_Sup,jpLogicGame_Sup,jpFindLocationGame_Sup,jpCutWireGame_Sup,jplogicRestart_Sup;
	int teamNum;

	
	SupervisorGUI(int teamNum, Socket s){
		this.setSize(800,500);
		this.setLayout(new BorderLayout());

		mySocket = s;
		//initiate all panels
		mainPanel = new JPanel();
		jpLobby = new Lobby();
		//create instruction
		this.teamNum = teamNum;
		String teamNumStr = this.teamNum + "";
		myUserName = "Supervisor";
		jpInstruction = new Instruction(myUserName, teamNumStr); 
		
		jpTwoStageGame_Sup = new TwoStageSupervisor();
		jpCutWireGame_Sup = new CutTheWireSupervisor();
		jpFindLocationGame_Sup = new FindTheLocationSupervisor();
		jpLogicGame_Sup = new LogicGameSupervisor();
		
		//other teamNUm
		chat = new Chat(this,(3-teamNum));
		
		//cardLayout
		mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
	
		mainPanel.add(jpInstruction,"Intruction");
		mainPanel.add(jpLobby,"Lobby");
	
	
		mainPanel.add(jpTwoStageGame_Sup,"TwoStageGame_Supervisor");
		mainPanel.add(jpCutWireGame_Sup,"CutWireGame_Supervisor");
		mainPanel.add(jpFindLocationGame_Sup,"FindLocationGame_Supervisor");
		mainPanel.add(jpLogicGame_Sup,"LogicGame_Supervisor");
		
		//for logic game
		jplogicRestart_Sup = new LogicGameRestartSupervisor();
		mainPanel.add(jplogicRestart_Sup,"LogicRestart_Sup");
	
		

		add(mainPanel,BorderLayout.CENTER);
		add(chat,BorderLayout.EAST);
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
