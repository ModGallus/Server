package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used for the npcs in alkharid which can heal you.
 * @author 'Vexia
 * @version 1.0
 */
public final class AlkharidHealPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(962).getConfigurations().put("option:heal", this);
		NPCDefinition.forId(961).getConfigurations().put("option:heal", this);
		NPCDefinition.forId(960).getConfigurations().put("option:heal", this);
		NPCDefinition.forId(959).getConfigurations().put("option:heal", this);
		NPCDefinition.forId(958).getConfigurations().put("option:heal", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getDialogueInterpreter().open(((NPC) node).getId(), ((NPC) node));
		return true;
	}

}
