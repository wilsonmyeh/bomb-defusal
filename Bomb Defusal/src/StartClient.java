import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class StartClient {
	static BaseClient bc;
	private int role;
	private int teamNum;
	private Socket s; 
	private PrintWriter pw;
	private BufferedReader br;
	
	//here goes the connection to the server
	StartClient(String ip, int port){
		//need t0 connect  to server, first to connect: team 1 supervisor, then team 2 supervisor
		//then team 1 operator, then team 2 operator
		//role -1:operator  1:supervisor
		try {
			Socket s = new Socket(ip, port);
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			role = Integer.parseInt(br.readLine()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(role) {
		case 0 : bc = new OperatorGUI(0,s); 
				 break;
		case 1 : bc = new SupervisorGUI(0,s);
		         break;
		case 2 : bc = new OperatorGUI(1,s); 
		         break;
		case 3 : bc = new SupervisorGUI(1,s); 
		         break;
		default : System.out.println("Something went horribly wrong.");
		}
	}
	public static void main(String[] args){
		//wait for server to assign
		Scanner scan = new Scanner(System.in); 
		System.out.println("IP?");
		String ip = scan.nextLine(); 
		System.out.println("Port?");
		int port = scan.nextInt(); 
		new StartClient(ip,port); 
	}
}
