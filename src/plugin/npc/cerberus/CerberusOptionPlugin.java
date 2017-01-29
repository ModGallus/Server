package plugin.npc.cerberus;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.content.skill.member.slayer.Tasks;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.plugin.Plugin;

/**
 * Handles the options related to Cerberus.
 * @author Vexia
 *
 */
public class CerberusOptionPlugin extends OptionHandler {
	
	/**
	 * The west cerberus zone.
	 */
	private static final CerberusZone WEST = new CerberusZone(4883, new CerberusNPC(8632, new Location(1240, 1251, 0)));
	
	/**
	 * The north cerberus zone.
	 */
	private static final CerberusZone NORTH = new CerberusZone(5140, new CerberusNPC(8632, new Location(1303, 1314, 0)));
	
	/**
	 * The east cerberus zone.
	 */
	private static final CerberusZone EAST = new CerberusZone(5395, new CerberusNPC(8632, new Location(1368, 1251, 0)));

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(42137).getConfigurations().put("opion:crawl", this);
		ObjectDefinition.forId(42137).getConfigurations().put("opion:crawl", this);
		ObjectDefinition.forId(42136).getConfigurations().put("opion:crawl", this);
		ObjectDefinition.forId(42138).getConfigurations().put("opion:crawl", this);
		ObjectDefinition.forId(42128).getConfigurations().put("option:turn", this);
		ObjectDefinition.forId(42128).getConfigurations().put("option:peek", this);
		ObjectDefinition.forId(42197).getConfigurations().put("option:exit", this);
		ObjectDefinition.forId(42111).getConfigurations().put("option:exit", this);
		ZoneBuilder.configure(WEST);
		ZoneBuilder.configure(NORTH);
		ZoneBuilder.configure(EAST);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		CerberusZone zone = null;
		switch (node.asObject().getId()) {
		case 42137:
		case 42138:
		case 42136:
			switch (option) {
			case "crawl":
				final Location loc = player.getLocation().withinDistance(Location.create(1310, 1237, 0)) ? Location.create(2873, 9847, 0) : Location.create(1310, 1237, 0);
				player.lock();
				player.getInterfaceManager().openOverlay(new Component(115));
				GameWorld.submit(new Pulse(1, player) {
					int count;
					@Override
					public boolean pulse() {
						switch (++count) {
						case 2:
							player.teleport(loc);
							break;
						case 4:
							player.getInterfaceManager().closeOverlay();
							return true;
						}
						return false;
					}
				});
				break;
			}
			break;
		case 42128:
			zone = getZone(node.getLocation());
			switch (option) {
			case "turn":
				if (!player.isAdmin() && (!player.getSlayer().hasTask() || (player.getSlayer().getTask() != Tasks.CERBERUS.getTask() || player.getSlayer().getTask() != Tasks.HELLHOUNDS.getTask()))) {
					player.getDialogueInterpreter().sendDialogues(8673, FacialExpression.NORMAL, "Only those who have been assigned to slayer Cerberus, or", "her lesser brethren, may face her in battle.");
					return true;
				}
				int regionId = zone.getRegionId();
				int x = 32, y = 32;
				Location loc = Location.create(((regionId >> 8) << 6) + x, ((regionId & 0xFF) << 6) + y, 0);
				player.teleport(loc.transform(-8, -22, 0));
				break;
			case "peek":
				int players = zone.getPlayers().size();
				String message = "There are no adventurers inside the cave.";
				if (players == 1) {
					message = "1 adventurer is inside the cave.";
				} else if (players> 1) {
					message = players + " adventurers inside the cave.";
				}
				player.getDialogueInterpreter().sendDialogues(8673, FacialExpression.NORMAL, message);
				break;
			}
			break;
		case 42197:
		case 4211:
			zone = getZone(node.getLocation());
			switch (option) {
			case "exit":
				if (zone == EAST) {
					player.teleport(Location.create(1333, 1252, 0));
				} else if (zone == NORTH) {
					player.teleport(Location.create(1310, 1274, 0));
				} else {
					player.teleport(Location.create(1289, 1252, 0));
				}
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * Gets a cerberus zone due to a location.
	 * @param location The location.
	 * @return The zone.
	 */
	private static CerberusZone getZone(Location location) {
		int x = location.getX();
		int y = location.getY();
		if (x == 1307 && y == 1269) {
			return NORTH;
		} else if (x == 1291 && y == 1254) {
			return WEST;
		} else if (x == 1328 && y == 1254) {
			return EAST;
		}
		return NORTH;
	}
}
