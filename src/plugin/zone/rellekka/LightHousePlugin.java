package plugin.zone.rellekka;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.DoorActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Handles the light house plugin.
 * @author Vexia
 */
public class LightHousePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(4577).getConfigurations().put("option:walk-through", this);
		ObjectDefinition.forId(4383).getConfigurations().put("option:climb", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 4577:
			DoorActionHandler.handleDoor(player, node.asObject());
			return true;
		case 4383:
			return false;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n.getName().equals("Door")) {
			return DoorActionHandler.getDestination((Entity) node, n.asObject());
		}
		return null;
	}
}
