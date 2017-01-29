package plugin.command;

import java.util.ArrayList;
import java.util.List;

import org.areillan.game.interaction.Interaction;
import org.areillan.game.interaction.Option;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPBuilder;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPActions;
import org.areillan.game.node.entity.player.ai.pvp.PVPAIPBuilderUtils;
import org.areillan.game.node.entity.player.ai.random.RandomAIPActions;
import org.areillan.game.node.entity.player.link.appearance.Gender;
import org.areillan.game.system.command.CommandPlugin;
import org.areillan.game.system.command.CommandSet;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.map.path.Pathfinder;
import org.areillan.game.world.repository.Repository;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * Handles the AIPlayer commands.
 * @author Emperor
 */
public final class AIPCommandPlugin extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.DEVELOPER);
		return this;
	}

	@Override
	public boolean parse(final Player player, String name, String[] args) {
		List<AIPlayer> legion = player.getAttribute("aip_legion");
		switch (name) {
		case "desaip":
			player.removeAttribute("aip_select");
			player.removeAttribute("aip_became");
			return true;
		case "sellegion":
			if (legion != null && !legion.isEmpty()) {
				player.setAttribute("aip_select", legion.get(0));
			}
			return true;
		case "regroup":
			Player last = player;
			if (legion != null && !legion.isEmpty()) {
				for (AIPlayer p : legion) {
					p.follow(last);
					last = p;
				}
			}
			player.removeAttribute("aip_select");
			return true;
		case "clearlegion":
			if (legion != null && !legion.isEmpty()) {
				for (AIPlayer p : legion) {
					//AIPlayer.deregister(p.getUid());
					p.clear();
				}
				legion.clear();
			}
			player.removeAttribute("aip_select");
			player.removeAttribute("aip_legion");
			return true;
		case "clearaips":
			for (Player p : Repository.getPlayers()) {
				if (p.isArtificial()) {
					p.clear();
				}
			}
			player.removeAttribute("aip_select");
			return true;
		case "aip":
			name = args.length < 2 ? player.getName() : args[1];
			AIPlayer p = AIPBuilder.copy(player, name, player.getLocation().transform(0, 1, 0));
			MSPacketRepository.sendInfoUpdate(p);
			p.getCommunication().setClan(player.getCommunication().getClan());
			if (p.getCommunication().getClan() != null) {
				p.getCommunication().getClan().enter(p);
			}
			MSPacketRepository.sendJoinClan(p, name);
			Repository.getPlayers().add(p);
			p.init();
			//Interaction.sendOption(player, 7, "Control");
			player.getInteraction().set(Option._P_AI_CONTROL);
			
			return true;
		case "legion":
			int size = args.length < 2 ? 10 : Integer.parseInt(args[1]);
			last = player;
			if (legion == null) {
				player.setAttribute("aip_legion", legion = new ArrayList<>());
			}
			//Interaction.sendOption(player, 7, "Control");
			player.getInteraction().set(Option._P_AI_CONTROL);
			boolean joinClan = player.getCommunication().getClan() != null && !player.getCommunication().getClan().isDefault();
			String message = null;//player.getName().equals("emperor") ? "The Dark Army marches again!" : null; // Add
			// your
			// own
			// message
			for (int i = 0; i < size; i++) {
				final AIPlayer aip = AIPBuilder.copy(player, last.getLocation().transform(0, 1, 0));
				Repository.getPlayers().add(aip);
				aip.init();
				if (legion.isEmpty()) {
					aip.setAttribute("aip_legion", legion);
				}
				legion.add(aip);
				final Player l = last;
				if (joinClan) {
					if (player.getCommunication().getClan().enter(aip)) {
						aip.getCommunication().setClan(player.getCommunication().getClan());
					}
					if (player.getCommunication().getClan().getClanWar() != null) {
						player.getCommunication().getClan().getClanWar().fireEvent("join", aip);
					}
				}
				GameWorld.submit(new Pulse(1) {
					@Override
					public boolean pulse() {
						aip.follow(l);
						return true;
					}
				});
				if (message != null) {
					aip.sendChat("The Dark Army marches again!");
				}
				last = aip;
			}
			return true;
		case "randomai":
			int size1 = args.length < 2 ? 10 : Integer.parseInt(args[1]);
			if (RandomAIPActions.random_players == null) {
				RandomAIPActions.random_players = new ArrayList<>();
			}
			for (int i = 0; i < size1; i++) {
				String aipName = PVPAIPBuilderUtils.names[i];
				final AIPlayer aip = AIPBuilder.create(aipName, generateLocation(player));
				aip.setControler(player);
				aip.getAppearance().setGender(RandomFunction.random(3) == 1 ? Gender.FEMALE : Gender.MALE);
				Repository.getPlayers().add(aip);
				aip.init();
				RandomAIPActions.giveStartingItems(aip);
				//PVPAIPBuilderUtils.generateClass(aip);
				RandomAIPActions.random_players.add(aip);
			}
			return true;
			
		case "randomaistart":
			RandomAIPActions.syncBotThread(player);
			return true;
		}
		return false;
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