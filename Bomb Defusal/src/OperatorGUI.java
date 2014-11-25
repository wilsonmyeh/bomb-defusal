import javax.swing.JPanel;


public class OperatorGUI extends BaseClient{

	private JPanel mainPanel,jpLobby,jpInstruction,jpTwoStageGame_Ope,jpCutWireGame_Ope,jpFindLocationGame_Ope,jpLogicGame_Ope;

	mainPanel = new JPanel();
	jpInstruction = new Instruction(); 
	jpLobby = new Lobby();
	jpTwoStageGame_Ope = new TwoStageOperator();
	jpCutWireGame_Ope = new CutTheWireOperator();
	jpFindLocationGame_Ope = new FindTheLocationOperator();
	jpLogicGame_Ope_Ope = new LogicGameOperator();
	
	mainCardLayout = new CardLayout();		
	mainPanel = new JPanel(mainCardLayout);
	
	mainPanel.add(jpLobby,"Lobby");
	mainPanel.add(jpInstruction,"Intruction");
	
	mainPanel.add(jpTwoStageGame_Ope,"TwoStageGame_Operator");
	mainPanel.add(jpCutWireGame_Ope,"CutWireGame_Operator");
	mainPanel.add(jpFindLocationGame_Ope,"FindLocationGame_Operator");
	mainPanel.add(jpLogicGame_Ope,"LogicGame_Operator");
	
	add(mainPanel);
	//show instruction page
	mainCardLayout(mainPanel,"Instruction");
	
	
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		
	setVisible(true);
	
}
