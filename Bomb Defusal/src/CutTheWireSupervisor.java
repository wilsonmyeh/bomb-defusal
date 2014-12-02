import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CutTheWireSupervisor extends BaseMiniGameClient {
	private final int GAME_ID = 2;
	JButton left, right;
	JLabel top, bottom;
	int page;
	JLabel[] wires;
	JLabel[] blackWires;
	String[] instructions;
	Vector<Color[]> colorSchemes; 

	public CutTheWireSupervisor(BaseClient bc) {
		super(bc); 
		page = 0;
		setSize(new Dimension(500, 500));
		wires = new JLabel[5];
		blackWires = new JLabel[10];
		setLayout(null);
		left = new JButton("<");
		right = new JButton(">");
		left.setLocation(50, 400);
		left.setSize(50, 50);
		right.setLocation(400, 400);
		right.setSize(50, 50);
		top = new JLabel("Manual for Cutting the Right Wire");
		top.setSize(400, 100);
		top.setLocation(150, 50);
		bottom = new JLabel("TESTING TEXT");
		bottom.setSize(400, 100);
		bottom.setLocation(150, 350);
		for (int i = 0; i < 5; i++) {
			wires[i] = new JLabel();
			wires[i].setSize(200, 15);
			wires[i].setOpaque(true);
			wires[i].setBackground(Color.red);
			wires[i].setLocation(150, 200 + 20 * i);
			add(wires[i]);
		}
		for (int i = 0; i < 5; i++) {
			blackWires[i] = new JLabel();
			blackWires[i].setSize(50, 3);
			blackWires[i].setOpaque(true);
			blackWires[i].setBackground(Color.black);
			blackWires[i].setLocation(100, 200 + 21 * i);
			add(blackWires[i]);
		}
		for (int i = 5; i < 10; i++) {
			blackWires[i] = new JLabel();
			blackWires[i].setSize(50, 3);
			blackWires[i].setOpaque(true);
			blackWires[i].setBackground(Color.black);
			blackWires[i].setLocation(350, 100 + 21 * i);
			add(blackWires[i]);
		}
		add(left);
		left.addActionListener(new leftActionListener());
		add(right);
		right.addActionListener(new rightActionListener());
		add(top);
		add(bottom);

		colorSchemes = new Vector<Color[]>(); 
		Color[] scheme1 = {Color.red, Color.blue, Color.yellow, Color.green, Color.cyan};//Cut the red one
		Color[] scheme2 = {Color.red, Color.red, Color.red, Color.red, Color.red}; //Cut the second one
		Color[] scheme3 = {Color.green, Color.yellow, Color.green, Color.yellow, Color.green}; //cut middle one
		Color[] scheme4 = {Color.red, Color.red, Color.red, Color.blue, Color.red}; //cut the blue one
		Color[] scheme5 = {Color.blue, Color.red, Color.blue, Color.blue, Color.blue}; //last blue one
		colorSchemes.add(scheme1); 
		colorSchemes.add(scheme2);
		colorSchemes.add(scheme3);
		colorSchemes.add(scheme4); 
		colorSchemes.add(scheme5); 
		
		setVisible(true); 
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(50, 50, 400, 400);
		switch (page) {
		case 0:
			bottom.setText("These are the cases for the wirecutting.");
			for(int i = 0; i < 5; i++)
			{
				wires[i].setOpaque(false); 
			}
			for(int i = 0; i < 10; i++)
			{
				blackWires[i].setOpaque(false); 
			}
			break;
		case 1:
			bottom.setText("Cut the red wire.");
			colorWires(colorSchemes.get(0)); 
			break;
		case 2:
			bottom.setText("Cut the second wire.");
			colorWires(colorSchemes.get(1)); 
			break;
		case 3:
			bottom.setText("Cut the middle green wire.");
			colorWires(colorSchemes.get(2)); 
			break;
		case 4:
			bottom.setText("Cut the odd wire out.");
			colorWires(colorSchemes.get(3)); 
			break;
		case 5:
			bottom.setText("Cut the second blue wire.");
			colorWires(colorSchemes.get(4)); 
			break;
		default:
			bottom.setText("Problem has occured... Restart");
			break;
		}
	}
	public void colorWires(Color[] arg)
	{
		for(int i = 0; i < 5; i++)
		{
			wires[i].setOpaque(true); 
			wires[i].setBackground(arg[i]);
		}
		for(int i = 0; i < 10; i++)
		{
			blackWires[i].setOpaque(true);
		}
	}
	/*public static void main(String[] args) {
		JFrame test = new JFrame();
		test.setSize(500, 500);
		test.getContentPane().add(new CutTheWireSupervisor());
		test.setVisible(true);
	}*/
	
	@Override
	public void parseCommand(String command) {
		if(command.startsWith("Win")){
			bc.switchToLobby();
			Lobby.DisableCutTheWire();
		}
	}
	
	class rightActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (page < 5) {
				page++;
			}
			repaint();
			revalidate();
		}

	}

	class leftActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (page > 0) {
				page--;
			}
			repaint();
			revalidate();
		}

	}
}
