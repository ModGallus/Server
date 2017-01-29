package plugin.skill.magic;

import org.areillan.game.content.skill.free.magic.MagicSpell;
import org.areillan.game.content.skill.free.magic.Runes;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.equipment.SpellType;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;

/**
 * Represents the charge spell magic spell.
 * @author Emperor
 * @version 1.0
 */
public final class ChargeSpell extends MagicSpell {

	/**
	 * Constructs a new {@code ChargeSpell} {@code Object}.
	 */
	public ChargeSpell() {
		super(SpellBook.MODERN, 80, 180, Animation.create(811), new Graphics(6, 96), null, new Item[] { Runes.FIRE_RUNE.getItem(3), Runes.BLOOD_RUNE.getItem(3), Runes.AIR_RUNE.getItem(3) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.MODERN.register(58, this);
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		final Player p = (Player) entity;
		if (p.getLocks().isLocked("charge_cast")) {
			p.getPacketDispatch().sendMessage("You need to wait for the spell to recharge.");
			return false;
		}
		if (!meetsRequirements(entity, true, true)) {
			return false;
		}
		p.getLocks().lock("charge_cast", 100);
		visualize(entity, target);
		p.getStateManager().set(EntityState.CHARGED);
		p.getPacketDispatch().sendMessage("You feel charged with magic power.");
		return true;
	}

}