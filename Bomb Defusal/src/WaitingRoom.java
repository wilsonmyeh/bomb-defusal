import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WaitingRoom extends JPanel{
	WaitingRoom(){
		this.setSize(500,500);
		this.setLayout(new BorderLayout());
		JLabel jl = new JLabel("Congratulations on defusing the bomb! Please wait for supervisor to choose the next game");
		this.add(jl);
	}
}
