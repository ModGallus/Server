package plugin.activity.clanwars;

import org.areillan.game.interaction.Option;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneBorders;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.game.world.map.zone.ZoneRestriction;
import org.areillan.game.world.map.zone.impl.WildernessZone;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles the clan wars challenge room.
 * @author Emperor
 */
public final class ClanWarsChallengeRoom extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code ClanWarsChallengeRoom} {@code Object}.
	 */
	public ClanWarsChallengeRoom() {
		super("clan wars cr", true, ZoneRestriction.RANDOM_EVENTS);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3264, 3672, 3279, 3695));
	}

	@Override
	public boolean enter(Entity e) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.getSkullManager().setWildernessDisabled(true);
			p.getSkullManager().setWilderness(false);
			p.getInterfaceManager().closeOverlay();
			p.getInteraction().remove(Option._P_ASSIST);
			p.getInteraction().set(CWChallengeOption.OPTION);
		}
		return super.enter(e);
	}

	@Override
	public boolean leave(final Entity e, final boolean logout) {
		if (e instanceof Player) {
			Player p = (Player) e;
			p.getSkullManager().setWildernessDisabled(false);
			p.getInteraction().remove(CWChallengeOption.OPTION);
			p.getInteraction().set(Option._P_ASSIST);
			if (WildernessZone.isInZone(e)) {
				WildernessZone.show(p);
				p.getSkullManager().setWilderness(true);
			}
		}
		return super.leave(e, logout);
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (target instanceof GameObject) {
			GameObject object = (GameObject) target;
			Player player = (Player) e;
			if (object.getId() == 28213) {
				if (player.getCommunication().getClan() == null) {
					player.getPacketDispatch().sendMessage("You have to be in a clan to enter this portal.");
				} else if (player.getCommunication().getClan().isDefault()) {
					player.sendMessage("You can't use the main clan chat for this.");
					return true;
				} else if (player.getCommunication().getClan().getClanWar() == null) {
					player.getPacketDispatch().sendMessage("Your clan has to be in a war.");
				} else {
					player.getCommunication().getClan().getClanWar().fireEvent("join", player);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new CWChallengeOption());
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

}