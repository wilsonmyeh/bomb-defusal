import java.awt.CardLayout;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

//to be removed 
class ClientGUI extends BaseClient {
	//500*500 size
	private Socket mySocket; 
	private myUserName; //This is your role in the game (i.e Supervisor 1))
	Chat chat;
	
	private CardLayout mainCardLayout;
	private JPanel mainPanel,jpLobby,jpInstruction,jpTwoStageGame_Ope,jpCutWireGame_Ope,jpFindLocationGame_Ope,jpLogicGame_Ope;
	private JPanel jpTwoStageGame_Sup,jpCutWireGame_Sup,jpFindLocationGame_Sup,jpLogicGame_Sup,
	private JPanel currentCard;

	//Client(int team, Socket server);
	public ClientGUI(){
		mainPanel = new JPanel();
		jpInstruction = new Instruction(); 
		jpLobby = new Lobby();
		jpTwoStageGame_Ope = new TwoStageOperator();
		jpCutWireGame_Ope = new CutTheWireOperator();
		jpFindLocationGame_Ope = new FindTheLocationOperator();
		jpLogicGame_Ope_Ope = new LogicGameOperator();
		jpTwoStageGame_Sup = new TwoStageSupervisor();
		jpCutWireGame_Sup = new CutTheWireSupervisor();
		jpFindLocationGame_Sup = new FindTheLocationSupervisor();
		jpLogicGame_Sup = new LogicGameSupervisor();
		
		mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
		
		mainPanel.add(jpLobby,"Lobby");
		mainPanel.add(jpInstruction,"Intruction");
		
		mainPanel.add(jpTwoStageGame_Ope,"TwoStageGame_Operator");
		mainPanel.add(jpCutWireGame_Ope,"CutWireGame_Operator");
		mainPanel.add(jpFindLocationGame_Ope,"FindLocationGame_Operator");
		mainPanel.add(jpLogicGame_Ope,"LogicGame_Operator");
		
		mainPanel.add(jpTwoStageGame_Sup,"TwoStageGame_Supervisor");
		mainPanel.add(jpCutWireGame_Sup,"CutWireGame_Supervisor");
		mainPanel.add(jpFindLocationGame_Sup,"FindLocationGame_Supervisor");
		mainPanel.add(jpLogicGame_Sup,"LogicGame_Supervisor");
		
		add(mainPanel);
		//show instruction page
		mainCardLayout(mainPanel,"Instruction");
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setVisible(true);
	}
	public CardLayout getCardLayout(){
		return mainCardLayout;
	}
	public JPanel getMainPanel(){
		return mainPanel;
	}
	void sendCommand(String command){
		String packet = team + command; //appends team# to beginning of packet
	}
	
	
}
