import java.util.Random;

class LogicGameServer extends BaseMiniGameServer{
	private final int GAME_ID = 3;
	Random rand;
	int randomNum;
	
	LogicGameServer(Server s, int teamNum){
		super(s, teamNum);
		rand = new Random();
		randomNum = rand.nextInt(10);
		sendCommand(GAME_ID + "Random " + randomNum);
	}
	void parseCommand(String command) {
		// TODO Auto-generated method stub
		if(command.startsWith("Win")){
			sendCommand(GAME_ID + "Win");
			solved = true;
			active = false;			
		}
		else if(command.startsWith("Reset")){
			rand = new Random();
  			int temp = rand.nextInt(10);
  			while(temp == randomNum)
				temp = rand.nextInt(10);
			randomNum = temp;
			sendCommand(GAME_ID + "Reset " + randomNum);
		}
	}
}
