package plugin.dialogue;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle dairy churning executing.
 * @author 'Vexia
 * @version 1.0
 */
public final class DairyChurnOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(10093).getConfigurations().put("option:churn", this);
		ObjectDefinition.forId(10094).getConfigurations().put("option:churn", this);
		ObjectDefinition.forId(25720).getConfigurations().put("option:churn", this);
		ObjectDefinition.forId(34800).getConfigurations().put("option:churn", this);
		ObjectDefinition.forId(35931).getConfigurations().put("option:churn", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getInventory().contains(1927, 1) && !player.getInventory().contains(2130, 1) && !player.getInventory().contains(6697, 1)) {
			player.getPacketDispatch().sendMessage("You need some milk, cream or butter to use in the churn.");
			return true;
		}
		player.getDialogueInterpreter().open(984374);
		return true;
	}

}
