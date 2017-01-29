package org.areillan.game.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.UIDInfo;
import org.areillan.game.system.mysql.SQLEntryHandler;
import org.areillan.game.system.mysql.SQLManager;
import org.areillan.game.system.mysql.impl.RegionSQLHandler;

/**
 * Holds the system configurations from the database.
 * @author Vexia
 *
 */
public class SystemConfig {
	
	/**
	 * The staff accounts.
	 */
	private final Map<String, StaffAccount> staff = new HashMap<>();

	/**
	 * The system-object configurations.
	 */
	private final Map<String, Object> configs = new HashMap<>();

	/**
	 * The list of beta user names.
	 */
	private final List<String> betaUsers = new ArrayList<>();

	/**
	 * Constructs a new {@Code SystemConfig} {@Code Object}
	 */
	public SystemConfig() {
		staff.put("mod_gallus", new StaffAccount(true) {
			@Override
			public boolean validate(UIDInfo info) {
				return true;
			}
		});
	}

	/**
	 * Parses system configurations from the SQL database.
	 */
	public void parse() {
		Connection connection = SQLManager.getConnection();
		if (connection == null) {
			return;
		}
		staff.clear();
		betaUsers.clear();
		configs.clear();
		try {

			
			ResultSet set = connection.createStatement().executeQuery("SELECT * FROM aincradc_server.configs");
			if (set == null) {
				SQLManager.close(connection);
				return;
			}
			while (set.next()) {
				parseConfig(set.getString(1), set.getString(2), set.getString(3));
			}
			set = connection.createStatement().executeQuery("SELECT * FROM aincradc_server.staff_accounts");
			if (set == null) {
				SQLManager.close(connection);
				return;
			}
			StaffAccount acc = null;
			while (set.next()) {
				acc = new StaffAccount(set.getBoolean(2));
				acc.getSerials().addAll(split(set.getString(3), ","));
				acc.getMacs().addAll(split(set.getString(4), ","));
				staff.put(set.getString(1), acc);
			}
			SQLManager.close(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			SQLManager.close(connection);
		}
	}

	/**
	 * Parses a config using the SQL info given.
	 * @param key The key identity.
	 * @param value The value.
	 * @param dataType The data type to parse.
	 */
	private void parseConfig(String key, String value, String dataType) {
		if (dataType == null) {
			configs.put(key, value);
			return;
		}
		if (value == null || value == "") {
			return;
		}
		switch (dataType) {
		case "int":
			configs.put(key, Integer.valueOf(value));
			break;
		case "double":
			break;
		case "boolean":
			break;
		case "date":
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date parsed = null;
			try {
				parsed = format.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			configs.put(key, parsed);
			break;
		default:
			configs.put(key, value);
			break;
		}
	}

	/**
	 * Checks if the player passes the system configuration validation.
	 * @param player The player.
	 * @return {@code True} if successfull.
	 */
	public boolean validLogin(Player player) {
		return player.isAdmin();
	}
	
	/**
	 * Checks if double experience is active.
	 * @return {@code True} if active.
	 */
	public boolean isDoubleExp() {
		Date date = getConfig("dxp", null);
		if (date == null) {
			return false;
		}
		return date.after(new Date());
	}

	/**
	 * Splits the data into an array list using a regex.
	 * @param data the data.
	 * @param regex the regex to split.
	 * @return the list of data.
	 */
	public List<String> split(String data, String regex) {
		if (!data.contains(regex)) {
			List<String> split = new ArrayList<>();
			split.add(data);
			return split;
		}
		List<String> split = new ArrayList<>();
		String[] tokens = data.trim().split(regex);
		for (String s : tokens) {
			split.add(s);
		}
		return split;
	}

	/**
	 * Checks if a username is an administrator.
	 * @param name The name.
	 * @return {@code True} if administrator.
	 */
	public boolean isAdmin(String name) {
		return name.equalsIgnoreCase("mod_gallus") || name.equalsIgnoreCase("mod_mystery") || name.equalsIgnoreCase("nuaris") || name.equalsIgnoreCase("mod_baylo");
	}

	/**
	 * Checks if a username is a staff member.
	 * @param name The name.
	 * @return {@code True} if so.
	 */
	public boolean isStaff(String name) {
		return staff.containsKey(name);
	}

	/**
	 * Checks if a username is a beta user.
	 * @param name The name.
	 * @return {@code True} if so.
	 */
	public boolean isBetaUser(String name) {
		return betaUsers.contains(name);
	}

	/**
	 * Gets an attribute.
	 * @param key The attribute name.
	 * @return The attribute value.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getConfig(String key) {
		if (!configs.containsKey(key)) {
			return null;
		}
		return (T) configs.get(key);
	}

	/**
	 * Gets an attribute.
	 * @param string The attribute name.
	 * @param fail The value to return if the attribute is null.
	 * @return The attribute value, or the fail argument when null.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getConfig(String string, T fail) {
		Object object = configs.get(string);
		if (object != null) {
			return (T) object;
		}
		return fail;
	}

	/**
	 * Gets the configs.
	 * @return the configs.
	 */
	public Map<String, Object> getConfigs() {
		return configs;
	}

	/**
	 * Gets the betaUsers.
	 * @return the betaUsers.
	 */
	public List<String> getBetaUsers() {
		return betaUsers;
	}

	/**
	 * Gets the staff.
	 * @return the staff.
	 */
	public Map<String, StaffAccount> getStaffAccounts() {
		return staff;
	}

}
