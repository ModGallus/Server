package plugin.skill.crafting;

import org.areillan.game.content.skill.free.crafting.SilverProduct;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to string jewelry.
 * @author 'Vexia
 * @version 1.0
 */
public final class SilverStringingPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code SilverStringingPlugin} {@code Object}.
	 */
	public SilverStringingPlugin() {
		super(1714, 1720);
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		SilverProduct silver = null;
		if (event.getUsedItem().getId() != 1759) {
			silver = SilverProduct.forProductId(event.getUsedItem().getId());
		} else if (((Item) event.getUsedWith()).getId() != 1759) {
			silver = SilverProduct.forProductId((((Item) event.getUsedWith()).getId()));
		}
		if (silver == null) {
			return true;
		}
		player.getInventory().remove(new Item(silver.getProduct(), 1));
		player.getInventory().remove(new Item(1759, 1));
		player.getInventory().add(new Item(silver.getStrung(), 1));
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(1759, ITEM_TYPE, this);
		return null;
	}

}
