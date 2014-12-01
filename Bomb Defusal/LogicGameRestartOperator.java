import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LogicGameRestartOperator extends JPanel {
	JButton jb;
	 LogicGameRestartOperator() throws InterruptedException{
		this.setSize(50,500);
		this.setLayout(new BorderLayout());
		JLabel jl = new JLabel("YOU ARE WRONG. Hit restart and wait for 5 seconds.");
		jb = new JButton("Restart");
		add(jb,BorderLayout.SOUTH);
		add(jl,BorderLayout.CENTER);
		
		
		
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				long startTime = System.nanoTime();
				long finishTime = System.nanoTime();
				long time = (finishTime - startTime) / 1000000000;
				while(time < 5){
					finishTime = System.nanoTime();
					time = (finishTime - startTime) / 1000000000;
				}
				StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"LogicGame_Operator");
			}
		});
	}
}