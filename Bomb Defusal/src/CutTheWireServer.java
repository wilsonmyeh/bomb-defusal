import java.util.Random;

class CutTheWireServer extends BaseMiniGameServer {
	private final int GAME_ID = 2;

	public CutTheWireServer(Server s, int teamNum) {
		super(s, teamNum);
		Random rand = new Random();
		int randomNum = rand.nextInt(10);
		//sendCommand( GAME_ID + "Random " + randomNum);
	}

	void parseCommand(String command) {
		if(command.contains("Win")){
			sendCommand(GAME_ID + "Win");
			solved = true;
			active = false;			
		}
	}
}