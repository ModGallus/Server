package plugin.interaction.player;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.game.interaction.Option;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the attack option plugin handler.
 * @author Emperor
 * @version 1.0
 */
public final class AttackOptionPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.attack(node);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		Option._P_ATTACK.setHandler(this);
		NPCDefinition.setOptionHandler("attack", this);
		return this;
	}

	@Override
	public boolean isDelayed(Player player) {
		return false;
	}

}
