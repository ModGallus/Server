package plugin.combat.special;

import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatStyle;
import org.areillan.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.areillan.game.node.entity.impl.Animator.Priority;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.audio.Audio;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * Handles the Judgement special attack.
 * @author Emperor
 * @version 1.0
 */
public final class JudgementSpecialHandler extends MeleeSwingHandler implements Plugin<Object> {

	/**
	 * The special energy required.
	 */
	private static final int SPECIAL_ENERGY = 50;

	/**
	 * The attack animation.
	 */
	private static final Animation ANIMATION = new Animation(7074, Priority.HIGH);

	/**
	 * The graphic.
	 */
	private static final Graphics GRAPHIC = new Graphics(1222);

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		if (CombatStyle.MELEE.getSwingHandler().register(11694, this) && CombatStyle.MELEE.getSwingHandler().register(13450, this))
			;
		return this;
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		if (!((Player) entity).getSettings().drainSpecial(SPECIAL_ENERGY)) {
			return -1;
		}
		int hit = 0;
		if (isAccurateImpact(entity, victim, CombatStyle.MELEE, 1.25, 0.98)) {
			hit = RandomFunction.random(calculateHit(entity, victim, 1.25));
		}
		state.setEstimatedHit(hit);
		entity.asPlayer().getAudioManager().send(new Audio(3865), true);
		return 1;
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		entity.visualize(ANIMATION, GRAPHIC);
	}
}