package me.tiliondc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class MyDatabaseconnector {
	
	private final static Logger logger = Bukkit.getLogger();
	
	private final static String ADRESS = (String) MyConfigurations.getSettings().get(MyConfigurations.DATABASE_HOST);
	private final static int 	PORT = (Integer) MyConfigurations.getSettings().get(MyConfigurations.DATABASE_PORT);
	private final static String USERNAME = (String) MyConfigurations.getSettings().get(MyConfigurations.DATABASE_USERNAME);
	private final static String PASSWORD = (String) MyConfigurations.getSettings().get(MyConfigurations.DATABASE_PASSWORD);
	private final static String DATABASE = (String) MyConfigurations.getSettings().get(MyConfigurations.DATABASE_NAME);
	private final static String TABLE_PLAYER_ACTIVITY = "PayerActivity";
	
	private final static String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS " + DATABASE + ";";

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://" + ADRESS + ":" + PORT + "/";
	
	Connection connection = null;
	Statement statement = null;
	
	public MyDatabaseconnector() {
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			statement.executeQuery(CREATE_DATABASE);
			statement.executeQuery(PlayerInfo.CREATE_TABLE);
		} catch (SQLException e) {
			logger.info("Could not connect to database");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.info("No database driver exists");
			e.printStackTrace();
		}
		
	}
	
	public class PlayerInfo {
		
		public static final String Player_ID = "id";
		public static final String Player_Name = "name";
		
		
		private final static String CREATE_TABLE =
				"CREATE TABLE IF NOT EXISTS " + TABLE_PLAYER_ACTIVITY 
				+ "("
				+ " id VARCHAR(255),"
				+ " name VARCHAR(255),"
				+ " PRIMARY KEY ( id )"
				+ ");"; 
		
		public String getName(String ID) {
			
			return Bukkit.getServer().getPlayer(UUID.fromString(ID)).getName();
		}
		
		public List<String> getIDFromDisplayName(String name) {
			List<String> list = new ArrayList<>();
			for(OfflinePlayer player : Bukkit.getServer().getOfflinePlayers())
				if(player.getName() == name)
					list.add(player.getUniqueId().toString());
			return list;
		}
		
		
		
		
	}
	
}
