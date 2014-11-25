import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EndSplash extends JPanel{
	private int thisTeam;
	private int winner;
	private int timeMS;
	private JLabel endMessage;
	private JTextField teamName;
	private JButton sendTeamInfo;
	
	
	public EndSplash(int thisTeam){
		this.setSize(500,500);	
		this.thisTeam = thisTeam;
		endMessage = new JLabel();
		teamName = new JTextField(20);
		sendTeamInfo = new JButton();
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
	}
	
	public void setWinner(int winner, int timeMS){
		this.winner = winner;
		this.timeMS = timeMS;
		if(thisTeam == winner){
			
		}
	}
}
