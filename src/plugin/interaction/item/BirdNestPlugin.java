package plugin.interaction.item;

import org.areillan.game.content.global.BirdNest;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Handles the searching of a bird nest item.
 * @author Vexia
 */
public final class BirdNestPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (BirdNest nest : BirdNest.values()) {
			nest.getNest().getDefinition().getConfigurations().put("option:search", this);
		}
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Item item = (Item) node;
		BirdNest.forNest(item).search(player, item);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
