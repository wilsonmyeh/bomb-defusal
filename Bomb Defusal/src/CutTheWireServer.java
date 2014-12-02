import java.util.Random;

class CutTheWireServer extends BaseMiniGameServer {
	private final int GAME_ID = 2;

	public CutTheWireServer(Server s) {
		super(s);
		Random rand = new Random();
		int randomNum = rand.nextInt(10);
		//sendCommand( GAME_ID + "Random " + randomNum);
	}

	void parseCommand(String command) {

	}
}