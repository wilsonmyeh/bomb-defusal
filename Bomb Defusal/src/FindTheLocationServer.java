import java.util.Random;

/*
 * 
 * //should only talk to THE server
abstract class BaseMiniGameServer {
	boolean active;
	boolean solved;
	int teamNumber;
	int gameNumber;
	Server server;
	boolean kickable;
	BaseMiniGameServer me = this;
	Thread timer = new Thread() {
		public void run() {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
 */

class FindTheLocationServer extends BaseMiniGameServer{
	private final int GAME_ID = 0;
	private int currentX;
	private int currentY;
	private int desiredX;
	private int desiredY;
	private int currentPercent;
	private int currentDistance;
	private int maxDistance;
	private Random rand;
	
	FindTheLocationServer(Server s){
		super(s);
		rand = new Random();
	}
	//Message possibilities
	/*
	 * op to server
	 * analyze
	 * 
	 * sup to server
	 * location x y
	 * finalize
	 * 
	 * server to op
	 * win
	 * percent
	 * reset
	 * 
	 * server to sup
	 * win
	 * reset
	 */
	
	
	void setDesiredLocation(){
		desiredX = rand.nextInt(500);
		desiredY = rand.nextInt(400);
		
		int maxXDistance = desiredX;
		if((500 - desiredX) > maxXDistance){
			maxXDistance = 500 - desiredX;
		}
		
		int maxYDistance = desiredY;
		if((400 - desiredY) > maxYDistance){
			maxYDistance = 500 - desiredY;
		}
		
		maxDistance = Math.abs((int) Math.sqrt((double)((desiredX - maxXDistance) * (desiredX - maxXDistance) + (desiredY - maxYDistance) * (desiredY - maxYDistance))));
	}
	
	void reset(){
		currentX = -1;
		currentY = -1;
		setDesiredLocation();
	}
	
	void setCurrentLocation(int x, int y){
		currentX = x;
		currentY = y;
		
		currentDistance = Math.abs((int) Math.sqrt((double)((desiredX - currentX) * (desiredX - currentX) + (desiredY - currentY) * (desiredY - currentY))));
		currentPercent= 100 - currentDistance/maxDistance;
	}
	
	boolean checkWin(){
		if(currentPercent >= 90){
			active = false;
			solved = true;
			sendCommand(GAME_ID + "win");
		}
		else{
			sendCommand(GAME_ID + "reset");
		}
		return (currentPercent >= 90);
	}

	@Override
	void parseCommand(String command) {
		if(command.startsWith("location")){
			String[] data = command.split(" ");
			setCurrentLocation(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
		}
		
		if(command.startsWith("finalize")){
			checkWin();
		}
	}
}