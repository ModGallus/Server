package plugin.interaction.inter;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.music.MusicEntry;
import org.areillan.plugin.Plugin;

/**
 * Handles the interface tab buttons.
 * @author Emperor
 */
public final class MusicTabInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(187, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (opcode) {
		case 155:
			switch (button) {
			case 11:
				player.getMusicPlayer().toggleLooping();
				return true;
			case 1:
				MusicEntry entry = player.getMusicPlayer().getUnlocked().get(slot);
				if (entry == null) {
					player.getPacketDispatch().sendMessage("You have not unlocked this piece of music yet!</col>");
					return true;
				}
				player.getMusicPlayer().play(entry);
				return true;
			}
			break;
		}
		return false;
	}

}