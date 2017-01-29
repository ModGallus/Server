package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.global.LightSource;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.map.zone.impl.DarkZone;
import org.areillan.plugin.Plugin;

/**
 * Represents the extinguish light plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class ExtinguishLightPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("extinguish", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final LightSource source = LightSource.forProductId(((Item) node).getId());
		if (source == null) {
			System.err.println("Extinguish Light Plugin source not found - " + ((Item) node).getId() + ".");
			return true;
		}
		player.getInventory().replace(source.getRaw(), ((Item) node).getSlot());
		DarkZone.checkDarkArea(player);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
