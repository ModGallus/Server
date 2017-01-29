package plugin.interaction.city;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle nodes related to camelot.
 * @author 'Vexia
 * @version 1.0
 */
public final class CamelotNodePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(26017).getConfigurations().put("option:climb-down", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final int id = ((GameObject) node).getId();
		switch (id) {
		case 26017:
			player.getPacketDispatch().sendMessage("Court is not in session.");
			break;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		if (n instanceof GameObject) {
			int id = ((GameObject) n).getId();
			switch (id) {
			case 993:
				if (node.getLocation().getX() <= 2638) {
					return Location.create(2637, 3350, 0);
				} else {
					return Location.create(2640, 3350, 0);
				}
			}
		}
		return null;
	}

}
