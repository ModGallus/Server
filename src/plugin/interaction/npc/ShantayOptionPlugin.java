package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Represents the option plguin.
 * @author 'Vexia
 */
public class ShantayOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(836).getConfigurations().put("option:buy-pass", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		DialogueInterpreter interpreter = player.getDialogueInterpreter();
		if (player.getInventory().remove(new Item(995, 5))) {
			player.getInventory().add(new Item(1854));
			interpreter.sendItemMessage(1854, "You purchase a Shantay Pass.");
		} else {
			interpreter.sendDialogues(player, null, "Sorry, I don't seem to have enough money.");
		}
		return true;
	}

}
