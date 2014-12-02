import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Should be the gateway between gameservers and individual clients
class Server {
	BaseMiniGameServer[] team0 = new BaseMiniGameServer[4];
	BaseMiniGameServer[] team1 = new BaseMiniGameServer[4]; // 0=FindTheLocation,
	// 1=Light,
	// 2=CutWire,
	// 3=LogicPuzzle
	PrintWriter[] out0 = new PrintWriter[2]; // 0=Operator, 1=Supervisor
	PrintWriter[] out1 = new PrintWriter[2]; // 0=Operator, 1=Supervisor

	ServerThread[] players = new ServerThread[4];

	int playerCount;// check for connection
	long timerStart, timerEnd, timerResult;
	int team1Status, team2Status;// -1=lose, 0=in progress, 1=win

	public Server(int port) {
		

		try {
			ServerSocket ss = new ServerSocket(port);
			int clients = 0;
			while (clients < 4) {
				try {
					Socket s = ss.accept();
					if (clients < 2) // Who's what is based on who joins first
					{
						out0[clients] = new PrintWriter(s.getOutputStream());
						out0[clients].println(clients);
						out0[clients].flush();
					} else {
						out1[clients % 2] = new PrintWriter(s.getOutputStream());
						out1[clients % 2].println(clients);
						out1[clients % 2].flush();
					}
					players[clients] = new ServerThread(s);
					players[clients].start();
					clients++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//5 second wait to start
			try {
				Thread.sleep(5000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			team0[0] = new FindTheLocationServer(this, 0);
			team0[1] = new LightServer(this, 0);
			team0[2] = new CutTheWireServer(this, 0);
			team0[3] = new LogicGameServer(this, 0);

			team1[0] = new FindTheLocationServer(this, 1);
			team1[1] = new LightServer(this, 1);
			team1[2] = new CutTheWireServer(this, 1);
			team1[3] = new LogicGameServer(this, 1);
			//"Kicks" everyone to lobby to start the game
			out0[0].println("5");
			out0[0].flush();
			out0[1].println("5");
			out0[1].flush();
			out1[0].println("5");
			out1[0].flush();
			out1[1].println("5");
			out1[1].flush();
			
			timerStart = System.currentTimeMillis();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
	void gamesAvailable(int teamNumber) { //Returns 4 character string.
		String temp = "";					//K=Kickable, U=Unavailble, A=Availble
		for (int i = 0; i < 4; i++) {
			if(teamNumber == 0) {
				if(team1[i].kickable)
					temp+="K";
				else if(team1[i].active)
					temp+="U";
				else temp+="A";
			}
			else if(teamNumber == 1) {
				if(team0[i].kickable)
					temp+="K";
				else if(team1[i].active)
					temp+="U";
				else temp+="A";
			}
		}
		if(teamNumber == 0)
		{
			out0[0].println(6+temp);
			out0[0].flush();
			out0[1].println(6+temp);
			out0[1].flush();
		}
		else if(teamNumber == 1)
		{
			out1[0].println(6+temp);
			out1[0].flush();
			out1[1].println(6+temp);
			out1[1].flush();
		}
	}*/

	void parse(String line) { // <Team#><Game#><Content> (Game#=4 refers to chat,
		// Game#=5 refers to kick, Game#=6 refers to checkGamesAvailable, Game#=8 refers to starting a game)
		// e.g. "01XXXX" refers to team 0's
		// TwoStagePuzzle
		int ind = (int) line.charAt(1) - 48;
		if(ind == 8) {
			int team = (int) line.charAt(0) - 48;
			int game = (int) line.charAt(2) - 48;
			if(team == 0) {
				out0[0].println("8"+game); //Only tell operators
				out0[0].flush();
			}
			else {
				out1[0].println("8"+game);
				out1[0].flush();
			}
		}
		else if(ind == 6) {
			int team = (int) line.charAt(0) - 48;
			BaseMiniGameServer[] select = (team==0) ? team0 : team1;
			boolean check = checkWin(select);
			System.out.println("check is " + check);
			if(check){
				System.out.println("WE WON FAM!");
				notifyVictor(team);
			}
			gamesAvailable(team);
		}
		else if(ind == 5) { //Kick Format: <YourTeam#><5><Game#>
			int team = (int) line.charAt(0) - 48;
			int game = (int) line.charAt(2) - 48;
			if(team == 0)
				kick(1,game);
			else kick(0,game);
		}
		else if (ind == 4) { //CHAT
			int target = Integer.parseInt(line.substring(2,3)); 
			System.out.println("Server->parse(String)->ind==4 received " + line + " TARGET: "+target);
			if(target != 3)
			{
				target++; 
			}
			String chatMessage = 4 + line.substring(3);
			switch(target)
			{
			case 1://Prints only to team 1 
				out0[0].println(chatMessage);
				out0[0].flush();
				out0[1].println(chatMessage); 
				out0[1].flush(); 
				break; 
			case 2://Prints only to team 2
				out1[0].println(chatMessage);
				out1[0].flush();
				out1[1].println(chatMessage); 
				out1[1].flush(); 
				break; 
			case 3: //Prints to everyone
				out1[0].println(chatMessage);
				out1[0].flush();
				out1[1].println(chatMessage); 
				out1[1].flush(); 
				out0[0].println(chatMessage);
				out0[0].flush();
				out0[1].println(chatMessage); 
				out0[1].flush();
				break; 
			}
		} else { //if it's a game 
			if (line.charAt(0) == '0') {
				team0[ind].parseCommand(line.substring(2));
			} else {
				team1[ind].parseCommand(line.substring(2));
			}
		}

	}

	boolean checkWin(BaseMiniGameServer[] team) {
		for (int i = 0; i < team.length; i++){
			if(team[i] == null){
				return false;
			}
			else if (!team[i].solved){
				return false;
			}
			System.out.println("Game " + i + " is solved.");
		}
		notifyVictor(team[1].teamNumber);
		return true;
	}

	// ends the game, displays winner
	void notifyVictor(int team) {
		System.out.println("notify victor wsa called");
		timerEnd = System.currentTimeMillis();
		long time = timerEnd - timerStart;
		
		out0[0].println("7"+team+time);
		out0[0].flush();
		out0[1].println("7"+team+time);
		out0[1].flush();
		out1[0].println("7"+team+time);
		out1[0].flush();
		out1[1].println("7"+team+time);
		out1[1].flush();
	}

	void sendCommand(int teamNum, String command) {
		System.out.println("Server->sendCommand(BMGS, STRING) sending " + command + " to team " + teamNum);
		//Syntax for sending to client, <GameNumber><Message>
		for (int i = 0; i < 2; i++) {
			if (teamNum == 0) {
				out0[i].println(command);
				out0[i].flush();
			} else {
				out1[i].println(command);
				out1[i].flush();
			}

		}
	}
	/*
	int indexOf(BaseMiniGameServer[] a, BaseMiniGameServer t) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == t)
				return i;
		}
		return -1;
	}*/

	class ServerThread extends Thread { // Just listens, no printing
		private Socket s;
		private BufferedReader br;
		private Lock lock = new ReentrantLock();

		public ServerThread(Socket s) {
			this.s = s;
			try {
				br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		public void run() {
			while (!checkWin(team0) && !checkWin(team1)) {
				try {
					String line = br.readLine();
					if(line != null){
						System.out.println("ServerThread while(true) received " + line);
						parse(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(checkWin(team0)){
			}
			else if(checkWin(team1)){
			}
		}
		// NEED TO DEFINE AND IMPLEMENT PARSE
	}

	public static void main(String args[]) {
		new Server(3469);
	}
	// commands:
	// team#,gametype,command
}
