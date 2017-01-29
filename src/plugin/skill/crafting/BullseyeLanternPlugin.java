package plugin.skill.crafting;

import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Handles the bullseye lantern crafting.
 * @author Vexia
 *
 */
public class BullseyeLanternPlugin extends UseWithHandler {
	
	/**
	 * Constructs the {@code BullseyeLanternPlugin}
	 */
	public BullseyeLanternPlugin() {
		super(4542, 1607);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(4544, ITEM_TYPE, this);
		return this;
	}
	
	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Item with = event.getUsedWith().asItem();
		int rewardId = 4546;
		if (with.getId() == 1607) {
			rewardId = 4700;
		}
		if (player.getInventory().remove(event.getUsedItem())) {
			player.getInventory().replace(new Item(rewardId), event.getUsedWith().asItem().getSlot());
		}
		return true;
	}

}
