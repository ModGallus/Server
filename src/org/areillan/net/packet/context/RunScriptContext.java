package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The run script packet context.
 * @author Emperor
 */
public class RunScriptContext implements Context {

	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The run script id.
	 */
	private int id;

	/**
	 * The parameters.
	 */
	private Object[] objects;

	/**
	 * The string.
	 */
	private String string;

	/**
	 * Construct a new {@code RunScriptContext} {@code Object}.
	 * @param player
	 * @param id
	 * @param string
	 * @param objects
	 */
	public RunScriptContext(Player player, int id, String string, Object... objects) {
		this.player = player;
		this.id = id;
		this.objects = objects;
		this.string = string;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Sets the player.
	 * @param player The player.
	 * @return This context instance.
	 */
	public Context setPlayer(Player player) {
		this.player = player;
		return this;
	}

	/**
	 * Get the run scripts id.
	 * @return The run scripts id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the objects.
	 * @return The objects.
	 */
	public Object[] getObjects() {
		return objects;
	}

	/**
	 * Get the string.
	 * @return The string.
	 */
	public String getString() {
		return string;
	}
}
