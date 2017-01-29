package org.areillan.game.system.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.areillan.game.system.SystemLogger;
import org.areillan.game.system.SystemManager;
import org.areillan.game.system.mysql.impl.ComponentSQLHandler;
import org.areillan.game.system.mysql.impl.DoorConfigSQLHandler;
import org.areillan.game.system.mysql.impl.GroundSpawnSQLHandler;
import org.areillan.game.system.mysql.impl.ItemConfigSQLHandler;
import org.areillan.game.system.mysql.impl.MusicConfigSQLHandler;
import org.areillan.game.system.mysql.impl.NPCConfigSQLHandler;
import org.areillan.game.system.mysql.impl.NPCDropSQLHandler;
import org.areillan.game.system.mysql.impl.NPCSpawnSQLHandler;
import org.areillan.game.system.mysql.impl.RangeConfigSQLHandler;
import org.areillan.game.system.mysql.impl.RegionSQLHandler;
import org.areillan.game.system.mysql.impl.ShopSQLHandler;

/**
 * Manages the SQL connections.
 * @author Vexia
 * 
 */
public final class SQLManager {
	
	/**
	 * If the sql manager is locally hosted.
	 */
	public static final boolean LOCAL = true;

    /**
     * The database URL.
     */
    public static final String DATABASE_URL = (LOCAL ? "104.196.187.228" : "104.196.187.228") + ":3306/aincradc_global";

    /**
     * The username of the user.
     */
    private static final String USERNAME = (LOCAL ? "root" : "root");

    /**
     * The password of the user.
     */
    private static final String PASSWORD = (LOCAL ? "12051rjm" : "12051rjm");

	/**
	 * IF the sql manager is initialized.
	 */
	private static boolean initialized;

	/**
	 * Constructs a new {@code SQLManager} {@code Object}
	 */
	public SQLManager() {
		/**
		 * empty.
		 */
	}

	/**
	 * Initializes the sql manager.
	 */
	public static void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			initialized = false;
			return;
		}
		initialized = true;
		SystemManager.getSystemConfig().parse();
	}
	
	/**
	 * Pre-plugin parsing.
	 */
	public static void prePlugin() {
		try {
			new NPCConfigSQLHandler().parse();
			new ItemConfigSQLHandler().parse();
			new RegionSQLHandler().parse();
			new ComponentSQLHandler().parse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parses data from the database for the server post plugin loading.
	 */
	public static void postPlugin() {
		try {
			new ShopSQLHandler().parse();
			new NPCDropSQLHandler().parse();
			new NPCSpawnSQLHandler().parse();
			new DoorConfigSQLHandler().parse();
			new GroundSpawnSQLHandler().parse();
			new MusicConfigSQLHandler().parse();
			new RangeConfigSQLHandler().parse();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a connection.
	 * @return The connection.
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + DATABASE_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			SystemLogger.error(SQLManager.class, "Error: Mysql error message=" + e.getMessage() + ".");
		}
		return null;
	}

	/**
	 * Releases the connection so it's available for usage.
	 * @param connection The connection.
	 */
	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the initialized.
	 * @return the initialized
	 */
	public static boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets the bainitialized.
	 * @param initialized the initialized to set.
	 */
	public static void setInitialized(boolean initialized) {
		SQLManager.initialized = initialized;
	}

}
