package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.global.tutorial.CharacterDesign;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents a component plugin to handle the character design interface.
 * @author 'Vexia
 * @version 1.9
 */
public final class CharacterDesignInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(771, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (opcode) {
		case 155:
			CharacterDesign.handleButtons(player, button);
			break;
		}
		return true;
	}
}
