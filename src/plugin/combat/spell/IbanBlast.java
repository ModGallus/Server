package plugin.combat.spell;

import org.areillan.game.container.impl.EquipmentContainer;
import org.areillan.game.content.skill.free.magic.Runes;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatSpell;
import org.areillan.game.node.entity.combat.equipment.SpellType;
import org.areillan.game.node.entity.impl.Projectile;
import org.areillan.game.node.entity.impl.Animator.Priority;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;

/**
 * Represents the iban blast spell.
 * @author Emperor
 * @version 1.0
 */
public final class IbanBlast extends CombatSpell {

	/**
	 * Constructs a new {@code IbanBlast} {@code Object}.
	 */
	public IbanBlast() {
		super(SpellType.IBANS_BLAST, SpellBook.MODERN, 50, 60.5, -1, -1, new Animation(708, Priority.HIGH), new Graphics(87, 96), Projectile.create((Entity) null, null, 88, 40, 36, 52, 75, 15, 11), new Graphics(89, 96), Runes.FIRE_RUNE.getItem(5), Runes.DEATH_RUNE.getItem(1));
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		if (((Player) entity).getEquipment().getNew(EquipmentContainer.SLOT_WEAPON).getId() != 1409) {
			((Player) entity).getPacketDispatch().sendMessage("You need to wear Iban's staff to cast this spell.");
			return false;
		}
		return super.cast(entity, target);
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(29, this);
		return this;
	}

	@Override
	public int getMaximumImpact(Entity entity, Entity victim, BattleState state) {
		return type.getImpactAmount(entity, victim, 0);
	}
}