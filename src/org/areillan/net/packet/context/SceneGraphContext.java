package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.net.packet.Context;

/**
 * The update scene graph packet context.
 * @author Emperor
 */
public class SceneGraphContext implements Context {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * If we are logging in.
	 */
	private final boolean login;

	/**
	 * Constructs a new {@code SceneGraphContext} {@code Object}.
	 * @param player The player.
	 * @param login If we are logging in.
	 */
	public SceneGraphContext(Player player, boolean login) {
		this.player = player;
		this.login = login;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the login.
	 * @return The login.
	 */
	public boolean isLogin() {
		return login;
	}

}