package plugin.combat.special;

import java.util.List;

import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatStyle;
import org.areillan.game.node.entity.combat.InteractionType;
import org.areillan.game.node.entity.combat.handlers.MeleeSwingHandler;
import org.areillan.game.node.entity.impl.Animator.Priority;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * Handles Vesta's Spear special attack - Spear Wall.
 * @author Splinter
 */
public final class SpearWallSpecialHandler extends MeleeSwingHandler implements Plugin<Object> {

	/**
	 * The special energy required.
	 */
	private static final int SPECIAL_ENERGY = 50;

	/**
	 * The attack animation.
	 */
	private static final Animation ANIMATION = new Animation(10499, Priority.HIGH);

	/**
	 * The graphic.
	 */
	private static final Graphics GRAPHIC = new Graphics(1835);

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void impact(Entity entity, Entity victim, BattleState state) {
		if (state.getTargets() != null) {
			for (BattleState s : state.getTargets()) {
				if (s != null) {
					s.getVictim().getImpactHandler().handleImpact(entity, s.getEstimatedHit(), CombatStyle.MELEE, s);
				}
			}
			return;
		}
		victim.getImpactHandler().handleImpact(entity, state.getEstimatedHit(), CombatStyle.MELEE, state);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		CombatStyle.MELEE.getSwingHandler().register(13905, this);
		CombatStyle.MELEE.getSwingHandler().register(13907, this);
		return this;
	}

	@Override
	public int swing(Entity entity, Entity victim, BattleState state) {
		if (!((Player) entity).getSettings().drainSpecial(SPECIAL_ENERGY)) {
			return -1;
		}
		boolean multi = entity.getProperties().isMultiZone();
		if (!multi) {
			return super.swing(entity, victim, state);
		}
		@SuppressWarnings("rawtypes")
		List list = victim instanceof NPC ? RegionManager.getSurroundingNPCs(entity, 9, entity) : RegionManager.getSurroundingPlayers(entity, 9, entity);
		BattleState[] targets = new BattleState[list.size()];
		int count = 0;
		for (Object o : list) {
			Entity e = (Entity) o;
			if (CombatStyle.RANGE.getSwingHandler().canSwing(entity, e) != InteractionType.NO_INTERACT) {
				BattleState s = targets[count++] = new BattleState(entity, e);
				int hit = 0;
				hit = RandomFunction.random(calculateHit(entity, e, 1.0));
				s.setEstimatedHit(hit);
			}
		}
		state.setTargets(targets);
		return 1;
	}

	@Override
	public void visualize(Entity entity, Entity victim, BattleState state) {
		entity.visualize(ANIMATION, GRAPHIC);
	}

	@Override
	public void visualizeImpact(Entity entity, Entity victim, BattleState state) {
		if (state.getTargets() != null) {
			for (BattleState s : state.getTargets()) {
				if (s != null) {
					s.getVictim().animate(victim.getProperties().getDefenceAnimation());
				}
			}
			return;
		}
		victim.animate(victim.getProperties().getDefenceAnimation());
	}
}
