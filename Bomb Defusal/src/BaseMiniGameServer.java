//should only talk to THE server
abstract class BaseMiniGameServer {
	boolean active;
	boolean solved;
	int teamNumber;
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
			server.sendCommand(me,"KICK ME HARD"); 
		}
	};
	
	public BaseMiniGameServer() {
		kickable = false;
		active = false;
	}
	
	
	abstract void parseCommand(String command);
		//if(start) { timer.start(); active = true; } or something liek that
	
	void sendCommand(String command){
		server.sendCommand(this,command);
		
	}
	
}