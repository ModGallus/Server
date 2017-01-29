package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Handles Saniboch's options.
 * @author Emperor
 */
public final class SanibochPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(1595).getConfigurations().put("option:pay", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(1595, null, 35);
		return false;
	}

}