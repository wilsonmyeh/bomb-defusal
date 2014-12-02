//should only talk to THE server
abstract class BaseMiniGameServer {
	boolean active;
	boolean solved;
	public int teamNumber;
	Server server;
	boolean kickable;
	BaseMiniGameServer me = this;
	Thread timer = new Thread() {
		public void run() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				//If the puzzle is solved before it becomes kickable, interrupt thread 'timer'
				return;
			}
			kickable = true;
			//server.sendCommand("kickable")
		}
	};
	
	public BaseMiniGameServer(Server s, int teamNum) {
		System.out.println("set teamNum to " + teamNum);
		this.teamNumber = teamNum;
		this.server = s;
		kickable = false;
		active = false;
	}
	
	
	abstract void parseCommand(String command);
		//if(start) { timer.start(); active = true; } or something liek that
	
	void sendCommand(String command){
		server.sendCommand(teamNumber, command);
		
	}
	
}