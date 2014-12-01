import javax.swing.JPanel;


abstract class BaseMiniGameClient extends JPanel{
	BaseClient bc;
	
	BaseMiniGameClient(BaseClient bc) {
		this.bc = bc;
	}
	
	public abstract void parseCommand(String command);
}