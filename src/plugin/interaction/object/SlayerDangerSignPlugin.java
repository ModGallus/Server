package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class SlayerDangerSignPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(5127).getConfigurations().put("option:read", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().sendDialogue("<col=FFF0000>WARNING!", "</col>This area contains very dangerous creatures!", "Do not pass unless properly prepared!");
		return true;
	}

}
