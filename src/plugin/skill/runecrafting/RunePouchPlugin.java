package plugin.skill.runecrafting;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.skill.free.runecrafting.RunePouch;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.node.item.ItemPlugin;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles the rune pouches.
 * @author Vexia
 */
public class RunePouchPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (RunePouch pouch : RunePouch.values()) {
			for (int i = 0; i < 2; i++) {
				Item item = i == 0 ? pouch.getPouch() : pouch.getDecayedPouch();
				if (item != null) {
					ItemDefinition.forId(item.getId()).getConfigurations().put("option:fill", this);
					ItemDefinition.forId(item.getId()).getConfigurations().put("option:empty", this);
					ItemDefinition.forId(item.getId()).getConfigurations().put("option:check", this);
					ItemDefinition.forId(item.getId()).getConfigurations().put("option:drop", this);
				}
			}
		}
		PluginManager.definePlugin(new RunePouchItem());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final RunePouch pouch = RunePouch.forItem((Item) node);
		pouch.action(player, (Item) node, option);
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	/**
	 * An item plugin to wrapp around rune pouches.
	 * @author Vexia
	 */
	public class RunePouchItem extends ItemPlugin {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			for (RunePouch pouch : RunePouch.values()) {
				for (int i = 0; i < 2; i++) {
					Item item = i == 0 ? pouch.getPouch() : pouch.getDecayedPouch();
					if (item != null) {
						ItemDefinition.forId(item.getId()).setItemPlugin(this);
					}
				}
			}
			return this;
		}

		@Override
		public void remove(Player player, Item item, int type) {
			final RunePouch pouch = RunePouch.forItem(item);
			if (pouch == null) {
				return;
			}
			switch (type) {
			case DROP:
				pouch.onDrop(player, item);
				break;
			}
		}

		@Override
		public boolean createDrop(Item item, Player player, NPC npc, Location location) {
			if (player.hasItem(item)) {
				return false;
			}
			return super.createDrop(item, player, npc, location);
		}
	}
}
