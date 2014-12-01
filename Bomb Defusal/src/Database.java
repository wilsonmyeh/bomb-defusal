import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database {
	private static Connection conn;
	
	public static void initialize(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://185.21.216.150:31204/bombgame", "bomb", "salmonhotdog42");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insert(String teamName, int timeMS){
		int timeS = timeMS/1000;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("INSERT INTO scores (team_name, time) VALUES (?, ?)");
			statement.setString(1, teamName);
			statement.setInt(2, timeS);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Vector<Entry> getRecords(){
		//SELECT team_name, time FROM scores ORDER BY time ASC LIMIT 10;
		Statement statement = null;
		ResultSet entrySet = null;
		Vector<Entry> entries = new Vector<Entry>();
		try{
			statement = conn.createStatement();
			entrySet = statement.executeQuery("SELECT team_name, time FROM scores ORDER BY time ASC LIMIT 10");
			entrySet.next();
			for(int i = 0; i < 10; i++){
				if(!entrySet.isAfterLast()){
					String teamName = entrySet.getString(1);
					int time = entrySet.getInt(2);
					entries.add(new Entry(teamName, time));
					entrySet.next();
				}
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return entries;
	}
	
	
}

class Entry{
	public String teamName;
	public int time;
	
	Entry(String teamName, int time){
		this.teamName = teamName;
		this.time = time;
	}
}
