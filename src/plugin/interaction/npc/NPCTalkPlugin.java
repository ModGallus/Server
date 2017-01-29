package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Handles the NPC talk-to option.
 * @author Emperor
 */
public final class NPCTalkPlugin extends OptionHandler {

	@Override
	public Location getDestination(Node n, Node node) {
		NPC npc = (NPC) node;
		if (npc.getAttribute("facing_booth", false)) {
			int offsetX = npc.getDirection().getStepX() << 1;
			int offsetY = npc.getDirection().getStepY() << 1;
			return npc.getLocation().transform(offsetX, offsetY, 0);
		}
		return null;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final NPC npc = (NPC) node;
		if (!npc.getAttribute("facing_booth", false)) {
			npc.faceLocation(player.getLocation());
		}
		return player.getDialogueInterpreter().open(npc.getId(), npc);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("talk-to", this);
		return this;
	}
}