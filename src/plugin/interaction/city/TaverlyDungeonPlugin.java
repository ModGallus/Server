package plugin.interaction.city;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.content.global.action.DoorActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used for taverly dungeon.
 * @author 'Vexia
 * @version 1.0
 */
public final class TaverlyDungeonPlugin extends OptionHandler {

	/**
	 * The suits of armour.
	 */
	private static final NPC[] ARMOUR_SUITS = new NPC[2];

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2143).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(2144).getConfigurations().put("option:open", this);
		//1310 1237
		ObjectDefinition.forId(42336).getConfigurations().put("option:crawl", this);
		ObjectDefinition.forId(42337).getConfigurations().put("option:crawl", this);
		ObjectDefinition.forId(42338).getConfigurations().put("option:crawl", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		final int id = ((GameObject) node).getId();
		switch (id) {
		
		case 42336:
		case 42337:
		case 42338:
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
						player.unlock();
						return true;
					}
					return false;
				}
			});
			return true;
		
		case 2143:
		case 2144:
			if (player.getLocation().getX() < node.getLocation().getX() && !player.getAttribute("spawned_suits", false)) {
				boolean alive = true;
				for (int i = 0; i < ARMOUR_SUITS.length; i++) {
					NPC npc = ARMOUR_SUITS[i];
					if (npc == null || !npc.isActive()) {
						Location location = Location.create(2887, 9829 + (i * 3), 0);
						ARMOUR_SUITS[i] = npc = NPC.create(453, location);
						npc.init();
						npc.getProperties().getCombatPulse().attack(player);
						GameObject object = RegionManager.getObject(location);
						if (object != null) {
							ObjectBuilder.remove(object);
						}
						alive = false;
					}
				}
				if (!alive) {
					player.setAttribute("spawned_suits", true);
					player.getPacketDispatch().sendMessage("Suddenly the suit of armour comes to life!");
					return true;
				}
			}
			player.removeAttribute("spawned_suits");
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
			return true;
		}
		return false;
	}

}
