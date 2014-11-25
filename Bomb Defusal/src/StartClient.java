public class StartClient {
	BaseClient bc;
	//Client(IP, port){
		//200*500 size
	//}
	
	//here goes the connection to the server
	StartClient(){
		
	}
	//assign each player supervisor or operator
	//return 1 for supervisor, return -1 for operator
	static int parse(){
		return -1;
	}
	public static void main(String [] args){
		//wait for server to assign
		if(parse() == -1)
			new OperatorGUI();
		else
			new SupervisorGUI();
	}
}
