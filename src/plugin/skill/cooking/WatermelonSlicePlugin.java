package plugin.skill.cooking;

import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.item.GroundItemManager;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to slice a watermelon.
 * @author 'Vexia
 * @version 1.0
 */
public final class WatermelonSlicePlugin extends UseWithHandler {

	/**
	 * Represents the knife item.
	 */
	private static final Item KNIFE = new Item(946);

	/**
	 * Represents the watermelon item.
	 */
	private static final Item WATERMELON = new Item(5982);

	/**
	 * Represents the watermelon slice item.
	 */
	private static final Item WATERMELON_SLICE = new Item(5984);

	/**
	 * Constructs a new {@code WatermelonSlicePlugin.java} {@code Object}.
	 */
	public WatermelonSlicePlugin() {
		super(KNIFE.getId());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(5982, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		if (event.getPlayer().getInventory().remove(WATERMELON)) {
			for (int i = 0; i < 3; i++) {
				if (!event.getPlayer().getInventory().add(WATERMELON_SLICE)) {
					GroundItemManager.create(WATERMELON_SLICE, event.getPlayer());
				}
			}
			event.getPlayer().getPacketDispatch().sendMessage("You slice the watermelon into three slices.");
		}
		return true;
	}

}
