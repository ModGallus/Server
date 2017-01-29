package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.global.action.DigSpadeHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle the dig option on a spade.
 * @author 'Vexia
 * @author Emperor
 */
public class SpadeDigOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(952).getConfigurations().put("option:dig", this);
		return null;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (!DigSpadeHandler.dig(player)) {
			player.sendMessage("You dig but find nothing.");
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

}