package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * Packet context for music.
 * @author Emperor
 * @author SonicForce41
 */
public class MusicContext implements Context {

	/**
	 * The Player
	 */
	private Player player;

	/**
	 * The music Id
	 */
	private int musicId;

	/**
	 * The secondary music type.
	 */
	private boolean secondary;

	/**
	 * Constructs a new {@code MusicContext} {@code Object}.
	 * @param player The player.
	 * @param musicId The music id.
	 */
	public MusicContext(Player player, int musicId) {
		this(player, musicId, false);
	}

	/**
	 * Constructs a new {@code MusicContext} {@code Object}.
	 * @param player The player.
	 * @param musicId The music id.
	 * @param temporary The temporary music type.
	 */
	public MusicContext(Player player, int musicId, boolean temporary) {
		this.player = player;
		this.musicId = musicId;
		this.secondary = temporary;
	}

	/**
	 * Gets the Music Id
	 * @return the musicId
	 */
	public final int getMusicId() {
		return musicId;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the secondary.
	 * @return The secondary.
	 */
	public boolean isSecondary() {
		return secondary;
	}

	/**
	 * Sets the secondary.
	 * @param secondary The secondary to set.
	 */
	public void setSecondary(boolean secondary) {
		this.secondary = secondary;
	}

}