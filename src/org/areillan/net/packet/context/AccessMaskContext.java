package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The access mask context.
 * @author Emperor
 */
public class AccessMaskContext implements Context {

	/**
	 * The player reference.
	 */
	private Player player;

	/**
	 * The access mask id.
	 */
	private int id;

	/**
	 * The child id.
	 */
	private int childId;

	/**
	 * The interface id.
	 */
	private int interfaceId;

	/**
	 * The offset.
	 */
	private int offset;

	/**
	 * The length.
	 */
	private int length;

	/**
	 * Construct a new {@code AccessMaskContext} {@code Object}.
	 * @param player
	 * @param id
	 * @param childId
	 * @param interfaceId
	 * @param offset
	 * @param length
	 */
	public AccessMaskContext(Player player, int id, int childId, int interfaceId, int offset, int length) {
		this.player = player;
		this.id = id;
		this.childId = childId;
		this.interfaceId = interfaceId;
		this.offset = offset;
		this.length = length;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Transforms this access mask context into a new instance with the player
	 * instance and id value changed.
	 * @param player The player to set.
	 * @param id The id to set.
	 * @return The access mask context.
	 */
	public AccessMaskContext transform(Player player, int id) {
		return new AccessMaskContext(player, id, childId, interfaceId, offset, length);
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
	 * Get the access mask id.
	 * @return The access mask.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the child id.
	 * @return The child id.
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * Get the interface id.
	 * @return The interface id.
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Get the offset.
	 * @return The offset.
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Get the length.
	 * @return The length.
	 */
	public int getLength() {
		return length;
	}
}
