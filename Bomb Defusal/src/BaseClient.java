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


	CardLayout mainCardLayout;
	JPanel mainPanel;
	
	public BaseClient()
	{
		pw = null;
		br = null; 
		Database.initialize();

	}
	
	//should display warning since you've just been kicked and should move you back to lobby (query server for what should be available) 
	void kick()
	{

	}
	public Chat getChat()
	{
		return chat; 
	}

	public void sendCommand(String command){
		if(pw == null)
		{
			try {
				pw = new PrintWriter(mySocket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		pw.println(command);
		pw.flush(); 
	}
	
}

