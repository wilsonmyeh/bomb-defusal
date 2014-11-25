import javax.swing.JPanel;

class SupervisorGUI extends BaseClient
{
		private JPanel jpTwoStageGame_Sup,jpCutWireGame_Sup,jpFindLocationGame_Sup,jpLogicGame_Sup;
	
		jpTwoStageGame_Sup = new TwoStageSupervisor();
		jpCutWireGame_Sup = new CutTheWireSupervisor();
		jpFindLocationGame_Sup = new FindTheLocationSupervisor();
		jpLogicGame_Sup = new LogicGameSupervisor();
		
			mainCardLayout = new CardLayout();		
		mainPanel = new JPanel(mainCardLayout);
		
		mainPanel.add(jpLobby,"Lobby");
		mainPanel.add(jpInstruction,"Intruction");
		
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