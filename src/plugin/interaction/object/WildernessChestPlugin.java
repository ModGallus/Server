package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;
import org.areillan.tools.RandomFunction;

/**
 * Represents the plugin used for the crystal chest.
 * @author 'Vexia
 * @version 1.0
 */
public final class WildernessChestPlugin extends UseWithHandler {

	/**
	 * Represents the bank key.
	 */
	private static final Item KEY = new Item(15010);

	/**
	 * Constructs a new {@code CrystalChestPlugin} {@code Object}.
	 */
	public WildernessChestPlugin() {
		super(15010);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(15722, OBJECT_TYPE, this);
		PluginManager.definePlugin(new OptionHandler() {

			@Override
			public Plugin<Object> newInstance(Object arg) throws Throwable {
				ObjectDefinition.forId(15722).getConfigurations().put("option:open", this);
				return this;
			}

			@Override
			public boolean handle(Player player, Node node, String option) {
				unlock(player);
				return true;
			}

		});
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		unlock(event.getPlayer());
		return true;
	}

	/**
	 * Unlocks the chest.
	 * @param player the player,
	 * @return true if so.
	 */
	private boolean unlock(Player player) {
		if (!player.getInventory().contains(15010, 1)) {
			player.getPacketDispatch().sendMessage("This chest is securely locked shut.");
			return true;
		}
		if (player.getInventory().freeSlots() == 0) {
			player.getPacketDispatch().sendMessage("Not enough inventory space.");
			return true;
		}
		if (player.getInventory().remove(KEY)) {
			Reward reward = Reward.getReward(player);
			for (Item i : reward.getItems()) {
				player.getInventory().add(i, player);
			}
			player.getPacketDispatch().sendMessage("You unlock the chest with your key.");
			player.getPacketDispatch().sendMessage("You find some teasure in the chest!");
		}
		return true;
	}

	/**
	 * Represents a crystal ches reward.
	 * @author 'Vexia
	 */
	public enum Reward {
		FIRST(39.69, 
		new Item(14940, 1), new Item(452, 20), new Item(14694, 20), new Item(200, 30), new Item(218, 15), new Item(454, 25), new Item(445, 75), new Item(1713, 1)), // FOR
		// MALES!
		TWELFTH_FEMALE(0.26, new Item(14940, 1), new Item(452, 20), new Item(14694, 20), new Item(200, 30), new Item(218, 15), new Item(454, 25), new Item(445, 75), new Item(1713, 1)); // FOR
		// SHEMALES(Female)

		/**
		 * Represents the item rewards.
		 */
		private final Item[] items;

		/**
		 * Represents the chance of getting the item.
		 */
		private final double chance;

		/**
		 * Constructs a new {@code CrystalChestPlugin} {@code Object}.
		 * @param chance the chance.
		 * @param items the item.
		 */
		Reward(double chance, Item... items) {
			this.chance = chance;
			this.items = items;
		}

		/**
		 * Gets the reward.
		 * @param player the player.
		 * @return the reward.
		 */
		public static Reward getReward(final Player player) {
			int totalChance = 0;
			for (Reward r : values()) {
				if (r == Reward.TWELFTH_FEMALE && player.getAppearance().isMale()) {
					continue;
				}
				totalChance += r.getChance();
			}
			final int random = RandomFunction.random(totalChance);
			int total = 0;
			for (Reward r : values()) {
				total += r.getChance();
				if (random < total) {
					return r;
				}
			}
			return null;
		}

		/**
		 * Gets the items.
		 * @return The items.
		 */
		public Item[] getItems() {
			return items;
		}

		/**
		 * Gets the chance.
		 * @return The chance.
		 */
		public double getChance() {
			return chance;
		}

	}

}