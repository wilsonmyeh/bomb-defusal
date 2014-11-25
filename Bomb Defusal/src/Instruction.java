import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Instruction extends JPanel {
	private JTextArea jtaInstruction;
	private JButton jbStart;
	
	Instruction(){
		this.setSize(500,500);	
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//add instruction
		jbStart = new JButton("Start");
		jtaInstruction = new JTextArea("Here is Instructions");
	
		this.add(jbStart);
		this.add(jtaInstruction);
	
		jbStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//ClientGUI cg
				//cg.getCardLayout().(cg.getMainPanel,"Lobby");
				//send command to server to let it know
			}
		});
	}
}
