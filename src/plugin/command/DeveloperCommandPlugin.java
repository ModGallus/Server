package plugin.command;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.areillan.ServerConstants;
import org.areillan.cache.Cache;
import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.container.impl.EquipmentContainer;
import org.areillan.game.content.activity.ActivityManager;
import org.areillan.game.content.eco.ge.GrandExchangeDatabase;
import org.areillan.game.content.eco.ge.GrandExchangeEntry;
import org.areillan.game.content.eco.ge.ResourceManager;
import org.areillan.game.content.global.tutorial.CharacterDesign;
import org.areillan.game.content.global.tutorial.TutorialSession;
import org.areillan.game.content.holiday.HolidayItem;
import org.areillan.game.content.holiday.ItemLimitation;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.content.skill.free.smithing.smelting.Bar;
import org.areillan.game.content.skill.member.construction.HouseLocation;
import org.areillan.game.content.skill.member.farming.wrapper.PatchWrapper;
import org.areillan.game.content.skill.member.summoning.pet.Pets;
import org.areillan.game.node.entity.combat.DeathTask;
import org.areillan.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.areillan.game.node.entity.impl.Projectile;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPBuilder;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.node.entity.player.info.login.PlayerParser;
import org.areillan.game.node.entity.player.link.HintIconManager;
import org.areillan.game.node.entity.player.link.IronmanMode;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.entity.player.link.appearance.Gender;
import org.areillan.game.node.entity.player.link.audio.Audio;
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
import org.areillan.game.system.mysql.impl.ItemConfigSQLHandler;
import org.areillan.game.system.script.ScriptManager;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.Region;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.map.RegionPlane;
import org.areillan.game.world.map.build.DynamicRegion;
import org.areillan.game.world.map.path.Pathfinder;
import org.areillan.game.world.map.zone.RegionZone;
import org.areillan.game.world.map.zone.impl.DonatorZone;
import org.areillan.game.world.repository.Repository;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.gui.AriosFrame;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.ContainerContext;
import org.areillan.net.packet.context.InterfaceContext;
import org.areillan.net.packet.out.ContainerPacket;
import org.areillan.net.packet.out.Interface;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

import plugin.activity.barrows.RareReward;
import plugin.skill.herblore.PotionDecantingPlugin;

/**
 * Handles the developer commands.
 * @author Vexia
 * 
 */
public final class DeveloperCommandPlugin extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.DEVELOPER);
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean parse(final Player player, String name, String[] args) {
		int id, amount;
		Player p;
		final Player target;
		String n = "";
		Location location = null;
		GameObject object = null;
		Player o = null;
		switch (name) {
		case "restart":
			player.getBank().clear();
			return true;
		case "unnull":
			Player target4 = Repository.getPlayer(args[1]);
			
			target4.removeAttribute("tut-island");
			target4.getConfigManager().set(1021, 0);
			MSPacketRepository.sendInfoUpdate(target4);
			TutorialSession.getExtension(target4).setStage(72);
			target4.getInterfaceManager().closeOverlay();
			target4.removeAttribute("tut-island:hi_slot");

			target4.getPacketDispatch().sendMessage("Welcome to " + GameWorld.getName() + ".");
			target4.getPacketDispatch().sendMessages("<img=16><col=6600CC>As a new player, you are receiving boosted combat skill experience.</col>");
			Repository.sendNews("<col=ff0000>Everyone Welcome "+target4.getUsername()+" to Aincrad!");
			
			return true;
			
			
		case "news":
			int nitem = Integer.parseInt(args[1]);
			ItemDefinition newsItem = ItemDefinition.forId(nitem);
			Repository.sendNews(player.getUsername() + " has just received: 1 x " + newsItem.getName() + ".");
			return true;
			
		case "slo"://
			try {
				String targ = "";
				for (int i = 3; i < args.length; i++)
					targ += args[i] + ((i == args.length - 1) ? "" : " ");
				target = Repository.getPlayer(targ);
				if (target == null)
					return false;
				target.getSkills().setStaticLevel(Integer.valueOf(args[1]), Integer.valueOf(args[2]));
				player.getPacketDispatch().sendMessage("You have set " + target.getUsername() + "'s " + Skills.SKILL_NAME[Integer.valueOf(args[1])] + " to level " + args[2] + ".");
			} catch(Exception e) {
				player.getPacketDispatch().sendMessage("Failed.");
			}
			return true;
		case "kill":
			Player target3 = Repository.getPlayer(args[1]);
			DeathTask.startDeath(target3, target3);
			player.getPacketDispatch().sendMessage("You've killed " + target3 +" instantly.");
			return true;
		case "kick":
			Player target1 = Repository.getPlayer(args[1]);
			if (WorldCommunicator.isEnabled()) {
				MSPacketRepository.sendPunishment(player, args[1], 6, 0l);
			} else if (target1 != null) {
				target1.getPacketDispatch().sendLogout();
				target1.getSession().disconnect();
				target1.clear(true);
				player.getPacketDispatch().sendMessage("Kicked player " + args[1] + " from this world.");
			} else {
				player.getPacketDispatch().sendMessage("Player " + args[1] + " was already inactive.");
			}
			return true;
		case "god":
			player.setAttribute("godMode", !player.getAttribute("godMode", false));
			player.sendMessage("God mode=" + player.getAttribute("godMode", false));
			return true;
		case "getinfo":
			printInfo(player, args[1]);
			return true;
		case "unban":
			unpunish(player, args[1], 1);
			return true;
		case "unmute":
			unpunish(player, args[1], 0);
			return true;
		case "mute":
		case "permmute":
			punish(player, args[1], args, 0);
			return true;
		case "ban":
		case "permban":
			punish(player, args[1], args, 1);
			return true;
		case "ipban":
			punish(player, args[1], args, 2);
			return true;
		case "macban":
			punish(player, args[1], args, 3);
			return true;
		case "uidban":
		case "mskban":
			punish(player, args[1], args, 4);
			return true;
		case "sysban":
			punish(player, args[1], args, 5);
			return true;
		case "unccban":
			ClanRepository clan = ClanRepository.get(GameWorld.getSettings().getName());
			if (clan == null) {
				return true;
			}
			if (!clan.isBanned(args[1])) {
				player.sendMessage("The player is not banned.");
				return true;
			}
			clan.getBanned().remove(args[1]);
			player.sendMessage("Unbanned the player " + args[1] + " from the cc.");
			return true;
		case "checkbank":
			checkBank(player, args);
			return true;
		case "checkinvy":
			checkInvy(player, args);
			return true;
		case "players":
			if ((player.getInterfaceManager().isOpened() && player.getInterfaceManager().getOpened().getId() != 275) || player.getLocks().isMovementLocked() || player.getLocks().isTeleportLocked()) {
				player.sendMessage("Please finish what you're doing first.");
				return true;
			}
			player.getInterfaceManager().open(new Component(275));
			/*for (int i = 0; i < 257; i++) {
				player.getPacketDispatch().sendString("", 275, i);
			}*/
			String red = "<col=8A0808>";
			player.getPacketDispatch().sendString("<col=8A0808>" + "Players" + "</col>", 275, 2);
			//StringBuilder builder = new StringBuilder("<br>");
			int count = 0;
			for (Player p1 : Repository.getPlayers()) {
				if (count > 310) {
					//builder.append("<br>Max amount we can show on this interface.");
					player.getPacketDispatch().sendString("<br>Max amount we can show on this interface.", 275, count+11);
					break;
				}
				if (p1 == null || (p1.isAdmin() && !GameWorld.getSettings().isDevMode()) && !player.isAdmin() || p1.isArtificial()) {
					continue;
				}
				player.getPacketDispatch().sendString(red + "<img=" + (Rights.getChatIcon(p1) - 1) + ">" + p1.getUsername() + " [ip=" + p1.getDetails().getIpAddress() + ", name=" + p1.getDetails().getCompName() + "]", 275, count+11);
				//builder.append(red + "<img=" + (Rights.getChatIcon(p1) - 1) + ">" + p1.getUsername() + " [ip=" + p1.getDetails().getIpAddress() + ", name=" + p1.getDetails().getCompName() + "]<br><br>");
				count++;
			}
			// clear all that was not set.
			for(int i2=count+11; i2 < 311; i2++) {
				player.getPacketDispatch().sendString("", 275, i2);
			}
			//player.getPacketDispatch().sendString(builder.toString(), 275, 45);
			return true;
		case "telecs": // Client ctrl+shift teleport
			try {
				Location loc = player.getPlayerFlags().getLastSceneGraph();
				int x = ((loc.getRegionX() - 6) << 3) + toInteger(args[1]);
				int y = ((loc.getRegionY() - 6) << 3) + toInteger(args[2]);
				player.getPulseManager().clear();
				player.getProperties().setTeleportLocation(Location.create(x, y, player.getLocation().getZ()));
			} catch (Throwable t) {
				// region is changing
				t.printStackTrace();
			}
			return true;
		case "teler": // Teleports to the center of the region.
			int regionId = Integer.parseInt(args[1]);
			int x = 32;
			int y = 32;
			if (args.length > 3) {
				x = toInteger(args[2]);
				y = toInteger(args[3]);
			}
			player.getProperties().setTeleportLocation(Location.create(((regionId >> 8) << 6) + x, ((regionId & 0xFF) << 6) + y, 0));
			player.debug("Current location=" + player.getProperties().getTeleportLocation());
			return true;
		case "maxkc":
			for (int i = 0; i < 6; i++) {
				player.getSavedData().getActivityData().getBarrowBrothers()[i] = true;
			}
			String[] names = new String[] { "Ahrim", "Dharok", "Guthan", "Karil", "Torag", "Verac" };
			player.getSavedData().getActivityData().setBarrowKills(500);
			player.getPacketDispatch().sendMessage("Flagged all barrow brothers killed and 50 catacomb kills, current entrance: " + names[player.getSavedData().getActivityData().getBarrowTunnelIndex()] + ".");
			return true;
		case "1hko":
			player.setAttribute("1hko", !player.getAttribute("1hko", false));
			player.getPacketDispatch().sendMessage("1-hit KO mode " + (player.getAttribute("1hko", false) ? "on." : "off."));
			return true;
		case "sync":
		case "visual":
			if (args.length < 3) {
				player.debug("syntax error: anim_id gfx_id (optional) height");
				return true;
			}
			int animId = toInteger(args[1]);
			int gfxId = toInteger(args[2]);
			int height = args.length > 3 ? toInteger(args[3]) : 0;
			player.visualize(Animation.create(animId), new Graphics(gfxId, height));
			return true;
		case "pos_graphic":
		case "position_gfx":
		case "pos_gfx":
		case "lgfx":
			if (args.length < 2) {
				player.debug("syntax error: id x y (optional) height delay");
				return true;
			}
			location = Location.create(Integer.parseInt(args[2]), Integer.parseInt(args[3]), args.length > 4 ? Integer.parseInt(args[4]) : 0);
			player.getPacketDispatch().sendPositionedGraphic(Integer.parseInt(args[1]), args.length > 5 ? Integer.parseInt(args[5]) : 0, args.length > 6 ? Integer.parseInt(args[6]) : 0, location);
			break;
		case "inter":
		case "component":
		case "interface":
			if (args.length < 2) {
				player.debug("syntax error: interface-id");
				return true;
			}
			int componentId = toInteger(args[1]);
			if (componentId < 0 || componentId > Cache.getInterfaceDefinitionsSize()) {
				player.debug("Invalid component id [id=" + componentId + ", max=" + Cache.getInterfaceDefinitionsSize() + "].");
				return true;
			}
			player.getInterfaceManager().openComponent(componentId);
			return true;
		case "bank":
			if (args.length < 2) {
			player.getBank().open();
			}
			return true;
		case "hit":
			player.getImpactHandler().manualHit(player, toInteger(args[1]), HitsplatType.NORMAL);
			return true;
		case "sound":
			player.getAudioManager().send(new Audio(Integer.parseInt(args[1]), 10, 1));
			return true;
		case "noclip":
			player.setAttribute("no_clip", !player.getAttribute("no_clip", false));
			return true;
		case "removenpc":
			player.setAttribute("removenpc", !player.getAttribute("removenpc", false));
			player.debug("You have set remove npc value to " + player.getAttribute("removenpc", false) + ".");
			return true;
		case "npc":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) direction");
				return true;
			}
			NPC npc = NPC.create(toInteger(args[1]), player.getLocation());
			npc.setAttribute("spawned:npc", true);
			npc.setRespawn(false);
			npc.setDirection(player.getDirection());
			npc.init();
			npc.setWalks(args.length > 2 ? true : false);
			String npcString = "{" + npc.getLocation().getX() + "," + npc.getLocation().getY() + "," + npc.getLocation().getZ() + "," + (npc.isWalks() ? "1" : "0") + "," + npc.getDirection().ordinal() + "}";
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(new StringSelection(npcString), null);
			System.out.println(npcString);
			return true;
		case "object":
		case "obj":
			if (args.length < 2) {
				player.debug("syntax error: id (optional) type rotation or rotation");
				return true;
			}
			object = args.length > 3 ? new GameObject(toInteger(args[1]), player.getLocation(), toInteger(args[2]), toInteger(args[3])) : args.length == 3 ? new GameObject(toInteger(args[1]), player.getLocation(), toInteger(args[2])) : new GameObject(toInteger(args[1]), player.getLocation());
			ObjectBuilder.add(object);
			SystemLogger.log("object = " + object);
			return true;
		case "update":
			if (args.length > 1) {
				SystemManager.getUpdater().setCountdown(Integer.parseInt(args[1]));
			}
			SystemManager.flag(SystemState.UPDATING);
			return true;
		case "cancel_update":
		case "cancelupdate":
		case "cancel":
			SystemManager.getUpdater().cancel();
			return true;
		case "dz":
			player.teleport(new Location(159, 52, 0));
			return true;
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
		case "empty":
			player.getInventory().clear();
			return true;
		case "itemn":
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
		case "unmax":
			if (player.getDetails().getRights() != Rights.ADMINISTRATOR) {
				if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness()) {
					player.getPacketDispatch().sendMessage("You can't do that right now.");
					return true;
				}
			}
			for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
				player.getSkills().setLevel(i, 1);
				player.getSkills().setStaticLevel(i, 1);
			}
			player.getSkills().setLevel(3, 10);
			player.getSkills().setStaticLevel(3, 10);
			player.getSkills().updateCombatLevel();
			player.getAppearance().sync();
			return true;
		case "master":
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

		case "reward":
			RareReward.reward(player);
			return true;
		case "copy":
			Player target2 = Repository.getPlayer(args[1]);
			if (target2 != null) {
				player.getInventory().copy(target2.getInventory());
				player.getInventory().refresh();
				player.getSkills().copy(target2.getSkills());
				player.getSkills().configure();
				player.getEquipment().copy(target2.getEquipment());
				player.getEquipment().refresh();
				player.getAppearance().sync();
			}
			return true;
		case "pvplegion":
			int size = args.length < 2 ? 10 : Integer.parseInt(args[1]);
			Player last = player;
			List<AIPlayer> legion = player.getAttribute("aip_legion");
			if (PVPAIPActions.pvp_players == null) {
				player.setAttribute("aip_legion", PVPAIPActions.pvp_players = new ArrayList<>());
			}
			if (legion == null) {
				player.setAttribute("aip_legion", legion = new ArrayList<>());
			}
			for (int i = 0; i < size; i++) {
				String aipName = PVPAIPBuilderUtils.names[i];
				final AIPlayer aip = AIPBuilder.create(aipName, generateLocation(player));
				aip.setControler(player);
				aip.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);
				Repository.getPlayers().add(aip);
				aip.init();
				PVPAIPBuilderUtils.generateClass(aip);
				
				if (PVPAIPActions.pvp_players.isEmpty()) {
					aip.setAttribute("aip_legion", PVPAIPActions.pvp_players);
				}
				PVPAIPActions.pvp_players.add(aip);
				if (legion.isEmpty()) {
					aip.setAttribute("aip_legion", legion);
				}
				legion.add(aip);
				last = aip;
			}
			return true;
			
		case "pvpfight":
			PVPAIPActions.syncBotThread(player);
			return true;
		case "invisible":
		case "invis":
		case "seti":
			player.setInvisible(!player.isInvisible());
			player.sendMessage("You are now "+(player.isInvisible() ? "invisible" : "rendering")+" for other players.");
			return true;
		case "demonic":
			player.teleport(new Location(2697, 9212, 1));
			return true;

		case "pos":
			Location loc = player.getLocation();
			player.getPacketDispatch().sendMessage("X = " + loc.getX() + " Y = " + loc.getY());
			return true;
		case "heal":
		case "hp":
		case "life":
			player.getSettings().setSpecialEnergy(100000);
			player.getSettings().updateRunEnergy(-100);
			player.getSkills().setLifepointsIncrease(0);
			player.getSkills().rechargePrayerPoints();
			player.getSkills().heal(10000);
			player.getSkills().setLevel(Skills.SUMMONING, player.getSkills().getStaticLevel(Skills.SUMMONING));
			player.getStateManager().remove(EntityState.TELEBLOCK);
			if (player.getFamiliarManager().hasFamiliar()) {
				player.getFamiliarManager().getFamiliar().updateSpecialPoints(-200);
			}
			return true;
		case "tele":
			if (args.length == 2 && args[1].contains(",")) {
				args = args[1].split(",");
				int x1 = Integer.parseInt(args[1]) << 6 | Integer.parseInt(args[3]);
				int y1 = Integer.parseInt(args[2]) << 6 | Integer.parseInt(args[4]);
				int z = Integer.parseInt(args[0]);
				player.getProperties().setTeleportLocation(Location.create(x1, y1, z));
				return true;
			}
			if (args.length < 2) {
				player.debug("syntax error: x, y, (optional) z");
				return false;
			}
			player.getProperties().setTeleportLocation(Location.create(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args.length > 3 ? Integer.parseInt(args[3]) : 0));
			return true;
			
		case "item":
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
		case "zul":
			ActivityManager.start(player, "zulrah cutscene", false);
			return true;
		}
		return false;
	}

	/**
	 * Adds an item to a players item.
	 * @param player the player.
	 * @param args the args.
	 */
	
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
		//player.getInterfaceManager().open(new Component(12));
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
	
	private void addItem(Player player, String[] args) {
		Player t = Repository.getPlayer(args[1]);
		if (t == null) {
			return;
		}
		int id = toInteger(args[2]);
		int amount = toInteger(args[3]);
		Item item = new Item(id, amount);
		t.getInventory().add(item);
		player.getPacketDispatch().sendMessage("You just gave " + t.getUsername() + " the item - " + item);
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
	
	/**
	 * Deletes an item from a players item.
	 * @param player the player.
	 * @param args the args.
	 */
	private void deleteItem(Player player, String[] args) {
		Player t = Repository.getPlayer(args[1]);
		if (t == null) {
			return;
		}
		int id = toInteger(args[2]);
		int amount = toInteger(args[3]);
		Item item = new Item(id, amount);
		if (args[0].equals("deleteitemb")) {
			t.getBank().remove(item);
		} else {
			t.getInventory().remove(item);
		}
		player.getPacketDispatch().sendMessage("You just removed" + t.getUsername() + " the item - " + item);
	}

}
