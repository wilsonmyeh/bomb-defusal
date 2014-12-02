import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WaitingRoom extends JPanel{
	WaitingRoom(){
		this.setSize(500,500);
		this.setLayout(new BorderLayout());
		Font font = new Font("Verdana", Font.BOLD, 10);
		JLabel jl = new JLabel("Bomb defused! Please wait for supervisor to choose the next game");
		this.add(jl);
	}
}
