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
		
		jtaInstruction = new JTextArea("You are on TEAM: " + teamNum + " and you are a " + identity + ".");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 1");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 2");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 3");
		jtaInstruction.setText(jtaInstruction.getText() + "Instruction for game 4");
		
		add(Box.createGlue());
		this.add(jtaInstruction);
		add(Box.createGlue());
		//depends on supervisor or operator
		if(identity.equals("Supervisor"))
			this.add(jbStart);
		else
			this.add(jlWait);
		add(Box.createGlue());
	
		jbStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"Lobby");
			}
		});
	}
}
