package org.areillan.game.node.entity.player.info.login;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.areillan.game.component.CloseEvent;
import org.areillan.game.component.Component;
import org.areillan.game.content.global.tutorial.TutorialSession;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.portal.PlayerSQLManager;
import org.areillan.game.node.entity.player.link.HintIconManager;
import org.areillan.game.node.entity.player.link.emote.Emotes;
import org.areillan.game.system.SystemManager;
import org.areillan.game.system.mysql.SQLEntryHandler;
import org.areillan.game.system.mysql.impl.HighscoreSQLHandler;
import org.areillan.game.system.mysql.impl.WorldSQLHandler;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.repository.Repository;
import org.areillan.game.world.update.UpdateSequence;
import org.areillan.game.world.update.flag.player.AppearanceFlag;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.InterfaceContext;
import org.areillan.net.packet.out.Interface;
import org.areillan.plugin.Plugin;

/**
 * Sends the login configuration packets.
 * @author Emperor
 * 
 */
public final class LoginConfiguration {

	/**
	 * The login plugins.
	 */
	private static final List<Plugin<Object>> LOGIN_PLUGINS = new ArrayList<>();
	
	/**
	 * The lobby pane component.
	 */
	private static final Component LOBBY_PANE = new Component(549);

	/**
	 * The lobby interface close event.
	 */
	private static final Component LOBBY_INTERFACE = new Component(378).setCloseEvent(new CloseEvent() {
		@Override
		public boolean close(Player player, Component c) {
			return player.getLocks().isLocked("login");
		}
	});

	/**
	 * Constructs a new {@Code LoginConfiguration} {@Code Object}
	 */
	public LoginConfiguration() {
		/*
		 * empty.
		 */
	}

	/**
	 * Configures the lobby login.
	 * @param player The player.
	 */
	public static void configureLobby(Player player) {
		player.updateSceneGraph(true);
		if (!player.isArtificial() && TutorialSession.getExtension(player).getStage() >= TutorialSession.MAX_STAGE && player.getAttribute("login_type", LoginType.NORMAL_LOGIN) != LoginType.RECONNECT_TYPE) {
			sendLobbyScreen(player);
		} else {
			configureGameWorld(player);
		}
	}

	/**
	 * Sends the lobby interface-related packets.
	 * @param player The player.
	 */
	public static void sendLobbyScreen(Player player) {

		Repository.getLobbyPlayers().add(player);
		SQLEntryHandler.write(new WorldSQLHandler(player));
		player.getPacketDispatch().sendString(getLastLogin(player), 378, 116);
		player.getPacketDispatch().sendString("Welcome to " + GameWorld.getName(), 378, 115);
		player.getPacketDispatch().sendString("" + player.getDetails().getShop().getCredits(), 378, 96);
		player.getPacketDispatch().sendString(player.getDetails().getShop().getCredits() + " Aincrad credits", 378, 94);

		player.getPacketDispatch().sendString(SystemManager.getSystemConfig().getConfig("weeklyMessage", "Welcome to Aincrad!"), SystemManager.getSystemConfig().getConfig("messageInterface", 18), getMessageChild(SystemManager.getSystemConfig().getConfig("messageInterface", 18)));
		player.getPacketDispatch().sendString("You can gain more credits by voting, reporting bugs and various other methods of contribution.", 378, 93);
		player.getInterfaceManager().openWindowsPane(LOBBY_PANE);
		player.getInterfaceManager().setOpened(LOBBY_INTERFACE);
		PacketRepository.send(Interface.class, new InterfaceContext(player, 549, 2, 378, true));//PlayerDetails
		PacketRepository.send(Interface.class, new InterfaceContext(player, 549, 3, SystemManager.getSystemConfig().getConfig("messageInterface", 18), true));
		
	}
	/**
	 * Configures the game world.
	 * @param player The player.
	 */	
	public static void configureGameWorld(final Player player) {
		player.getConfigManager().reset();
		sendGameConfiguration(player);
		Repository.getLobbyPlayers().remove(player);
		player.setPlaying(true);
		UpdateSequence.getRenderablePlayers().add(player);
		RegionManager.move(player);
		player.getMusicPlayer().init();
		player.getUpdateMasks().register(new AppearanceFlag(player));
		player.getPlayerFlags().setUpdateSceneGraph(true);
		player.getStateManager().init();
		TutorialSession.getExtension(player).init();
	}

	/**
	 * Sends the game configuration packets.
	 * @param player The player to send to.
	 */
	public static void sendGameConfiguration(final Player player) {
		player.getInterfaceManager().openWindowsPane(new Component(player.getInterfaceManager().isResizable() ? 746 : 548));
		player.getInterfaceManager().openChatbox(137);
		player.getInterfaceManager().openDefaultTabs();
		welcome(player);
		config(player);
		for (Plugin<Object> plugin : LOGIN_PLUGINS) {
			try {
				plugin.newInstance(player);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		player.getCommunication().sync(player);
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendInfoUpdate(player);
		}
	}

	/**
	 * Method used to welcome the player.
	 * @param player the player.
	 */
	public static final void welcome(final Player player) {
		player.getPacketDispatch().sendMessage("Welcome to " + GameWorld.getName() + ".");

		if (player.getDetails().isMuted()) {
			player.getPacketDispatch().sendMessage("You are muted.");
			player.getPacketDispatch().sendMessage("To prevent further mutes please read the rules.");
		}
		if (SystemManager.getSystemConfig().isDoubleExp()) 
		{			
			player.getPacketDispatch().sendMessages("<col=5510CC>The double experience weekend is currently active in this world.</col>");

		}
		player.setAttribute("startTime", System.currentTimeMillis());
	}

	/**
	 * Method used to configure all possible settings for the player.
	 * @param player the player.
	 */
	public static final void config(final Player player) {
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.getSkills().refresh();
		player.getSkills().configure();
		player.getSettings().update();
		player.getInteraction().setDefault();
		player.getPacketDispatch().sendRunEnergy();
		player.getFamiliarManager().login();
		player.getInterfaceManager().openDefaultTabs();
		player.getGrandExchange().init();
		player.getPacketDispatch().sendString("Friends List - World " + GameWorld.getSettings().getWorldId(), 550, 3);
		player.getConfigManager().init();
		player.getAntiMacroHandler().init();
		player.getQuestRepository().syncronizeTab(player);
		player.getGraveManager().update();
		player.getInterfaceManager().close();
		player.getEmoteManager().refresh();
		player.getInterfaceManager().openInfoBars();
	}
	
	/**
	 * Gets the message child for the inter id.
	 * @param interfaceId The interface id.
	 * @return The child id.
	 */
	public static int getMessageChild(int interfaceId) {
		//15 = keys & lock
		//16 = fly swat
		//17 = person with question marks
		//18 = wise old man
		//19 = man & woman with mouth closed
		//20 = man & lock & key
		//21 = closed chests
		//22 = same as 15
		//23 = snowmen
		if (interfaceId == 16) {
			return 6;
		} else if (interfaceId == 17 || interfaceId == 15 || interfaceId == 18 || interfaceId == 19 || interfaceId == 21 || interfaceId == 22) {
			return 4;
		} else if (interfaceId == 20) {
			return 5;
		} else if(interfaceId == 23) {
			return 3;
		}
		return 0;
	}

	/** 
	 * Gets the last login string for the lobby.
	 * @param player the player.
	 * @return the last login.
	 */
	public static String getLastLogin(Player player) {
		String lastIp = (String) player.getDetails().getSqlManager().getTable().getColumn("lastGameIp").getValue();
		if (lastIp == null || lastIp == "") {
			lastIp = player.getDetails().getIpAddress();
		}
		String string = "You last logged in @timeAgo from: " + lastIp;
		long time = player.getDetails().getLastLogin();
		Date lastLogin = new Date(time);
		Date current = new Date();
		long diff = current.getTime() - lastLogin.getTime();
		int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		if (days < 1) {
			string = string.replace("@timeAgo", "earlier today");
		} else if (days == 1) {
			string = string.replace("@timeAgo", "yesterday");
		} else {
			string = string.replace("@timeAgo", days + " days ago");
		}
		return string;
	}

	/**
	 * Gets the loginPlugins.
	 * @return The loginPlugins.
	 */
	public static List<Plugin<Object>> getLoginPlugins() {
		return LOGIN_PLUGINS;
	}

}