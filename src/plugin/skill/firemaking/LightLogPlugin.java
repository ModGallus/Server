package plugin.skill.firemaking;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.skill.free.firemaking.FireMakingPulse;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.GroundItem;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to light a log.
 * @author 'Vexia
 * @version 1.0
 */
public final class LightLogPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPulseManager().run(new FireMakingPulse(player, ((Item) node), ((GroundItem) node)));
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.setOptionHandler("light", this);
		return this;
	}

}
