import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Instruction extends JPanel {
	private JTextArea jtaInstruction;
	private JButton jbStart;
	private JLabel jlWait;
	private String identiry, teamNum;
	
	Instruction(String identity, String teamNum){
		this.setSize(500,500);	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		if(identity.equals("Supervisor"))
			jbStart = new JButton("Start");
		else 
			jlWait = new JLabel("Wait for supervisor to choose a game");
		
		
		jtaInstruction = new JTextArea("You are on TEAM: " + teamNum + " and you are a " + identity + "." + "\n");
		
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 1" + "\n");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 2" + "\n");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 3" + "\n");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 4" + "\n");
		
		add(Box.createGlue());
		this.add(jtaInstruction);
		add(Box.createGlue());
		//depends on supervisor or operator
		if(identity.equals("Supervisor"))
			this.add(jbStart);
		else
			this.add(jlWait);
		add(Box.createGlue());
	
		if(identity.equals("Supervisor")){
			jbStart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"Lobby");
				}
			});
		}
	}
}
