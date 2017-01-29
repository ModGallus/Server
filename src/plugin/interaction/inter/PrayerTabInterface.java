package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.prayer.PrayerType;
import org.areillan.plugin.Plugin;

/**
 * Represents the prayer interface.
 * @author 'Vexia
 */
public final class PrayerTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(271, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		final PrayerType type = PrayerType.get(button);
		if (type == null) {
			return true;
		}
		player.getPrayer().toggle(type);
		return true;
	}
}
