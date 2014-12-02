import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class CutTheWireOperator extends BaseMiniGameClient {
	private final int GAME_ID = 2;
	JLabel[] wires;
	JLabel timer;
	int secondsLeft;
	Vector<Color[]> colorSchemes; 
	Color[] selectedScheme;
	boolean anyWireClicked; 
	int correctAnswer; 

	public CutTheWireOperator(BaseClient bc, int wireSet) {
		super(bc);
		setSize(new Dimension(500, 500));
		setLayout(null);
		anyWireClicked = false; 
		correctAnswer = wireSet; 
		JLabel title = new JLabel("Cut the right wire!");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font(getFont().getName(), Font.PLAIN, 24));
		title.setSize(new Dimension(300, 100));
		title.setLocation(100, 0);
		title.setOpaque(true);
		JLabel instructions = new JLabel("Your Supervisor knows the right one. So ask!");
		instructions.setHorizontalAlignment(SwingConstants.CENTER);
		instructions.setFont(new Font(getFont().getName(), Font.PLAIN, 18));
		instructions.setSize(new Dimension(500, 100));
		instructions.setLocation(25, 280);
		instructions.setOpaque(true);
		// title.setBackground(Color.red);
		colorSchemes = new Vector<Color[]>(); 
		wires = new JLabel[5];
		wires[0] = new JLabel();
		wires[0].setOpaque(true);
		wires[0].setSize(new Dimension(300, 10));
		wires[0].setLocation(100, 110);
		wires[0].setText("0"); 
		wires[1] = new JLabel();
		wires[1].setOpaque(true);
		wires[1].setSize(new Dimension(300, 10));
		wires[1].setLocation(100, 140);
		wires[1].setText("1"); 
		wires[2] = new JLabel();
		wires[2].setOpaque(true);
		wires[2].setSize(new Dimension(300, 10));
		wires[2].setLocation(100, 170);
		wires[2].setText("2"); 
		wires[3] = new JLabel();
		wires[3].setOpaque(true);
		wires[3].setSize(new Dimension(300, 10));
		wires[3].setLocation(100, 200);
		wires[3].setText("3"); 
		wires[4] = new JLabel();
		wires[4].setOpaque(true);
		wires[4].setSize(new Dimension(300, 10));
		wires[4].setLocation(100, 230);
		wires[4].setText("4"); 
		/*timer = new JLabel();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = BorderFactory.createTitledBorder(blackline,
				"Timer");
		border.setTitleJustification(TitledBorder.CENTER);
		timer.setBorder(border);
		timer.setSize(new Dimension(60, 60));
		timer.setForeground(Color.red);
		timer.setLocation(400, 400);
		timer.setHorizontalAlignment(SwingConstants.CENTER);*/
		JLabel[] blackWires = new JLabel[5];
		for(int i = 0; i < 5; i++)
		{
			blackWires[i] = new JLabel(); 
			blackWires[i].setSize(new Dimension(100, 3));
			blackWires[i].setOpaque(true);
			blackWires[i].setBackground(Color.black);
			blackWires[i].setLocation(0, 113+30*i);
		}
		JLabel[] blackWires2 = new JLabel[5]; 
		for(int i = 0; i < 5; i++)
		{
			blackWires2[i] = new JLabel(); 
			blackWires2[i].setSize(new Dimension(100, 3));
			blackWires2[i].setOpaque(true);
			blackWires2[i].setBackground(Color.black);
			blackWires2[i].setLocation(400, 113+30*i);
		}
		add(wires[0]);
		add(wires[1]);
		add(wires[2]);
		add(wires[3]);
		add(wires[4]);
		for(int i = 0; i < 5; i++)
		{
			add(blackWires[i]);
			add(blackWires2[i]); 
		}
		//add(timer);
		add(title);
		add(instructions); 
		//setButtons(); 
		Color[] scheme1 = {Color.red, Color.blue, Color.yellow, Color.green, Color.cyan};//Cut the red one
		Color[] scheme2 = {Color.red, Color.red, Color.red, Color.red, Color.red}; //Cut the second one
		Color[] scheme3 = {Color.green, Color.yellow, Color.green, Color.yellow, Color.green}; //cut the green one
		Color[] scheme4 = {Color.red, Color.red, Color.red, Color.blue, Color.red}; //cut the blue one
		Color[] scheme5 = {Color.blue, Color.red, Color.blue, Color.blue, Color.blue}; //last blue one
		colorSchemes.add(scheme1); 
		colorSchemes.add(scheme2);
		colorSchemes.add(scheme3);
		colorSchemes.add(scheme4);
		colorSchemes.add(scheme5);
		setButtons(wireSet); 
		setVisible(true);
		
	}

	// Randomizes color scheme
	public void setButtons(int puzzleNum) {
	correctAnswer = puzzleNum; 
	for(int i = 0; i < 5; i++)
	{
		wires[i].addMouseListener(new myMouseAdapter());
	}
	for(int i = 0; i < 5; i++)
	{
		wires[i].setBackground(colorSchemes.get(puzzleNum)[i]);
		wires[i].setForeground(colorSchemes.get(puzzleNum)[i]); 
	}
	}

	public void start() {//start timer for kick
		int timeDelay = 1000;
		secondsLeft = 15;
		ActionListener time;
		time = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (secondsLeft >= 0) {
					timer.setText(Integer.toString(secondsLeft--));
				}
			}
		};

		new Timer(timeDelay, time).start();
	}
	
	public void parseCommand(String command)
	{
		if(command.startsWith("Win")){
			bc.switchToWaitingRoom();
		}
	}
	/*public static void main(String[] args) {

		JFrame test = new JFrame();
		test.setSize(500, 500);
		test.getContentPane().add(new CutTheWireOperator());
		test.setVisible(true);
	}*/
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		Random rand = new Random();
		int puzzleNum = rand.nextInt(5)+1; 
		correctAnswer = puzzleNum; 
		for(int i = 0; i < 5; i++)
		{
			wires[i].setBackground(colorSchemes.get(puzzleNum)[i]);
			wires[i].setForeground(colorSchemes.get(puzzleNum)[i]); 
			wires[i].setOpaque(true); 
		}
		anyWireClicked = false; 
		
	}
	class myMouseAdapter extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) {
			if(!anyWireClicked)
			{
			anyWireClicked = true; 
	        ((JComponent) e.getComponent()).setOpaque(false); 
	        int myChoice = Integer.parseInt(((JLabel) e.getComponent()).getText());
	        System.out.println("MYCHOICE:"+myChoice+" CORRECT:"+correctAnswer);
	        if(myChoice != correctAnswer)
	        {
	        	/*((OperatorGUI) bc).restartCW(); */
	        	repaint(); 
	        	revalidate(); 
	        }
	        else
	        {
	        	System.out.println("WON");
	        	bc.sendCommand(GAME_ID+"Win");
	        }
	        repaint();
	        revalidate(); 
			}
	    }
	}
}
