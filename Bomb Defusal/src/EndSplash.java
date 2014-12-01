import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EndSplash extends JPanel{
	private int thisTeam;
	private int winner;
	private int timeMS;
	private JPanel entryPanel;
	
	private JPanel endMessagePanel;
	private JLabel endMessage;
	
	private JPanel records;
	
	private JLabel entryInfo;
	private JTextField teamName;
	private JButton sendTeamInfo;
	
	
	public EndSplash(int thisTeam){
		this.setSize(500,500);	
		this.thisTeam = thisTeam;
		this.setLayout(new BorderLayout());
		entryPanel = new JPanel();
		records = new JPanel();
		records.setLayout(new BoxLayout(records, BoxLayout.Y_AXIS));
		records.setAlignmentX(CENTER_ALIGNMENT);
		records.add(Box.createVerticalGlue());
		
		entryInfo = new JLabel("Team Name:");
		
		endMessagePanel = new JPanel();
		endMessage = new JLabel();
		endMessage.setAlignmentX(CENTER_ALIGNMENT);
		teamName = new JTextField(20);
		sendTeamInfo = new JButton("Submit score");
		entryInfo.setVisible(false);
		teamName.setVisible(false);
		sendTeamInfo.setVisible(false);
		sendTeamInfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String toInsert = teamName.getText();
				toInsert = toInsert.trim();
				if(!toInsert.equals("")){
					Database.insert(toInsert, timeMS);
					sendTeamInfo.setEnabled(false);
				}
			}
		});
		
		entryPanel.add(entryInfo);
		entryPanel.add(teamName);
		entryPanel.add(sendTeamInfo);
		endMessagePanel.add(endMessage);
		add(entryPanel, BorderLayout.SOUTH);
		add(records, BorderLayout.CENTER);
		add(endMessagePanel, BorderLayout.NORTH);
	}
	
	public void setWinner(int winner, int timeMS){
		this.winner = winner;
		this.timeMS = timeMS;
		if(thisTeam == winner){
			endMessage.setText("Congratulations! You won.");
			entryInfo.setVisible(true);
			teamName.setVisible(true);
			sendTeamInfo.setVisible(true);
		}
		else{
			endMessage.setText("You didn't make it. Better luck next time!");
		}
		
		populateRecords(Database.getRecords());
	}
	
	public void populateRecords(Vector<Entry> recordsVector){
		for(int i = 0; i < recordsVector.size(); i++){
			JLabel winner = new JLabel(recordsVector.get(i).teamName + " " + recordsVector.get(i).time);
			winner.setAlignmentX(CENTER_ALIGNMENT);
			records.add(winner);
		}
		records.add(Box.createVerticalGlue());
	}
	
	public static void main(String[] args){
		JFrame test = new JFrame();
		test.setSize(500, 500);
		
		Database.initialize();
		EndSplash testPanel = new EndSplash(0);
		testPanel.setWinner(0, 54345000);
		test.add(testPanel);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}


