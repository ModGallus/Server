package plugin.npc.familiar;

import java.util.List;

import org.areillan.game.content.skill.member.summoning.familiar.Familiar;
import org.areillan.game.content.skill.member.summoning.familiar.FamiliarSpecial;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.areillan.game.node.entity.combat.equipment.WeaponInterface;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.tools.RandomFunction;

/**
 * Represents the Giant Chinchompa familiar.
 * @author Aero
 * @author Vexia
 */
public class GiantChinchompaNPC extends Familiar {

	/**
	 * Constructs a new {@code GiantChinchompaNPC} {@code Object}.
	 */
	public GiantChinchompaNPC() {
		this(null, 7353);
	}

	/**
	 * Constructs a new {@code GiantChinchompaNPC} {@code Object}.
	 * @param owner The owner.
	 * @param id The id.
	 */
	public GiantChinchompaNPC(Player owner, int id) {
		super(owner, id, 3100, 12800, 3, WeaponInterface.STYLE_RANGE_ACCURATE);
	}

	@Override
	public Familiar construct(Player owner, int id) {
		return new GiantChinchompaNPC(owner, id);
	}

	@Override
	public void onAttack(Entity entity) {
		super.onAttack(entity);
		if (RandomFunction.random(20) == 10) {
			executeSpecialMove(new FamiliarSpecial(null));
		}
	}

	@Override
	protected boolean specialMove(FamiliarSpecial special) {
		if (!isOwnerAttackable()) {
			return false;
		}
		final List<Entity> entitys = RegionManager.getLocalEntitys(owner, 6);
		entitys.remove(owner);
		entitys.remove(this);
		sendChat("Squeak!");
		animate(Animation.create(7758));
		graphics(Graphics.create(1364));
		GameWorld.submit(new Pulse(3, owner, this) {
			@Override
			public boolean pulse() {
				for (Entity entity : entitys) {
					if (canCombatSpecial(entity, false)) {
						entity.getImpactHandler().manualHit(GiantChinchompaNPC.this, RandomFunction.random(13), HitsplatType.NORMAL);
					}
				}
				dismiss();
				return true;
			}
		});
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7353, 7354 };
	}

}