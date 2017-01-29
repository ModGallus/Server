package plugin.command;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import org.areillan.ServerConstants;
import org.areillan.cache.Cache;
import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.container.impl.EquipmentContainer;
import org.areillan.game.content.activity.ActivityManager;
import org.areillan.game.content.eco.ge.ResourceManager;
import org.areillan.game.content.global.tutorial.CharacterDesign;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPBuilder;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.entity.player.link.appearance.Gender;
import org.areillan.game.node.entity.player.link.quest.Quest;
import org.areillan.game.node.entity.player.link.quest.QuestRepository;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.game.system.command.CommandPlugin;
import org.areillan.game.system.command.CommandSet;
import org.areillan.game.system.communication.ClanRepository;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.map.path.Pathfinder;
import org.areillan.game.world.map.zone.impl.DonatorZone;
import org.areillan.game.world.repository.Repository;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;
import org.areillan.tools.StringUtils;
import org.areillan.tools.npc.TestStats;

/**
 * The commands available during beta stage.
 * @author Emperor
 */
public final class BetaCommandPlugin extends CommandPlugin {

	@Override
	public boolean parse(Player player, String name, String[] args) {
		int id, amount;
		Player p;
		final Player target;
		String n = "";
		switch (name) {
		/*
		case "char":
			CharacterDesign.open(player);
			break;
		case "teleto":
			if (args.length < 1) {
				player.debug("syntax error: name");
				return true;
			}
			for (int i = 1; i < args.length; i++) {
				if (i == 1) {
					n += args[i];
					continue;
				}
				n += " " + args[i];
			}
			target = Repository.getPlayer(n);
			if (target == null) {
				player.debug("syntax error: name");
				return true;
			}
			if (target.getAttribute("fc_wave") != null) {
				player.sendMessage("You cannot teleport to a player who is in the Fight Caves.");
				return true;
			}
			player.getProperties().setTeleportLocation(target.getLocation());
			break;
		
		case "pnpc":
			if (args.length < 2) {
				player.debug("syntax error: id");
				return true;
			}
			player.getAppearance().transformNPC(toInteger(args[1]));
			return true;
		case "anim":
		case "emote":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) delay");
				return true;
			}
			final Animation animation = new Animation(Integer.parseInt(args[1]), args.length > 2 ? Integer.parseInt(args[2]) : 0);
			player.animate(animation);
			return true;
		case "resetquest":
		case "reset_quest":
			if (args.length < 2) {
				player.debug("syntax error: name");
				return true;
			}
			name = "";
			for (int i = 1; i < args.length; i++) {
				name += (i == 1 ? "" : " ") + args[i];
			}
			name = StringUtils.formatDisplayName(name);
			if (player.getQuestRepository().getQuest(name) == null) {
				player.debug("err or: invalid quest - " + name);
				return true;
			}
			player.getQuestRepository().getQuest(name).setStage(player, 0);
			player.getQuestRepository().syncronizeTab(player);
			return true;
		case "allquest":
			for (Quest quest : QuestRepository.getQuests().values()) {
				quest.finish(player);
			}
			return true;
		case "setquest":
		case "setoquest":
			if (args.length < 2) {
				player.debug("syntax error: name");
				return true;
			}
			Player m = name.equals("setoquest") ? getTarget(args[args.length - 1]) : player;
			if (m == null || !m.isActive()) {
				player.sendMessage("Error! " + args[3] + " in invalid.");
				return true;
			}
			name = "";
			for (int i = 1; i < args.length - 1; i++) {
				name += (i == 1 ? "" : " ") + args[i];
			}
			Quest quest = null;
			for (Quest q : QuestRepository.getQuests().values()) {
				if (q.getName().toLowerCase().equals(name.toLowerCase())) {
					quest = q;
					break;
				}
			}
			if (quest == null) {
				player.debug("error: invalid quest - " + name);
				return true;
			}
			int stage = toInteger(args[args.length - 1]);
			quest.setStage(player, stage);
			m.getPacketDispatch().sendMessage("quest=" + name + ", new stage=" + stage);
			m.getQuestRepository().syncronizeTab(player);
			break;
		case "empty":
			player.getInventory().clear();
			return true;
		case "pickupn":
			if (args.length < 2) {
				player.debug("syntax error: item-name (optional) amount");
				return true;
			}
			String params = "";
			for (int i = 1; i < args.length; i++) {
				params += i == args.length - 1 ? args[i] : args[i] + " ";
			}
			for (int i = 0; i < ItemDefinition.getDefinitions().size(); i++) {
				ItemDefinition def1 = ItemDefinition.forId(i);
				if (def1 != null && def1.getName().equalsIgnoreCase(params.toLowerCase())) {
					player.getInventory().add(new Item(i, 1));
					player.getPacketDispatch().sendMessage("[item=" + def1.getId() + ", " + def1.getName() + "].");
					break;
				}
			}
			return true;
		case "pos":
			Location loc = player.getLocation();
			player.getPacketDispatch().sendMessage("X = " + loc.getX() + " Y = " + loc.getY());
			return true;
		case "pickup":
			amount = args.length > 2 ? toInteger(args[2]) : 1;
			if (args[1].contains("-")) {
				String[] data = args[1].split("-");
				for (id = toInteger(data[0]); id < toInteger(data[1]); id++) {
					if (id > Cache.getItemDefinitionsSize()) {
						return true;
					}
					Item item = new Item(id, amount);
					int max = player.getInventory().getMaximumAdd(item);
					if (amount > max) {
						amount = max;
					}
					item.setAmount(amount);
					player.getInventory().add(item);
				}
				return true;
			}
			id = args.length > 1 ? toInteger(args[1]) : 0;
			if (id > Cache.getItemDefinitionsSize()) {
				return true;
			}
			Item item = new Item(id, amount);
			int max = player.getInventory().getMaximumAdd(item);
			if (amount > max) {
				amount = max;
			}
			item.setAmount(amount);
			player.getInventory().add(item);
			return true;
		case "zulrah":
			ActivityManager.start(player, "zulrah cutscene", false);
			return true;
		//case "master":
		case "max":
			if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
				if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
					player.getPacketDispatch().sendMessage("You can't do that right now.");
					return true;
				}
			}
			for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
				player.getSkills().setLevel(i, 99);
				player.getSkills().setStaticLevel(i, 99);
			}
			player.getSkills().updateCombatLevel();
			player.getAppearance().sync();
			return true;
		case "runes":
			for (int i = 554; i < 567; i++) {
				player.getInventory().add(new Item(i, 50000));
			}
			player.getInventory().add(new Item(9075, 50000));
			return true;
		case "copy":
			Player target1 = Repository.getPlayer(args[1]);
			if (target1 != null) {
				player.getInventory().copy(target1.getInventory());
				player.getInventory().refresh();
				player.getSkills().copy(target1.getSkills());
				player.getSkills().configure();
				player.getEquipment().copy(target1.getEquipment());
				player.getEquipment().refresh();
				player.getAppearance().sync();
			}
			return true;
		case "to":
			if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
				if (player.inCombat() || player.getLocks().isTeleportLocked()) {
					player.getPacketDispatch().sendMessage("You can't teleport right now.");
					return true;
				}
			}
			Location destination = null;
			String place = getArgumentLine(args);
			for (Object[] data : ServerConstants.TELEPORT_DESTINATIONS) {
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
		case "teleports":
		case "destinations":
			player.getInterfaceManager().close();
			player.getPacketDispatch().sendString("<u>Teleport destinations</u>", 239, 1);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < ServerConstants.TELEPORT_DESTINATIONS.length; i++) {
				sb.append(ServerConstants.TELEPORT_DESTINATIONS[i][1]);
				if (i != ServerConstants.TELEPORT_DESTINATIONS.length - 1) {
					sb.append(", ");
				}
			}
			player.getPacketDispatch().sendString("<br>" + sb.toString(), 239, 2);
			player.getPacketDispatch().sendString("", 239, 3);
			player.getPacketDispatch().sendString("", 239, 4);
			player.getPacketDispatch().sendString("", 239, 5);
			player.getInterfaceManager().openComponent(239);
			return true;
		case "maxmag":
			TestStats.setMaxedMagicAcc(player);
			return true;
		case "maxstr":
			TestStats.setMaxedMeleeStr(player);
			return true;
		case "tele":
			if (args.length == 2 && args[1].contains(",")) {
				args = args[1].split(",");
				int x = Integer.parseInt(args[1]) << 6 | Integer.parseInt(args[3]);
				int y = Integer.parseInt(args[2]) << 6 | Integer.parseInt(args[4]);
				int z = Integer.parseInt(args[0]);
				player.getProperties().setTeleportLocation(Location.create(x, y, z));
				return true;
			}
			if (args.length < 2) {
				player.debug("syntax error: x, y, (optional) z");
				return false;
			}
			player.getProperties().setTeleportLocation(Location.create(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args.length > 3 ? Integer.parseInt(args[3]) : 0));
			return true;
			*/
		}
		return false;
	}
	
	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.PLAYER, CommandSet.ADMINISTRATOR);
		return this;
	}
	
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


}