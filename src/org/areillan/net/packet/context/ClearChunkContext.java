package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.world.map.RegionChunk;
import org.areillan.net.packet.Context;

/**
 * The packet context for the clear region chunk outgoing packet.
 * @author Emperor
 */
public final class ClearChunkContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * The region chunk to clear.
	 */
	private final RegionChunk chunk;

	/**
	 * Constructs a new {@code ClearChunkContext} {@code Object}.
	 * @param player The player.
	 * @param chunk The chunk to clear.
	 */
	public ClearChunkContext(Player player, RegionChunk chunk) {
		this.player = player;
		this.chunk = chunk;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the chunk.
	 * @return The chunk.
	 */
	public RegionChunk getChunk() {
		return chunk;
	}

}
