import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class LogicGameSupervisor extends BaseMiniGameClient{
	private final int GAME_ID = 3;

	private Image imWilson, imSteven, imHaohan, imBlake, Wilson_large, Steven_large, background;
	private String [] stevenS = new String[10];
	private String [] wilsonS = new String[10];
	private String [] haohanS = new String[10];
	private String [] blakeS = new String[10];
	private String [] answer = new String[10];
	static Point point;
	static int randomNum;
	static String rule1, rule2;
	boolean firstTime = true;
	
	LogicGameSupervisor(BaseClient bc) throws IOException{
		super(bc);
		
		this.setSize(500,500);
		Image temp = ImageIO.read(new File("Assets/Logic/wilson.jpg"));
		imWilson = temp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Wilson_large = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		temp = ImageIO.read(new File("Assets/Logic/steven.jpg"));
		imSteven = temp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		Steven_large = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		temp = ImageIO.read(new File("Assets/Logic/haohan.jpg"));
		imHaohan = temp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		temp = ImageIO.read(new File("Assets/Logic/blake.jpg"));
		imBlake = temp.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		temp = ImageIO.read(new File("Assets/Logic/background.jpg"));
		background = temp.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		
		 rule1 = "Find the bomber and he will tell you which button to click on" ;
	     rule2  = "Else, game restarts in 5 seconds";
		
		//logic sentence pool
		stevenS[0] = "Steven: I didn't build the bomb.";
		wilsonS[0] = "Wilson: I guess the bomber is Steven or Haohan.";
		haohanS[0] = "Haohan: Blake built the bomb.";
		blakeS[0] = "Blake: Steven was telling the truth.";
		answer[0] = "Haohan";
		
		stevenS[1] = "Steven: Haohan built the bomb";
		wilsonS[1] = "Wilson: I guess the bomber is Steven or Haohan.";
		haohanS[1] = "Haohan: Wilson was telling the truth.";
		blakeS[1] = "Blake: Wilson was telling the truth.";
		answer[1] = "Steven";
		
		stevenS[2] = "Steven: Wilson is telling the truth";
		wilsonS[2] = "Wilson: I guess the bomber is Blake or Haohan.";
		haohanS[2] = "Haohan: I didn't built the bomb.";
		blakeS[2] = "Blake: Steven built the bomb.";
		answer[2] = "Blake";
		
		stevenS[3] = "Steven: I didn't built the bomb.";
		wilsonS[3] = "Wilson: Blake built the bomb.";
		haohanS[3] = "Haohan: I guess the bomber is Steven or Wilson.";
		blakeS[3] = "Blake: Haohan was telling the truth.";
		answer[3] = "Wilson";
		
		stevenS[4] = "Steven: Haohan and Wilson are not lying.";
		wilsonS[4] = "Wilson: Haohan is not the bomber";
		haohanS[4] = "Haohan: Wilson build the bomb.";
		blakeS[4] = "Blake: I didn't built the bomb";
		answer[4] = "Blake";
		
		stevenS[5] = "Steven: I didn't build the bomb.";
		wilsonS[5] = "Wilson: Haohan and Blake are not lying";
		haohanS[5] = "Haohan: I really have no idea.";
		blakeS[5] = "Blake: All I do is coding";
		answer[5] = "Steven";
		
		stevenS[6] = "Steven: Haohan and Wilson are not lying.";
		wilsonS[6] = "Wilson: Haohan is not the bomber";
		haohanS[6] = "Haohan: Wilson build the bomb.";
		blakeS[6] = "Blake: I didn't built the bomb";
		answer[6] = "Blake";
		
		stevenS[7] = "Steven: I don't know how to build a bomb.";
		wilsonS[7] = "Wilson: All I do is coding.";
		haohanS[7] = "Haohan: Steven does not look like a bomber.";
		blakeS[7] = "Blake: Steven and Haohan are not lying";
		answer[7] = "Wilson";
		
		stevenS[8] = "Steven: I spend all my time doing 5a.";
		wilsonS[8] = "Wilson: Steven is lying, he never start it.";
		haohanS[8] = "Haohan: I don't know who is the bomber.";
		blakeS[8] = "Blake: I'm sure Wilson is a good guy.";
		answer[8] = "Steven";
		
		stevenS[9] = "Steven: I have no idea";
		wilsonS[9] = "Wilson: I spend all my time doing 5a.";
		haohanS[9] = "Haohan: Wilson is lying, he never start it.";
		blakeS[9] = "Blake: I believe Haohan is telling the truth";
		answer[9] = "Wilson";
		
		//random num
		
	}
	
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(background, 0, 0, null);
	        g.setColor(Color.DARK_GRAY);
	        g.drawString(rule1,50,20);
	        g.drawString(rule2,50,35);
	        //click image
	        g.drawImage(imWilson, 50, 50, null);
	        g.drawImage(imSteven, 150, 50, null);
	        g.drawImage(imHaohan, 250, 50, null);
	        g.drawImage(imBlake, 350, 50, null);
	        //info image
	        g.drawImage(Steven_large, 50, 200, null);
	        g.drawImage(Wilson_large, 320, 300, null);
	        //info sentence
	        //dialog
	        g.drawLine(200, 200, 220, 220);
	        g.drawLine(200, 200, 220, 250);
	        g.drawLine(220, 220, 480, 220);
	        g.drawLine(480, 220, 480, 280);
	        g.drawLine(220, 250, 220, 280);
	        g.drawLine(220, 280, 480, 280);
	        
	        g.drawLine(320, 350, 300, 370);
	        g.drawLine(320, 350, 300, 420);
	        g.drawLine(300, 370, 20, 370);
	        g.drawLine(20, 370, 20,420);
	        g.drawLine(20,420, 300,420);
	        
	        //info sentence
	        g.setColor(Color.BLUE);
	        g.setFont(new Font("Helvetica", Font.BOLD, 10));
	        g.drawString(stevenS[randomNum],  250, 240);
	        g.drawString(wilsonS[randomNum], 30, 400);
	        //rule
	        g.setColor(Color.RED);
	        String rule3 = "FIND THE BOMBER!!! He is the only one lying.";
	        g.drawString(rule3, 100, 470);
	        
	        if(firstTime){
				addMouseListener(listener);
				firstTime = false;
			}
	        
	 }
	 
	 private void check(String name){
		 if(answer[randomNum].equals(name)){
			 String num = "";
			 if(name.equals("Wilson"))
				 num = 1 + "";
			 else if(name.equals("Steven"))
				 num = 2 + "";
			 else if(name.equals("Haohan"))
				 num = 3 + "";
			 else if(name.equals("Blake"))
				 num = 4 + "";
  			rule1 = name + ": You GOT me. Go click button " + num ;
  			rule2 = "";
  			this.repaint();
  		}
  		else if(!answer[randomNum].equals(name)){	
  			//rule1 = "Sorry you are wrong. Game restart in 5 minutes." ;
  			//rule2 = "";
  			//this.repaint();
  			//restart();
  			bc.sendCommand(GAME_ID + "Reset");
  			//StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"LogicRestart_Sup");
  			//restart();
		}
	 }
	 /*
	 public void restart(){
			//rule1 = "Find the bomber and he will tell you which button to click on" ;
		    //rule2  = "Else, game restart.";
			Random rand = new Random();
			int temp = rand.nextInt(10);
			while(temp == randomNum)
				temp = rand.nextInt(10);
			randomNum = temp;
			this.repaint(); 
	 }
	 
	 */
	 		//add action
		 private MouseListener listener = new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
	 	    	//get where the click is 
	 	    	point = e.getPoint();
	 	    	Rectangle wilsonImage = new Rectangle(50,50,100,100);
	 	    	Rectangle stevenImage = new Rectangle(150,50,100,100);
	 	    	Rectangle haohanImage = new Rectangle(250,50,100,100);
	 	    	Rectangle blakeImage = new Rectangle(350,50,100,100);
	 	    	//for shop
	 	    	if (stevenImage.contains(point)){
	 	    		System.out.println("Steven clicked");
	 	    		check("Steven");
	 	    	}
	 	    	else if (wilsonImage.contains(point)){
	 	    		System.out.println("Wilson clicked");
	 	    		check("Wilson");
	 	    		
	 	    	}
	 	    	else if (haohanImage.contains(point)){
	 	    		System.out.println("Haohan clicked");
	 	    		check("Haohan");
	 	    		
	 	    	}
	 	    	else if (blakeImage.contains(point)){
	 	    		System.out.println("Blake clicked");
	 	    		check("Blake");
	 	    		
	 	    	}
	 	    	repaint();
	 	    }
	     };
	     
	    

	@Override
	public void parseCommand(String command) {
		// TODO Auto-generated method stub
		if(command.startsWith("Win")){
			bc.switchToLobby();
		}
		else if(command.startsWith("Random")){
			String [] temp = command.split(" ");
			randomNum = Integer.parseInt(temp[1]);
		}
		else if(command.startsWith("Reset")){
			String [] temp = command.split(" ");
			randomNum = Integer.parseInt(temp[1]);
			StartClient.bc.mainCardLayout.show(StartClient.bc.mainPanel,"LogicRestart_Sup");
			this.repaint();
		}
	}

}
