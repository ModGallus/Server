package plugin.skill.magic.lunar;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.skill.free.magic.MagicSpell;
import org.areillan.game.content.skill.free.magic.Runes;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.equipment.SpellType;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.SpellBookManager.SpellBook;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;

/**
 * Represents the hunter kit spell.
 * @author 'Vexia
 * @version 1.0
 */
public final class HunterKitSpell extends MagicSpell {

	/**
	 * Represents the hunter kit.
	 */
	private static final Item KIT = new Item(11159);

	/**
	 * Represents the animation of this graphics.
	 */
	private static final Animation ANIMATION = new Animation(6303);

	/**
	 * Repesents the graphick, next spells of this spell.
	 */
	private static final Graphics GRAPHIC = new Graphics(1074);

	/**
	 * Constructs a new {@code CureOtherSpell} {@code Object}.
	 */
	public HunterKitSpell() {
		super(SpellBook.LUNAR, 71, 70, null, null, null, new Item[] { new Item(Runes.ASTRAL_RUNE.getId(), 2), new Item(Runes.EARTH_RUNE.getId(), 2) });
	}

	@Override
	public Plugin<SpellType> newInstance(SpellType arg) throws Throwable {
		SpellBook.LUNAR.register(8, this);
		ItemDefinition.forId(KIT.getId()).getConfigurations().put("option:open", new OptionHandler() {
			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				if (player.getInventory().freeSlots() < 7) {
					player.getPacketDispatch().sendMessage("You don't have enough inventory space.");
					return true;
				}
				if (player.getInventory().remove((Item) node)) {
					player.getInventory().add(new Item[] { new Item(10150), new Item(10010), new Item(10006), new Item(10031), new Item(10029), new Item(596), new Item(10008) });
				}
				return true;
			}

			@Override
			public boolean isWalk() {
				return false;
			}

		});
		return this;
	}

	@Override
	public boolean cast(Entity entity, Node target) {
		Player p = (Player) entity;
		if (p.getInventory().freeSlots() == 0) {
			p.getPacketDispatch().sendMessage("Not enough inventory space!");
			return false;
		}
		if (!meetsRequirements(entity, true, true)) {
			return false;
		}
		if (p.getInventory().add(KIT)) {
			p.lock(5);
			p.animate(ANIMATION);
			p.graphics(GRAPHIC);
		}
		return true;
	}

}