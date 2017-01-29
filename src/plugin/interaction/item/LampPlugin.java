package plugin.interaction.item;

import org.areillan.game.component.Component;
import org.areillan.game.content.global.Lamps;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used for an experience lamp.
 * @author 'Vexia
 * @version 1.0
 */
public final class LampPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Lamps lamp : Lamps.values()) {
			lamp.getItem().getDefinition().getConfigurations().put("option:rub", this);
		}
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.setAttribute("lamp", node);
		player.getInterfaceManager().open(new Component(134));
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

}
