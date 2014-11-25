import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Instruction extends JPanel {
	JTextField jtaInstruction;
	JButton jbStart;
	this.setSize(500,500);	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	//add instruction
	jbStart = new JButton("Start");
	jtaInstruction = new JTextArea("Here is Instructions");
	
	this.add(jbStart);
	this.add(jtaInstruction);
	
	jbStart.addAction(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
			cg.getCardLayout().(cg.getMainPanel,"Lobby");
				//send command to server to let it know
			}
		});
	
}