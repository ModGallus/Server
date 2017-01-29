package org.areillan.net.packet.context;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.net.packet.Context;

/**
 * Represents the context of animating an object.
 * @author Emperor
 * @author 'Vexia
 * @date 10/11/2013
 */
public class AnimateObjectContext implements Context {

	/**
	 * Represents the player of the context.
	 */
	private final Player player;

	/**
	 * Represents the animation to use.
	 */
	private final Animation animation;

	/**
	 * Constructs a new {@code AnimateObjectContext} {@code Object}.
	 * @param player the player.
	 * @param object the object.
	 * @param animation the animation.
	 * @param global if it is global.
	 */
	public AnimateObjectContext(Player player, Animation animation) {
		this.player = player;
		this.animation = animation;
	}

	@Override
	public Player getPlayer() {
		if (player != null && player.isArtificial() && (Player)((AIPlayer)player).getControler().getAttribute("aip_became") == player) {
			return ((AIPlayer)player).getControler();
		}
		return player;
	}

	/**
	 * Gets the animation.
	 * @return The animation.
	 */
	public Animation getAnimation() {
		return animation;
	}
}