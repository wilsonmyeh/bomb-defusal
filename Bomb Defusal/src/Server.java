//Should be the gateway between gameservers and individual clients
class Server {
	BaseMiniGameServer[] team0 = new BaseMiniGameServer[4];
	BaseMiniGameServer[] team1 = new BaseMiniGameServer[4]; //0=FindTheLocation, 1=TwoStage, 2=CutWire, 3=LogicPuzzle
	PrintWriter[] out0 = new PrintWriter[2]; //0=Operator, 1=Supervisor
	PrintWriter[] out1 = new PrintWriter[2]; //0=Operator, 1=Supervisor
	
	ServerThread[] players = new ServerThread[4];
	
	int playerCount;//check for connection
	long timerTeam1, timerTeam2; 
	int team1Status, team2Status;//-1=lose, 0=in progress, 1=win 
	
	public Server(int port) {
		team0[0] = new FindTheLocationServer();
		team0[1] = new TwoStageServer();
		team0[2] = new CutTheWireServer();
		team0[3] = new LogicPuzzleServer();
		
		team1[0] = new FindTheLocationServer();
		team1[1] = new TwoStageServer();
		team1[2] = new CutTheWireServer();
		team1[3] = new LogicPuzzleServer();
		
		try {
			ServerSocket ss = new ServerSocket(port);
			int clients = 0;
			while(clients < 4) {
				try {
					Socket s = ss.accept();
					if(clients < 2) //Who's what is based on who joins first
						out0[clients] = new PrintWriter(s.getOutputStream());
					else out1[clients % 2] = new PrintWriter(s.getOutputStream());
					players[clients] = new ServerThread(s,clients);
					players[clients].start();
					clients++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//Start the game, assign each client a team# and role
		}
	}
	
	void kick( Client specificClient) //Kicks a specific client to 'lobby view' 
	{
		//specificClient.cardLayout.show(lobby);
		specificClient.kick();
	}
	
	boolean[] gamesAvailable() {
		boolean[] temp = new boolean[4];
		for(int i = 0;i < 4;i++) {
			temp[i] = (!team0[i].active && !team1[i].active);
		}
		return temp;
	}
	
	void parse(String line) {  //<Team#><Game#><Content> (Game#=4 refers to chat) e.g. "01XXXX" refers to team 0's TwoStagePuzzle
		int ind = (int)line.charAt(1) - 48;
		if(ind == 4) {
			//TODO: Chat stuff
		}
		else {
			if(line.charAt(0) == 0) {
				team0[ind].parse(line.substring(2));
			}
			else {
				team1[ind].parse(line.substring(2));
			}
		}
		
	}
	
	boolean checkWin(BaseMiniGameServer[] team) {
		for(int i = 0;i < team.length;i++)
			if(!team[i].solved)
				return false;
		return true;
	}
	
	//ends the game, displays winner
	void notifyVictor()
	{
		
	}
	
	void sendCommand(BaseMiniGameServer mg, String command){
		
		for(int i = 0;i < team1.length;i++) {
			if(Arrays.indexOf(team1,mg) != -1) {
				out1[i].println(command);
				out1[i].flush();
			}
			else {
				out2[i].println(command);
				out2[i].flush();
			}
			
		}
				
	}
	
	class ServerThread extends Thread { //Just listens, no printing
		private Socket s;
		private BufferedReader br;
		private Lock lock = new ReentrantLock();
		
		public ServerThread(Socket s) {
			this.s = s;
			try {
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(!checkWin(team0) && !checkWin(team1)) {
				String line = br.readLine();
				parse(line);
			}
		}
		
		//NEED TO DEFINE AND IMPLEMENT PARSE
	}
	
	public static void main(String args[]) {
		
	}
	//commands:
	//team#,gametype,command
}