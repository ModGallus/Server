package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * @author 'Vexia.
 */
public class ThessaliaPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(548, true, true, true);
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(548).getConfigurations().put("option:change-clothes", this);
		return this;
	}

}