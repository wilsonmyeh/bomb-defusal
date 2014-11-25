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
															// 1=TwoStage,
															// 2=CutWire,
															// 3=LogicPuzzle
	PrintWriter[] out0 = new PrintWriter[2]; // 0=Operator, 1=Supervisor
	PrintWriter[] out1 = new PrintWriter[2]; // 0=Operator, 1=Supervisor

	ServerThread[] players = new ServerThread[4];

	int playerCount;// check for connection
	long timerTeam1, timerTeam2;
	int team1Status, team2Status;// -1=lose, 0=in progress, 1=win

	public Server(int port) {
		team0[0] = new FindTheLocationServer();
		team0[1] = new TwoStageServer();
		team0[2] = new CutTheWireServer();
		team0[3] = new LogicGameServer();

		team1[0] = new FindTheLocationServer();
		team1[1] = new TwoStageServer();
		team1[2] = new CutTheWireServer();
		team1[3] = new LogicGameServer();

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
			// Start the game, assign each client a team# and role
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void kick() {

	}

	boolean[] gamesAvailable() {
		boolean[] temp = new boolean[4];
		for (int i = 0; i < 4; i++) {
			temp[i] = (!team0[i].active && !team1[i].active);
		}
		return temp;
	}

	void parse(String line) { // <Team#><Game#><Content> (Game#=4 refers to
								// chat) e.g. "01XXXX" refers to team 0's
								// TwoStagePuzzle
		int ind = (int) line.charAt(1) - 48;
		if (ind == 4) {
			int target = (int) line.charAt(0) - 48; 
			switch(target)
			{
			case 1: {
				out0[0].println(line.substring(2));
				out0[0].flush();
				out0[1].println(line.substring(2)); 
				out0[1].flush(); 
			}
			case 2:{
				out1[0].println(line.substring(2));
				out1[0].flush();
				out1[1].println(line.substring(2)); 
				out1[1].flush(); 
			}
			case 3:{
				out1[0].println(line.substring(2));
				out1[0].flush();
				out1[1].println(line.substring(2)); 
				out1[1].flush(); 
				out0[0].println(line.substring(2));
				out0[0].flush();
				out0[1].println(line.substring(2)); 
				out0[1].flush();
			}
				
			}
		} else {
			if (line.charAt(0) == 0) {
				team0[ind].parseCommand(line.substring(2));
			} else {
				team1[ind].parseCommand(line.substring(2));
			}
		}

	}

	boolean checkWin(BaseMiniGameServer[] team) {
		for (int i = 0; i < team.length; i++)
			if (!team[i].solved)
				return false;
		return true;
	}

	// ends the game, displays winner
	void notifyVictor() {

	}

	void sendCommand(BaseMiniGameServer mg, String command) {

		for (int i = 0; i < team1.length; i++) {
			if (indexOf(team1, mg) != -1) {
				out0[i].println(command);
				out0[i].flush();
			} else {
				out1[i].println(command);
				out1[i].flush();
			}

		}
	}

	int indexOf(BaseMiniGameServer[] a, BaseMiniGameServer t) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == t)
				return i;
		}
		return -1;
	}

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

		@Override
		public void run() {
			while (!checkWin(team0) && !checkWin(team1)) {
				try {
					String line = br.readLine();
					parse(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
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