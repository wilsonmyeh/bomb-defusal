import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class OperatorGUI extends BaseClient{

	private JPanel jpInstruction,jpWaiting;
	private BaseMiniGameClient jpLightGame_Ope,jpCutWireGame_Ope,jpFindLocationGame_Ope,jpLogicGame_Ope;
	private JPanel jplogicRestart_Ope; //for logic game
	int teamNum;
	
	
	// no lobby panel
	OperatorGUI(int teamNum, Socket s) throws InterruptedException, IOException{
		super(s, teamNum); 
		this.setSize(800,500);
		this.setLayout(new BorderLayout());
		
		mySocket = s;
		//initiate all panels
		mainPanel = new JPanel();
		this.teamNum  = teamNum;
		myUserName = "Operator "+teamNum;
		String teamNumStr = teamNum + "";
		jpInstruction = new Instruction("Operator",teamNumStr);
		
		endSplash = new EndSplash(team, false);
		
		jpLightGame_Ope = new LightOperator(this);
		Random rand = new Random();
		jpCutWireGame_Ope = new CutTheWireOperator(this, rand.nextInt(5));
		jpFindLocationGame_Ope = new FindTheLocationOperator(this);
		jpLogicGame_Ope = new LogicGameOperator(this);
		jpWaiting = new WaitingRoom();
		//Adding games to gameClients array
		//Populate gameClients array
				// 0=FindTheLocation,
				// 1=TwoStage,
				// 2=CutWire,
				// 3=LogicPuzzle
		gameClients[0] = jpFindLocationGame_Ope;
		gameClients[1] = jpLightGame_Ope;
		gameClients[2] = jpCutWireGame_Ope;
		gameClients[3] = jpLogicGame_Ope;
		gamePanelNames[0] = "FindLocationGame_Operator"; 
		gamePanelNames[1] = "LightGame_Operator";
		gamePanelNames[2] = "CutWireGame_Operator";
		gamePanelNames[3] = "LogicGame_Operator";
		
		mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
		
		mainPanel.add(jpInstruction,"Intruction");
		mainPanel.add(endSplash, "endSplash");
		
		mainPanel.add(jpLightGame_Ope,"LightGame_Operator");
		mainPanel.add(jpCutWireGame_Ope,"CutWireGame_Operator");
		mainPanel.add(jpFindLocationGame_Ope,"FindLocationGame_Operator");
		mainPanel.add(jpLogicGame_Ope,"LogicGame_Operator");
		mainPanel.add(jpWaiting,"WaitingRoom");
		
		
		//for logic game
		jplogicRestart_Ope = new LogicGameRestartOperator();
		mainPanel.add(jplogicRestart_Ope,"LogicRestart_Ope");
		
		//other teamNUm
		chat = new Chat(this,(3-teamNum));
		
		add(mainPanel,BorderLayout.CENTER);
		add(chat,BorderLayout.EAST);
		//show instruction page
		mainCardLayout.show(mainPanel,"Instruction");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
		setVisible(true);	
	}
}
