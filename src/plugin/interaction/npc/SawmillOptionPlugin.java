package plugin.interaction.npc;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin to handle the options for saw mill man.
 * @author 'Vexia
 * @date Oct 8, 2013
 */
public class SawmillOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(4250).getConfigurations().put("option:buy-plank", this);
		NPCDefinition.forId(4250).getConfigurations().put("option:talk-to", this);
		NPCDefinition.forId(4250).getConfigurations().put("option:trade", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "buy-plank":
			player.getInterfaceManager().open(new Component(403));
			return true;
		case "talk-to":
			player.getDialogueInterpreter().open(4250, node);
			return true;
		case "trade":
			node.asNpc().openShop(player);
			return true;
		}
		return true;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return Location.create(3302, 3491, 0);
	}
}
