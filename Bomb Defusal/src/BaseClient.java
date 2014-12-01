import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;


abstract class BaseClient extends JFrame
{
	protected Socket mySocket; 
	protected String myUserName; //This is your role in the game (i.e Supervisor 1))
	protected int team;
	protected Chat chat; 
	protected PrintWriter pw;
	protected BufferedReader br;
	public BaseMiniGameClient[] gameClients = new BaseMiniGameClient[4];


	CardLayout mainCardLayout;
	JPanel mainPanel;
	
	public BaseClient(Socket s)
	{
		pw = null;
		br = null; 
		mySocket = s; 
		Database.initialize();

	}
	
	//should display warning since you've just been kicked and should move you back to lobby (query server for what should be available) 
	void kick()
	{
		//TODO: DISPLAY WARNING
		switchToLobby();
	}
	
	public Chat getChat()
	{
		return chat; 
	}

	public void sendCommand(String command){
		if(pw == null)
		{
			try {
				if(mySocket == null)
				{
					System.out.println("HARD LIFE");
				}
				pw = new PrintWriter(mySocket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		//System.out.println("TEAM+COMMAND"+team+command);
		pw.println(team+command);
		pw.flush(); 
		System.out.println("BaseClient->sendCommand(String) sent " + team + command);
	}
	
	public void routeCommand(String command){
		System.out.println("BaseClient->routeCommand(String) received " + command);
		int ind = Character.getNumericValue(command.charAt(0));
		switch(ind){
			case 0:{
				gameClients[0].parseCommand(command.substring(1));
				break;
			}
			
			case 1:{
				gameClients[1].parseCommand(command.substring(1));
				break;
			}
			
			case 2:{
				gameClients[2].parseCommand(command.substring(1));
				break;
			}
			
			case 3:{
				gameClients[3].parseCommand(command.substring(1));
				break;
			}
			
			case 4:{
				getChat().addText(command.substring(1));
				break;
			}
			
			case 5:{
				kick();
				break;
			}
			
			case 6:{
				//TODO: Alter lobby
				break;
			}
			
			case 7:{
				if(command.charAt(1)-48 == team)
					victory();
				else defeat();
				break; 
			}
			
			case 8:{
				int game = (int)command.charAt(1)-48;
				//TODO: Switch card to proper game
			}
		}
	}
	
	public JPanel getMainpanel(){
		return mainPanel;
	}
	public CardLayout getCardLayout(){
		return mainCardLayout;
	}
	
	public void switchToLobby(){
		sendCommand("6");
		try {
			Thread.sleep(1000); //Some time for Lobby to update
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		getCardLayout().show(getMainpanel(), "Lobby");
	}
	
	public void victory(){
		//TODO: vic
	}
	
	public void defeat(){
		//TODO: shit
	}
	
	//for operator
	public void switchToWaitingRoom(){
		getCardLayout().show(this, "WaitingRoom");
	}
}

