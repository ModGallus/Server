package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.global.action.EquipHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used for the quest cape and hood item.
 * @author 'Vexia
 */
public final class QuestCapePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(9813).getConfigurations().put("option:wear", this);
		ItemDefinition.forId(9814).getConfigurations().put("option:wear", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getQuestRepository().hasCompletedAll()) {
			player.getPacketDispatch().sendMessage("You cannot wear this " + node.getName().toLowerCase() + " yet.");
			return true;
		}
		return EquipHandler.SINGLETON.handle(player, node, option);
	}

	@Override
	public boolean isWalk() {
		return false;
	}
}
