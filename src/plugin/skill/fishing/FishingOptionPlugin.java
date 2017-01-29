package plugin.skill.fishing;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.content.skill.free.fishing.FishingOption;
import org.areillan.game.content.skill.free.fishing.FishingPulse;
import org.areillan.game.content.skill.free.fishing.FishingSpot;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to start fishing.
 * @author 'Vexia
 * @version 1.0
 */
public final class FishingOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("net", this);
		NPCDefinition.setOptionHandler("bait", this);
		NPCDefinition.setOptionHandler("lure", this);
		NPCDefinition.setOptionHandler("cage", this);
		NPCDefinition.setOptionHandler("harpoon", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		NPC npc = (NPC) node;
		FishingSpot spot = FishingSpot.forId(npc.getId());
		if (spot == null) {
			return false;
		}
		FishingOption opt = null;
		for (FishingOption o : spot.getOptions()) {
			if (o.getName().equals(option)) {
				opt = o;
				break;
			}
		}
		if (opt == null) {
			return false;
		}
		player.getPulseManager().run(new FishingPulse(player, npc, opt));
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return null;
	}
}
