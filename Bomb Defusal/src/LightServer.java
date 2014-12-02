import java.util.Random;

class LightServer extends BaseMiniGameServer {
	private final int GAME_ID = 1;
	Random rand = new Random();
	
	private boolean[] lights;
	private byte[] buttons; //map from button# to light#
	
	/*
	private final int MAX_PRESSURE = 100000;
	private final int MARGIN_OF_ERROR = 5000;
	
	private int pressure; //0 to 100
	private int threshold;
	*/
	
	public LightServer(Server s) {
		super(s);
		lights = new boolean[6];
		for(int i = 0;i < lights.length;i++)
			lights[i] = (Math.abs(rand.nextInt()) % 2) == 1;
		
		buttons = new byte[6];
		for(byte i = 0;i < buttons.length;i++)
			buttons[i] = i;
		for(int i = 0;i < 100;i++)
			swap(buttons,Math.abs(rand.nextInt()) % 6,Math.abs(rand.nextInt()) % 6);
		
		/*
		pressure = 0;
		threshold = rand.nextInt() % (MAX_PRESSURE/2) + (MAX_PRESSURE/4);
		*/
		
	}
	
	void parseCommand(String command) {
		//TODO: Add shit
		if(command.startsWith("START")) {
			active = true;
			timer.start();
			sendLights();
			//sendCommand(GAME_ID + "VALVE THRESHOLD " + threshold);
			//sendPressure();
		}
		
		else if(command.startsWith("LIGHT")) { //LIGHT <button>
			String[] data = command.split(" ");
			int light = buttons[Integer.parseInt(data[1])];
			lights[light] = !lights[light]; //Toggles light and adjacent lights
			if(light != 0)
				lights[light-1] = !lights[light-1];
			if(light != 5)
				lights[light+1] = !lights[light+1];
			sendLights();
			checkLightWin();
		}
		/*
		else if(command.startsWith("VALVE")) { //VALVE <rate>
			String[] data = command.split(" ");
			int rate = Integer.parseInt(data[1]);
			pressure += rate;
			if(pressure > MAX_PRESSURE)
				pressure = MAX_PRESSURE;
			if(rate == 0)
				checkValveWin();
		}
		*/
		
		else if(command.startsWith("WIN")) {
			solved = true;
			active = false;
			timer.interrupt();
		}
	}
	
	void checkLightWin() {
		for(int i = 0;i < lights.length;i++)
			if(!lights[i])
				return;
		sendCommand(GAME_ID + "LIGHT WIN");
	}
	
	void sendLights() {
		String s = GAME_ID + "LIGHT "; //LIGHTS <on/off values>
		for(int i = 0;i < lights.length;i++)
			s += (lights[i]) ? "1" : "0";
		sendCommand(s);
	}
	
	/*
	void checkValveWin() {
		if(pressure >= threshold && pressure <= threshold+MARGIN_OF_ERROR)
		{
			sendCommand(GAME_ID + "VALVE WIN");
		}
	}

	void sendPressure() {
		String s = GAME_ID + "VALVE " + pressure; //VALVE <pressure>
		sendCommand(s);
	}
	*/
	
	void swap(byte[] arr, int a, int b) {
		arr[a] += arr[b];
		arr[b] = (byte) (arr[a] - arr[b]);
		arr[a] -= arr[b];
	}
}
