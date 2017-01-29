package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.global.travel.glider.GliderPulse;
import org.areillan.game.content.global.travel.glider.Gliders;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the glider interface component.
 * @author Emperor
 * @author 'Vexia
 * @version 1.0
 */
public final class GliderInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(138, this);
		return this;
	}

	@Override
	public boolean handle(final Player player, Component component, int opcode, int button, int slot, int itemId) {
		final Gliders glider = Gliders.forId(button);
		if (glider == null) {
			return true;
		}
		player.getPulseManager().run(new GliderPulse(1, player, glider));
		return true;
	}
}
