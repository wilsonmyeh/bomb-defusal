import java.util.Random;

class LogicGameServer extends BaseMiniGameServer{
	private final int GAME_ID = 3;
	Random rand;
	int randomNum;
	
	@Override
	LogicGameServer(){
		rand = new Random();
		randomNum = rand.nextInt(10);
		sendCommand("Random " + rand);
	}
	void parseCommand(String command) {
		// TODO Auto-generated method stub
		if(command.startsWith("Win")){
			sendCommand("Win");
		}
		else if(command.startsWith("Reset")){
			temp = rand.nextInt(10);
			while(temp == randomNum)
				temp = rand.nextInt(10);
			randomNum = temp;
			sendCommand("Reset " + temp);
		}
	}
}