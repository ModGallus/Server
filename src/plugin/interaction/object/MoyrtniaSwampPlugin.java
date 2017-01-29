package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the mortynia swamp plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class MoyrtniaSwampPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(3506).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().sendDialogue("There's a message attached to this gate, it reads:~", "~ Mort Myre is a dangerous Ghast infested swamp. ~", "~ Do not enter if you value your life. ~", "~ All persons wishing to enter must see Drezel. ~");
		return true;
	}

}
