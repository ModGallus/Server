package plugin.interaction.item;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.global.ttrail.ClueLevel;
import org.areillan.game.content.global.ttrail.ClueScrollPlugin;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.repository.Repository;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;
import org.areillan.tools.RandomFunction;

/**
 * Handles the arios voting bond item.
 * @author Vexia
 *
 */
public class AriosVotingBond extends OptionHandler {
	
	/**
	 * The arios bond item.
	 */
	private static final Item BOND = new Item(14807);
	
	/**
	 * The ultra lamp item.
	 */
	private static final Item ULTRA_LAMP = new Item(14820);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(14807).getConfigurations().put("option:redeem", this);
		ItemDefinition.forId(14807).getConfigurations().put("option:deposit", this);
		
		PluginManager.definePlugin(new AriosVotingBondDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		Item item = node.asItem();
		switch (option) {
		case "redeem":
			player.getDialogueInterpreter().open(DialogueInterpreter.getDialogueKey("arios-bond"));
			break;
		case "deposit":
			if (!player.getBank().hasSpaceFor(item)) {
				player.sendMessage("You don't have enough space in your bank for that item.");
				return true;
			}
			if (player.getInventory().remove(item)) {
				player.getBank().add(item);
				player.sendMessage("You deposit your Aincrad Bond into your bank.");
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
	public class AriosVotingBondDialogue extends DialoguePlugin {

		/**
		 * Constructs the {@code AriosVotingBondDialogue}
		 */
		public AriosVotingBondDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs the {@code AriosVotingBondDialogue}
		 * @param player The player.
		 */
		public AriosVotingBondDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new AriosVotingBondDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			options("Double Experience (2 Hour)", "Ultra Lamp (x3)", "Convert (GP)", "Convert (20 Credits)");
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
					if (player.getSavedData().getGlobalData().hasDoubleExp()) {
						interpreter.sendItemMessage(14807, "You already have <col=FF0000>double EXP</col> active!");
						return true;
					}
					if (player.getInventory().remove(BOND)) {
						player.getSavedData().getGlobalData().setDoubleExp(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2));
						player.getStateManager().set(EntityState.DOUBLE_EXPERIENCE, 7200, 0);
						interpreter.sendItemMessage(14807, "You redeemed 2 <col=FF0000>hour</col> of double EXP!");
					}
					break;
				case 2:
					if (!player.getInventory().hasSpaceFor(ULTRA_LAMP) && !player.getInventory().hasSpaceFor(ULTRA_LAMP) && !player.getInventory().hasSpaceFor(ULTRA_LAMP)) 
					{
						if (player.getInventory().remove(BOND)) {
							player.getBank().add(ULTRA_LAMP);
							player.getBank().add(ULTRA_LAMP);
							player.getBank().add(ULTRA_LAMP);
							
							interpreter.sendItemMessage(14820, "You didn't have enough space, your Lamps have been added to the bank..");
						}
						return true;
					}
					else
						
					if (player.getInventory().remove(BOND)) {
						player.getInventory().add(ULTRA_LAMP);
						player.getInventory().add(ULTRA_LAMP);
						player.getInventory().add(ULTRA_LAMP);
						interpreter.sendItemMessage(14820, "You redeem 3 <col=FF0000>Ultra Lamps</col>.");
						return true;
					}
					break;
				case 3:
					Item coins = new Item(995, RandomFunction.random(10000,  75000));
					if (!player.getInventory().hasSpaceFor(coins)) {
						interpreter.sendItemMessage(14807, "Sorry, you don't have enough inventory space.");
						return true;
					}
					if (player.getInventory().remove(BOND)) {
						DecimalFormat formatter = new DecimalFormat("#,###");
						player.getInventory().add(coins);
						interpreter.sendItemMessage(14807, "You redeem <col=FF0000>" + formatter.format(coins.getAmount()) + "</col> gold coins.");
						Repository.sendNews("" + player.getUsername() + " redeemed " + formatter.format(coins.getAmount()) + " gold coins from an Aincrad voting bond!", 15, "<col=FF0000>");
					}
					break;
				case 4:
					if (player.getInventory().remove(BOND)) {
						interpreter.sendItemMessage(14807, "You've destroyed your bond for a return of <col=FF0000>20</col> credits.");
						player.getDetails().getShop().setCredits(player.getDetails().getShop().getCredits() +20, true);						
					}
					return true;
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
			return new int[] {DialogueInterpreter.getDialogueKey("arios-bond")};
		}

	}

}
