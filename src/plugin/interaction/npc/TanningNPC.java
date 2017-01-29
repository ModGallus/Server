package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.content.skill.free.crafting.TanningProduct;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * @author 'Vexia
 */
public class TanningNPC extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(1041).getConfigurations().put("option:trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		TanningProduct.open(player, ((NPC) node).getId());
		return true;
	}

}
