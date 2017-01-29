package plugin.interaction.item;

import java.util.concurrent.TimeUnit;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles the Lee voting bond item.
 * @author Lee
 *
 */
public class MaxCapePlugin extends OptionHandler {
	
	/**
	 * The arios bond item.
	 */
	private static final Item CAPE = new Item(14839);
	

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(14839).getConfigurations().put("option:teleports", this);
		ItemDefinition.forId(14839).getConfigurations().put("option:features", this);
		
		PluginManager.definePlugin(new MaxCapeDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		Item item = node.asItem();
		switch (option) {
		case "teleports":
			player.getDialogueInterpreter().open(DialogueInterpreter.getDialogueKey("max-cape"));
			break;
		case "features":
			if (!player.getBank().hasSpaceFor(item)) {
				player.sendMessage("You don't have enough space in your bank for that item.");
				return true;
			}
			if (player.getInventory().remove(item)) {
				player.getBank().add(item);
				player.sendMessage("You deposit your Aincrad voting bond into your bank.");
			}
			break;
		}
		return true;
	}

	@Override
	public boolean isWalk() {
		return false;
	}

	/**
	 * Handles the arios voting bond dialogue.
	 * @author Vexia
	 *
	 */
	public class MaxCapeDialogue extends DialoguePlugin {

		/**
		 * Constructs the {@code AriosVotingBondDialogue}
		 */
		public MaxCapeDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs the {@code AriosVotingBondDialogue}
		 * @param player The player.
		 */
		public MaxCapeDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new MaxCapeDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			options("Double Experience (2 Hour)", "Ultra Lamp (x3)", "Coming soon", "Convert (20 Credits)");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				stage = 1;
				switch (buttonId) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					break;
				case 4:
					
					break;
				}
				break;
			case 1:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {DialogueInterpreter.getDialogueKey("max-cape")};
		}

	}

}
