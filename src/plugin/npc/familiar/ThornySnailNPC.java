package plugin.npc.familiar;

import org.areillan.game.content.skill.member.summoning.familiar.BurdenBeast;
import org.areillan.game.content.skill.member.summoning.familiar.Familiar;
import org.areillan.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatStyle;
import org.areillan.game.node.entity.impl.Projectile;
import org.areillan.game.node.entity.impl.Animator.Priority;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.tools.RandomFunction;

/**
 * Represents the Thorny Snail familiar.
 * @author Aero
 * @author Vexia
 */
public class ThornySnailNPC extends BurdenBeast {

	/**
	 * Constructs a new {@code ThornySnailNPC} {@code Object}.
	 */
	public ThornySnailNPC() {
		this(null, 6806);
	}

	/**
	 * Constructs a new {@code ThornySnailNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public ThornySnailNPC(Player owner, int id) {
		super(owner, id, 1600, 12019, 3, 3);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new ThornySnailNPC(owner, id);
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		final Entity target = (Entity) special.getNode();
		if (!canCombatSpecial(target)) {
			return false;
		}
		visualizeSpecialMove();
		visualize(new Animation(8148, Priority.HIGH), Graphics.create(1385));
		Projectile.magic(this, target, 1386, 40, 36, 51, 10).send();
		int ticks = 2 + (int) Math.floor(getLocation().getDistance(target.getLocation()) * 0.5);
		getProperties().getCombatPulse().setNextAttack(4);
		faceTemporary(target, 2);
		GameWorld.submit(new Pulse(ticks, this, target) {
			@Override
			public boolean pulse() {
				BattleState state = new BattleState(ThornySnailNPC.this, target);
				int hit = 0;
				if (CombatStyle.MAGIC.getSwingHandler().isAccurateImpact(ThornySnailNPC.this, target)) {
					hit = RandomFunction.randomize(8);
				}
				state.setEstimatedHit(hit);
				target.getImpactHandler().handleImpact(owner, hit, CombatStyle.MAGIC, state);
				target.graphics(new Graphics(1387));
				return true;
			}
		});
		return true;
	}

	@Override
	public boolean isPoisonImmune() {
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6806, 6807 };
	}

}