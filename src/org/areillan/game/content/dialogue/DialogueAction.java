package org.areillan.game.content.dialogue;

import org.areillan.game.node.entity.player.Player;

/**
 * A dialogue action.
 * @author Vexia
 */
public interface DialogueAction {

	/**
	 * Handles a dialogue click.
	 * @param player the player.
	 * @param buttonId the buttonId.
	 */
	public void handle(Player player, int buttonId);

}
