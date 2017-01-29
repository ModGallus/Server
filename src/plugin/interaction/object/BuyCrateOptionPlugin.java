package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.mysql.impl.ShopSQLHandler;
import org.areillan.plugin.Plugin;

/**
 * Represents the buy crate option plugin for the seers village city.
 * @author 'Vexia
 * @version 1.0
 */
public final class BuyCrateOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		ShopSQLHandler.openUid(player, 93);
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(6839).getConfigurations().put("option:buy", this);
		return this;
	}

}
