package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.global.action.PickupHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.GroundItem;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Represents the option handler used for ground items.
 * @author Vexia
 * @author Emperor
 */
public final class PickupPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("take", this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (player.getAttributes().containsKey("pickup"))
		    return false;	
		player.setAttribute("pickup", "true");
		boolean handleResult = PickupHandler.take(player, (GroundItem) node);
		player.removeAttribute("pickup");
		return handleResult;
	}

	@Override
	public Location getDestination(Node node, Node item) {
		return null;
	}

}