
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;


public class SupervisorGUI extends BaseClient{
	private JPanel jpLobby, jpInstruction;
	private BaseMiniGameClient jpLightGame_Sup,jpLogicGame_Sup,jpFindLocationGame_Sup,jpCutWireGame_Sup;
	private JPanel jplogicRestart_Sup;//for logic game
	int teamNum;

	
	SupervisorGUI(int teamNum, Socket s) throws InterruptedException, IOException{
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
		
		jpLightGame_Sup = new LightSupervisor(this);
		jpCutWireGame_Sup = new CutTheWireSupervisor(this);
		jpFindLocationGame_Sup = new FindTheLocationSupervisor(this);
		jpLogicGame_Sup = new LogicGameSupervisor(this);
		
		//Populate gameClients array
		// 0=FindTheLocation,
		// 1=TwoStage,
		// 2=CutWire,
		// 3=LogicPuzzle
		gameClients[0] = jpFindLocationGame_Sup;
		gameClients[1] = jpLightGame_Sup;
		gameClients[2] = jpCutWireGame_Sup;
		gameClients[3] = jpLogicGame_Sup;
		
		//other teamNUm
		chat = new Chat(this,(3-teamNum));
		
		//cardLayout
		mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
	
		mainPanel.add(jpInstruction,"Intruction");
		mainPanel.add(jpLobby,"Lobby");
	
	
		mainPanel.add(jpLightGame_Sup,"LightGame_Supervisor");
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
}
