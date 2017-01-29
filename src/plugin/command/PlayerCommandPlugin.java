package plugin.command;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import org.areillan.ServerConstants;
import org.areillan.cache.Cache;
import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.content.activity.ActivityManager;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.content.skill.member.construction.HouseLocation;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPBuilder;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.node.entity.player.info.portal.Perks;
import org.areillan.game.node.entity.player.link.IronmanMode;
import org.areillan.game.node.entity.player.link.RunScript;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.entity.player.link.appearance.Gender;
import org.areillan.game.node.entity.player.link.audio.Audio;
import org.areillan.game.node.entity.player.link.quest.Quest;
import org.areillan.game.node.entity.player.link.quest.QuestRepository;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.system.SystemLogger;
import org.areillan.game.system.SystemManager;
import org.areillan.game.system.SystemState;
import org.areillan.game.system.command.CommandPlugin;
import org.areillan.game.system.command.CommandSet;
import org.areillan.game.system.communication.ClanRepository;
import org.areillan.game.system.communication.CommunicationInfo;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.map.path.Pathfinder;
import org.areillan.game.world.map.zone.impl.DonatorZone;
import org.areillan.game.world.repository.Repository;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.ContainerContext;
import org.areillan.net.packet.out.ContainerPacket;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;
import org.areillan.tools.StringUtils;

/**
 * Handles a player command.
 * @author Lee
 */
public final class PlayerCommandPlugin extends CommandPlugin {

	private Player o;

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.PLAYER, CommandSet.DEVELOPER);
		return this;
	}

	@Override
	public boolean parse(Player player, String name, String[] arguments) {
		int id, amount;
		Player p;
		final Player target;
		String n = "";
		Location location = null;
		GameObject object = null;
		o = null;
		Object args;
		switch (name) {
		case "toreg"://these fucking kids are so goddam annoying
			o.getIronmanManager().setMode(IronmanMode.NONE);
			player.sendMessage("done...");
			o.sendMessage("<col=FF0000>You are no longer an ironman. Log out to see the ironman icon disappear.</col>");
			break;
		case "resetpin":
			if (arguments.length < 2) {
				player.sendMessage("Syntax error: ::resetpin oldpin");
				return true;
			}
			String oldPin = arguments[1];
			if (oldPin == null) {
				return true;
			}
			if (!player.getBankPinManager().hasPin()) {
				player.sendMessage("You don't have a pin.");
				return true;
			}
			if (!oldPin.equals(player.getBankPinManager().getPin())) {
				player.sendMessage("Your old pin doesn't match your current pin.");
				return true;
			}
			player.getBankPinManager().setPin(null);
			player.sendMessage("Your pin has been reset.");
			return true;
		case "bosses":
			player.getInterfaceManager().close();
			player.getPacketDispatch().sendString("<u>Boss destinations</u>", 239, 1);
			StringBuilder sb3 = new StringBuilder();
			for (int i = 0; i < ServerConstants.TELEPORT_BOSSES.length; i++) {
				sb3.append(ServerConstants.TELEPORT_BOSSES[i][1]);
				if (i != ServerConstants.TELEPORT_BOSSES.length - 1) {
					sb3.append(", ");
				}
			}
			player.getPacketDispatch().sendString("" + sb3.toString(), 239, 2);
			player.getPacketDispatch().sendString("<br>", 239, 3);
			player.getPacketDispatch().sendString("", 239, 4);
			player.getPacketDispatch().sendString("", 239, 5);
			player.getInterfaceManager().openComponent(239);
			return true;
		case "tele":
		case "item":
		case "pickup":
		case "bank":
		case "tob":
			if (player.getDetails().getRights() != Rights.ADMINISTRATOR && !player.getUsername().equalsIgnoreCase("nuaris")) 
			{
				player.sendChat("Hey, everyone, I just tried to do something very silly!");
			}
			return false;
		case "wagwag":
			if (player.getDetails().getRights() != Rights.ADMINISTRATOR && !player.getUsername().equalsIgnoreCase("nuaris")) {
				if (player.inCombat() || player.getLocks().isTeleportLocked()) {
					player.getPacketDispatch().sendMessage("You can't teleport right now.");
					return true;
				}
			}
			Location destination = null;
			String place = getArgumentLine(arguments);
			for (Object[] data : ServerConstants.TELEPORT_BOSSES) {
				for (int i = 1; i < data.length; i++) {
					if (place.equals(data[i])) {
						destination = (Location) data[0];
						break;
					}
				}
			}
			if (destination != null) {
				player.getTeleporter().send(destination, TeleportType.NORMAL);
			} else {
				player.getPacketDispatch().sendMessage("Could not locate teleport destination [name=" + place + "]!");
			}
			return true;
		case "claim":
		    try{
		        player.rspsdata(player, player.getUsername());
		        }catch(Exception e){
		    }
		break; 
		case "minigames":
		case "games":
			player.getInterfaceManager().close();
			player.getPacketDispatch().sendString("<u>Minigame Teleports</u>", 239, 1);
			StringBuilder sb11 = new StringBuilder();
			for (int i = 0; i < ServerConstants.TELEPORT_MINIGAMES.length; i++) {
				sb11.append(ServerConstants.TELEPORT_MINIGAMES[i][1]);
				if (i != ServerConstants.TELEPORT_MINIGAMES.length - 1) {
					sb11.append(", ");
				}
			}
			player.getPacketDispatch().sendString("Use ::tog (name of minigame) " + sb11.toString(), 239, 2);
			player.getPacketDispatch().sendString("<br>", 239, 3);
			player.getPacketDispatch().sendString("", 239, 4);
			player.getPacketDispatch().sendString("", 239, 5);
			player.getInterfaceManager().openComponent(239);
			return true;
		case "players":
			int count = Repository.getPlayers().size();
			int ironCount = 1;
			int ultIronCount = 0;
			for (Player p1 : Repository.getPlayers()) {
				if (p1.getIronmanManager().checkRestriction(IronmanMode.ULTIMATE)) {
					ultIronCount++;
				}
				if (p1.getIronmanManager().checkRestriction(IronmanMode.STANDARD)) {
					ironCount++;
				}
			}
			int regular = count - ironCount - ultIronCount;
			if (count == 1) {
				player.getPacketDispatch().sendMessage("There is 1 active player in this world.");
			} else {
				player.getPacketDispatch().sendMessage("There are " + count + " active players in this world: " + regular + " regular, " + ironCount + " iron, and " + ultIronCount + " ultimate iron.");
			}
			return player.getRights() == Rights.REGULAR_PLAYER;
		case "yell":
			if (!player.isDonator() && !player.isAdmin()) {
				player.getPacketDispatch().sendMessages("Join clan chat \"" + GameWorld.getName() + "\" to talk globally, or become a donator to have access to", "this benefit.");
				return true;
			}
			if (player.getDetails().isMuted()) {
				player.getPacketDispatch().sendMessage("You have been " + (player.getDetails().isPermMute() ? "permanently" : "temporarily") + " muted due to breaking a rule.");
				return true;
			}
			if(WorldCommunicator.isEnabled()){
				if(ClanRepository.getDefault().isBanned(player.getName())){
					player.sendMessages("You are temporarily unable to yell as you are banned from the main clan chat.", "Don't be annoying!");
					return true;
				}
			}
			if (player.getAttribute("yell-delay", 0.0) > GameWorld.getTicks()) {
				player.sendMessages("You have yelled in the last " + player.getDonatorType().getCooldown() + " seconds. Upgrade to an extreme donator to have", "unlimited yelling abilities.");
				return true;
			}
			String text = getArgumentLine(arguments);
		    if(text.contains("<img=") || text.contains("<br>") || text.contains("<col=") || text.contains("<shad=")){
				player.sendMessage("Bad! No images/text effects allowed in yell chat.");
				return true;
			}
		    if(text.contains("aq p")){
				return true;
			}
			int length = text.length();
			if (length > 100) {
				length = 100;
			}
			if (text.length() >= 2) {
				if (Character.isLowerCase(text.charAt(0))) {
					text = Character.toUpperCase(text.charAt(0)) + text.substring(1, length);
				}
				text = getYellPrefix(player) + text + "</col>";
				for (Player p1 : Repository.getPlayers()) {
					if (p1.isActive()) {
						p1.getPacketDispatch().sendMessage(text);
					}
				}
				if (player.getDonatorType().getCooldown() > 0 && !player.isStaff()) {
					player.setAttribute("yell-delay", (int) GameWorld.getTicks() + (player.getDonatorType().getCooldown() / 0.6));
				}
			} else {
				player.getPacketDispatch().sendMessage("Your message was too short.");
			}
			return true;
		case "togglenews":
			player.getSavedData().getGlobalData().setDisableNews(!player.getSavedData().getGlobalData().isDisableNews());
			player.sendMessage("<col=FF0000>" + (player.getSavedData().getGlobalData().isDisableNews() ? "You will no longer see news notifications." : "You will now see news notifications."));
			return true;
		case "commands":
		case "command":
		case "commandlist":
			sendCommands(player);
			return true;
		case "quests":
			sendQuests(player);
			return true;
		case "donate":
			sendDonationInfo(player);
			return true;
		case "reply":
			if(player.getInterfaceManager().isOpened()){
				player.sendMessage("Please finish what you're doing first.");
				return true;
			}
			if (player.getAttributes().containsKey("replyTo")) {
				player.setAttribute("keepDialogueAlive", true);
				final String replyTo = (String) player.getAttribute("replyTo", "").replaceAll("_", " ");
				player.setAttribute("runscript", new RunScript() {
					@Override
					public boolean handle() {
						CommunicationInfo.sendMessage(player, replyTo.toLowerCase(), (String) getValue());
						player.removeAttribute("keepDialogueAlive");
						return true;
					}
				});	
				player.getDialogueInterpreter().sendMessageInput(StringUtils.formatDisplayName(replyTo));
			} else {
				player.getPacketDispatch().sendMessage("You have not recieved any recent messages to which you can reply.");
			}
			return true;
		}
		return false;
	}

	/**
	 * Sends commands.
	 * @param player the player.
	 */
	private Location generateLocation(Player player) {
		Location random_location = player.getLocation().transform(RandomFunction.random(-15, 15), RandomFunction.random(-15, 15), 0);
		if (!RegionManager.isTeleportPermitted(random_location)) {
			return generateLocation(player);
		}
		if (!Pathfinder.find(player, random_location, false, Pathfinder.DUMB).isSuccessful()) {
			return generateLocation(player);
		}
		if (RegionManager.getObject(random_location) != null) {
			return generateLocation(player);
		}
		return random_location;
	}
	
	private Player punish(Player player, String target, String[] args, int type) {
		boolean perm = args[0].contains("perm");
		long duration = perm ? -1l : ((args.length > 2 ? Integer.parseInt(args[2]) : 2) * 24 * 60 * 60_000);
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, type, duration);
			return null;
		}
		player.sendMessage("Management server is offline, punishment could not be processed.");
		return null;
	}
 
	private void unpunish(Player player, String target, int type) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, type, 0L);
			return;
		}
		player.sendMessage("Management server is offline, removing punishment could not be processed.");
	}
	
	private void printInfo(Player player, String target) {
		if (WorldCommunicator.isEnabled()) {
			MSPacketRepository.sendPunishment(player, target, 7, 0L);
			return;
		}
	}
	
	private void checkBank(Player player, String[] args) {
		if (player.getDetails().getRights() == Rights.PLAYER_MODERATOR && !player.getZoneMonitor().isInZone("Moderator Zone")) {
			player.sendMessage("You can only use this command in the moderator room.");
			return;
		}
		Player o = Repository.getPlayer(args[1], true);
		if (o == null) {
			player.sendMessage("Unable to load player " + args[1]);
			return;
		}
		Item[] items = o.getBank().toArray();
		int size = 0;
		int[] slots = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				slots[size++] = i;
			}
		}
		int[] slot = new int[size];
		for (int i = 0; i < size; i++) {
			slot[i] = slots[i];
		}
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 89, 90, new Item[] {}, 0, false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 762, 89, 90, o.getBank().toArray(), false, slots));
		player.getInterfaceManager().open(new Component(12));
		player.getPacketDispatch().sendMessage("Checking " + o.getName() + "'s bank.");
	}

	/**
	 * Checks a players inventory.
	 * @param player the player.
	 * @param args the arguments.
	 */
	private void checkInvy(Player player, String[] args) {
		if (player.getDetails().getRights() == Rights.PLAYER_MODERATOR && !player.getZoneMonitor().isInZone("Moderator Zone")) {
			player.sendMessage("You can only use this command in the moderator room.");
			return;
		}
		Player o = Repository.getPlayer(args[1], true);
		if (o == null) {
			player.sendMessage("Unable to load player " + args[1]);
			return;
		}
		Item[] items = o.getInventory().toArray();
		int size = 0;
		int[] slots = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				slots[size++] = i;
			}
		}
		int[] slot = new int[size];
		for (int i = 0; i < size; i++) {
			slot[i] = slots[i];
		}
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 149, 0, 93, new Item[] {}, false));
		PacketRepository.send(ContainerPacket.class, new ContainerContext(player, 149, 0, 93, items, false, slots));
		player.getPacketDispatch().sendMessage("Checking " + o.getName() + "'s inventory.");
	}
	
	private void sendCommands(Player player) {
		if (player.getInterfaceManager().isOpened()) {
			player.sendMessage("Finish what you're currently doing.");
			return;
		}
		player.getInterfaceManager().close();
		player.getPacketDispatch().sendString("<u>" + GameWorld.getName() + " commands</u>", 239, 1);
		player.getPacketDispatch().sendString("Players (shows player count)<br>Togglenews (Toggles the news broadcasts.)<br>Toggleatk (Toggles left-click attack option.<br>Bosses (Shows all boss teleports.)", 239, 2);
		player.getPacketDispatch().sendString("", 239, 3);
		player.getPacketDispatch().sendString("", 239, 4);
		player.getPacketDispatch().sendString("", 239, 5);
		player.getInterfaceManager().openComponent(239);
	}

	/**
	 * Sends information about donating.
	 * @param player The player.
	 */
	private void sendDonationInfo(Player player) {
		player.getInterfaceManager().open(new Component(275));
		for (int i = 0; i < 257; i++) {
			player.getPacketDispatch().sendString("", 275, i);
		}
		int lineId = 11;
		player.getPacketDispatch().sendString("<col=8A0808>" + "Donation Information" + "</col>", 275, 2);
		for (String s : ServerConstants.MESSAGES) {
			player.getPacketDispatch().sendString("<col=8A0808>" + s + "<br><br></col>", 275, lineId++);
		}
	}
	/**
	 * Sends the quests.
	 * @param player the player.
	 */
	private void sendQuests(Player player) {
		player.getInterfaceManager().open(new Component(275));
		for (int i = 0; i < 257; i++) {
			player.getPacketDispatch().sendString("", 275, i);
		}
		String red = "<col=8A0808>";
		int lineId = 11;
		player.getPacketDispatch().sendString("<col=8A0808>" + "Available Quests" + "</col>", 275, 2);
		for (Quest q : QuestRepository.getQuests().values()) {
			player.getPacketDispatch().sendString(q.isCompleted(player) ? red + "<str> " + q.getName() + " <br><br>" : red + " " + q.getName() + " <br><br>", 275, lineId++);
		}
	}

	/**
	 * Gets the yell prefix for the given player.
	 * @param player The player.
	 * @return The prefix used in yell.
	 */
	private static String getYellPrefix(Player player) {
		String color = "<col=800080>";
		StringBuilder sb = new StringBuilder("[");
		if (player.getDetails().getRights().isVisible(player)) {
			Rights right = player.getAttribute("visible_rank", player.getDetails().getRights());
			switch (right) {
			case ADMINISTRATOR:
				color = "<col=009999>";
				break;
			case PLAYER_MODERATOR:
				color = "<col=81819B>";
				break;
			default:
				break;
			}
		}
		if (player.isDonator() && !player.isStaff()) {
			color = "<col=" + player.getDonatorType().getColor() + ">";
		}
		int icon = Rights.getChatIcon(player);
		if (icon > 0) {
			sb.append("<img=").append(icon - 1).append(">");
		}
		sb.append(color).append(player.getUsername()).append("</col>");
		sb.append("]: ").append(color);
		return sb.toString();
	}
}
