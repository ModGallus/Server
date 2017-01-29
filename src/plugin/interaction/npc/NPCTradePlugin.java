package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.content.skill.free.crafting.TanningProduct;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used for an npc with the trade option.
 * @author 'Vexia
 * @version 1.0
 */
public final class NPCTradePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final NPC npc = (NPC) node;
		if (npc.getId() == 2824) {
			TanningProduct.open(player, 2824);
			return true;
		}
		return node.asNpc().openShop(player);
	}

}
