package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.global.travel.canoe.Canoe;
import org.areillan.game.content.global.travel.canoe.CanoeExtension;
import org.areillan.game.content.global.travel.canoe.CanoeStation;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the canoe interface plugins.
 * @author 'Vexia
 * @version 1.0
 */
public final class CanoeInterfacePlugin extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(52, this);
		ComponentDefinition.put(53, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		final CanoeExtension extension = CanoeExtension.extension(player);
		switch (component.getId()) {
		case 52:
			final Canoe canoe = Canoe.forChild(button);
			if (canoe == null) {
				return true;
			}
			extension.craft(canoe);
			break;
		case 53:
			final CanoeStation station = CanoeStation.forButton(button);
			if (station == null) {
				return true;
			}
			if (extension.getStage() < 3) {
				return true;
			}
			extension.travel(station);
			break;
		}
		return true;
	}

}
