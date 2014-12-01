import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class FindTheLocationSupervisor extends BaseMiniGameClient{
	private final int GAME_ID = 0;
	private Random rand;
	private Image image;
	private int currentImageIndex;
	private int currentX;
	private int currentY;
	private JButton finalize;
	
	public FindTheLocationSupervisor(BaseClient bc){
		super(bc);
		rand = new Random();
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if(x < 500 && y < 400){
					currentX = x;
					currentY = y;
					updateCrosshairs();
				}
		    }
		});
		
		currentX = -1;
		currentY = -1;
		currentImageIndex = -1;
		setImage(-1);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		finalize = new JButton("Finalize.");
		finalize.setAlignmentX(CENTER_ALIGNMENT);
		finalize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				bc.sendCommand(GAME_ID + "finalize");
			}
			
		});
		add(finalize);
		add(Box.createVerticalStrut(50));
	}
	
	public void updateCrosshairs(){
		String command = GAME_ID + "location " + currentX + " " + currentY;
		bc.sendCommand(command);
		repaint();
		//send message to server
	}
	
	public void setImage(int notMe){
		int imageIndex = -1;
		String imageIndexStr;
		String imageLocation;
		if(notMe == -1){
			imageIndex = rand.nextInt(5);
			currentImageIndex = imageIndex;
			imageIndexStr = String.valueOf(imageIndex);
			imageLocation = "Assets/Location/" + imageIndexStr + ".jpg";
			image = new ImageIcon(imageLocation).getImage(); 
		}
		else{
			while(imageIndex == -1){
				imageIndex = rand.nextInt(5);
				if(imageIndex == notMe){
					imageIndex = -1;
				}
				else{
					currentImageIndex = imageIndex;
					imageIndexStr = String.valueOf(imageIndex);
					imageLocation = "Assets/Location/" + imageIndexStr + ".jpg";
					image = new ImageIcon(imageLocation).getImage();
				}
			}
		}
		repaint();
	}
	
	public void reset(){
		currentX = -1;
		currentY = -1;
		setImage(currentImageIndex);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image != null){
			g.drawImage(image, 0, 0, null);
		}
		if(currentX != -1 && currentY != -1){
			g.setColor(Color.red);
			g.drawLine(0, currentY, 500, currentY);
			g.drawLine(currentX, 0, currentX, 400);	
		}
		
	}
	
	public static void main(String[] args){
		JFrame test = new JFrame();
		test.setSize(500, 500);
		JPanel testPanel = new FindTheLocationSupervisor(null);
		test.add(testPanel);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

	@Override
	public void parseCommand(String command) {
		if(command.startsWith("win")){
			bc.switchToLobby();
		}
		else if(command.startsWith("reset")){
			reset();
		}
	}
}

