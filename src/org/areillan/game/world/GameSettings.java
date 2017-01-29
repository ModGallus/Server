package org.areillan.game.world;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.areillan.net.Constants;

/**
 * Represents the game settings used for this game instance.
 * @author Vexia
 */
public final class GameSettings {

	/**
	 * The name of the namme.
	 */
	private final String name;
	
	/**
	 * The name of the login clan.
	 */
	private final String clan;

	/**
	 * If the game is in beta mode.
	 */
	private final boolean beta;

	/**
	 * If the game is in developer mode.
	 */
	private final boolean devMode;

	/**
	 * If the gui is enabled.
	 */
	private final boolean gui;

	/**
	 * The world id of the server.
	 */
	private final int worldId;

	/**
	 * The country index.
	 */
	private final int countryIndex;

	/**
	 * The activity.
	 */
	private final String activity;

	/**
	 * If the world is members only.
	 */
	private final boolean members;

	/**
	 * If the world is a pvp world.
	 */
	private final boolean pvp;

	/**
	 * If only quick chat can be used on the world.
	 */
	private final boolean quickChat;

	/**
	 * If lootshare option is enabled on this world.
	 */
	private final boolean lootshare;

	/**
	 * The address of the Management server.
	 */
	private final String msAddress;


	/**
	 * Constructs a new {@code GameSettings} {@code Object}.
	 * @param name the name.
	 * @param beta the beta.
	 * @param type the game type.
	 * @param gui if gui is enabled.
	 * @param worldId the world id.
	 * @param countryIndex The country index.
	 * @param members If the world is members only.
	 * @param msAddress The address of the Management server.
	 */
	GameSettings(String name, String clan, boolean beta, boolean devMode, boolean gui, int worldId, int countryIndex, String activity, boolean members, boolean pvp, boolean quickChat, boolean lootshare, String msAddress) {
		this.name = name;
		this.clan = clan;
		this.beta = beta;
		this.devMode = devMode;
		this.gui = gui;
		this.worldId = worldId;
		this.countryIndex = countryIndex;
		this.activity = activity;
		this.members = members;
		this.pvp = pvp;
		this.quickChat = quickChat;
		this.lootshare = lootshare;
		this.msAddress = msAddress;
	}

	/**
	 * Parses the game settings from the program arguments.
	 * @param args The program arguments.
	 * @return The game settings.
	 */
	public static GameSettings parse(String[] args) {
		String name = "areillan";
		String clan = "areillan";
		boolean beta = false;
		boolean devMode = true;
		boolean gui = true;
		int worldId = 1;
		int country = 0;
		String activity = "Default gameplay";
		boolean members = true;
		boolean pvp = false;
		boolean quickChat = true;
		boolean lootshare = true;
		String msAddress = Constants.DEFAULT_MS_IP;
		for (String arg : args) {
			if (arg.startsWith("name=")) {
				name = arg.replace("name=", "");
			} else if (arg.startsWith("clan")) {
				name = arg.replace("clan=", "");
			} else if (arg.startsWith("beta=")) {
				beta = Boolean.parseBoolean(arg.replace("beta=", ""));
			} else if (arg.startsWith("devMode=")) {
				devMode = Boolean.parseBoolean(arg.replace("devMode=", ""));
			} else if (arg.startsWith("gui=")) {
				gui = Boolean.parseBoolean(arg.replace("gui=", ""));
			} else if (arg.startsWith("world=")) {
				worldId = Integer.parseInt(arg.replace("world=", ""));
			} else if (arg.startsWith("country=")) {
				country = Integer.parseInt(arg.replace("country=", ""));
			} else if (arg.startsWith("activity=")) {
				activity = arg.replace("activity=", "");
			} else if (arg.startsWith("members=")) {
				members = Boolean.parseBoolean(arg.replace("members=", ""));
			} else if (arg.startsWith("pvp=")) {
				pvp = Boolean.parseBoolean(arg.replace("pvp=", ""));
			} else if (arg.startsWith("quickChat=")) {
				quickChat = Boolean.parseBoolean(arg.replace("quickChat=", ""));
			} else if (arg.startsWith("lootshare=")) {
				lootshare = Boolean.parseBoolean(arg.replace("lootshare=", ""));
			} else if (arg.startsWith("msip=")) {
				msAddress = arg.replace("msip=", "");
			} else {
				System.err.println("Unknown program argument specified: " + arg + ".");
			}
		}
		return new GameSettings(name, clan, beta, devMode, gui, worldId, country, activity, members, pvp, quickChat, lootshare, msAddress);
	}

	/**
	 * Parses a game settings file.
	 * @param path the path.
	 * @return the settings.
	 */
	public static GameSettings parse(final String path) {
		final Properties properties = getProperties(path);
		String address = Constants.DEFAULT_MS_IP;
		if (properties.containsKey("msip")) {
			address = properties.getProperty("msip");
		}
		return new GameSettings(properties.getProperty("name"), 
				properties.getProperty("clan"),
				Boolean.parseBoolean(properties.getProperty("beta")), 
				Boolean.parseBoolean(properties.getProperty("devMode")), 
				Boolean.parseBoolean(properties.getProperty("gui")), 
				Integer.parseInt(properties.getProperty("worldId")), 
				Integer.parseInt(properties.getProperty("country")), 
				properties.getProperty("activity"),
				Boolean.parseBoolean(properties.getProperty("members")), 
				Boolean.parseBoolean(properties.getProperty("pvp")),
				Boolean.parseBoolean(properties.getProperty("quickChat")),
				Boolean.parseBoolean(properties.getProperty("lootshare")),
				address);
	}

	/**
	 * Gets the properties.
	 * @param path the path.
	 * @return the properties.
	 */
	private static Properties getProperties(String path) {
		FileInputStream file;
		Properties properties = new Properties();
		try {
			file = new FileInputStream(path);
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Gets the devMode.
	 * @return The devMode.
	 */
	public boolean isDevMode() {
		return devMode;
	}

	/**
	 * Gets the name.
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the clan
	 * @return the clan
	 */
	public String getClan() {
		return clan;
	}
	
	/**
	 * Gets the beta.
	 * @return The beta.
	 */
	public boolean isBeta() {
		return beta;
	}

	/**
	 * Checks if the server is hosted publicly.
	 * @return {@code True} if so.
	 */
	public boolean isHosted() {
		return !devMode;
	}

	/**
	 * Gets the gui.
	 * @return The gui.
	 */
	public boolean isGui() {
		return gui;
	}

	/**
	 * Gets the worldId.
	 * @return The worldId.
	 */
	public int getWorldId() {
		return worldId;
	}

	/**
	 * Gets the countryIndex.
	 * @return the countryIndex
	 */
	public int getCountryIndex() {
		return countryIndex;
	}

	/**
	 * Gets the members.
	 * @return the members
	 */
	public boolean isMembers() {
		return members;
	}

	/**
	 * Gets the msAddress.
	 * @return the msAddress
	 */
	public String getMsAddress() {
		return msAddress;
	}

	/**
	 * Gets the activity value.
	 * @return The activity.
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * Gets the pvp value.
	 * @return The pvp.
	 */
	public boolean isPvp() {
		return pvp;
	}

	/**
	 * Gets the quickChat value.
	 * @return The quickChat.
	 */
	public boolean isQuickChat() {
		return quickChat;
	}

	/**
	 * Gets the lootshare value.
	 * @return The lootshare.
	 */
	public boolean isLootshare() {
		return lootshare;
	}
	
	@Override
	public String toString() {
		return "GameSettings [name=" + name + ", clan=" + clan + ", beta=" + beta + ", devMode=" + devMode + ", gui=" + gui + ", worldId=" + worldId + "]";
	}
}
