package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.node.object.GameObject;
import org.areillan.net.packet.Context;

/**
 * Represents the build object packet context, <br> which is used for
 * construct/clear object outgoing packet.
 * @author Emperor
 */
public final class BuildObjectContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The list of game objects to send.
	 */
	private final GameObject gameObject;

	/**
	 * Constructs a new {@code BuildObjectContext} {@code Object}.
	 * @param player The player
	 * @param gameObjects The list of game objects to send.
	 */
	public BuildObjectContext(Player player, GameObject gameObject) {
		this.player = player;
		this.gameObject = gameObject;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the gameObject.
	 * @return The gameObject.
	 */
	public GameObject getGameObject() {
		return gameObject;
	}

}