package plugin.combat.special;

import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatStyle;
import org.areillan.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.areillan.game.node.entity.impl.Animator.Priority;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * Handles Vesta's Longsword special attack, feint.
 * @author Splinter
 * @version 1.0
 */
public final class FeintSpecialHandler extends MeleeSwingHandler implements Plugin<Object> {

	/**
	 * The special energy required.
	 */
	private static final int SPECIAL_ENERGY = 25;

	/**
	 * The attack animation.
	 */
	private static final Animation ANIMATION = new Animation(10502, Priority.HIGH);


	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		if (CombatStyle.MELEE.getSwingHandler().register(13899, this) && CombatStyle.MELEE.getSwingHandler().register(13901, this))
			;
		return this;
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		if (!((Player) entity).getSettings().drainSpecial(SPECIAL_ENERGY)) {
			return -1;
		}
		int hit = 0;
		if (isAccurateImpact(entity, victim, CombatStyle.MELEE, 1.0, 1.0)) {
			hit = RandomFunction.random(calculateHit(entity, victim, RandomFunction.random(1.0, 2.50)));
		}
		state.setEstimatedHit(hit);
		return 1;
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		entity.animate(ANIMATION);
	}
}