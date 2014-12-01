import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class FindTheLocationOperator extends BaseMiniGameClient{
	private final int GAME_ID = 0;
	private JButton analyze;
	private int percentage;
	
	FindTheLocationOperator(BaseClient bc){
		super(bc);
		percentage = 0;
		analyze = new JButton("Analyze");
		analyze.setAlignmentX(CENTER_ALIGNMENT);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(300));
		add(analyze);
		
		analyze.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				bc.sendCommand(GAME_ID + "analyze");
			}
			
		});
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(200, 225, 100, 25);
		g.fillRect(200, 225, percentage, 25);
		g.setColor(Color.red);
		g.drawString(percentage + "%", 240, 243);
	}

	@Override
	public void parseCommand(String command) {
		if(command.startsWith("percentage")){
			String[] data = command.split(" ");
			percentage = Integer.parseInt(data[1]);
			repaint();
		}
		else if(command.startsWith("reset")){
			percentage = 0;
			repaint();
		}
		else if(command.startsWith("win")){
			bc.switchToLobby();
		}
	}
	
	public static void main(String[] args){
		JFrame test = new JFrame();
		test.setSize(500, 500);
		BaseMiniGameClient testPanel = new FindTheLocationOperator(null);
		testPanel.parseCommand("percentage 20");
		test.add(testPanel);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

}
