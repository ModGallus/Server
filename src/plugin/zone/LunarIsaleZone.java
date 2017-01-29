package plugin.zone;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.content.global.shop.Shop;
import org.areillan.game.content.global.shop.ShopViewer;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.Option;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.node.entity.player.info.portal.DonatorType;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.entity.state.EntityState;
import org.areillan.game.node.item.Item;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.system.mysql.impl.ShopSQLHandler;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.system.task.TaskExecutor;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneBorders;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.game.world.repository.Repository;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

import plugin.activity.barrows.RareReward;
import plugin.activity.barrows.RewardChest;

/**
 * Home Mpa zone
 * 
 * @author Life
 * 
 */

public final class LunarIsaleZone extends MapZone implements Plugin<Object> {

	
	private static final CreditStore CREDIT_STORE = new CreditStore();

	
	public LunarIsaleZone() {
		super("Lunar Isale", true);
	}

	// (7967, '{2103, 3916, 0, 0, 0}'),
	@Override
	public void configure() {
		PluginManager.definePlugin(new RewardTraderDialogue());
		ShopSQLHandler.getUidShops().put(1485756, CREDIT_STORE);
		super.register(new ZoneBorders(2070, 3892, 2110, 3928));
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public boolean interact(Entity entity, Node node, Option option) {
		if (entity instanceof Player) {
			Player player = entity.asPlayer();
			switch (option.getName()) {
			case "Talk-to":
				switch (node.getId()) {
				case 8631:
					entity.asPlayer().getDialogueInterpreter().open(123656);
					return true;
				}
				break;
			case "Trade":
				switch (node.getId()) {
				case 8631:
					CREDIT_STORE.open(entity.asPlayer());
					return true;
				}
				break;
			}
		}
		return super.interact(entity, node, option);
	}

	/**
	 * The store that sells items in exchange for credits. Point prices are read
	 * from the item config table.
	 * 
	 * @author Splinter
	 */
	public static class CreditStore extends Shop {

		/**
		 * The default credit price if no item configuration was found.
		 */
		private final int DEFAULT_PRICE = 40;

		/**
		 * Constructs a new {@Code CreditStore} {@Code Object}
		 */
		public CreditStore() {
			super("Aincrad  <col=FF0000>Voting</col> Credit Shop", new Item[] { new Item(6199, 100000000), new Item(15079, 100000000), 
					new Item(15080, 100000000), new Item(15081, 100000000), new Item(14810, 100000000), new Item(14807, 100000000), new Item(14674, 100000000), 
					new Item(13661, 100000000), new Item(14816, 100000000), new Item(14813, 100000000), new Item(14784, 100000000), new Item(14976, 100000000), 
					new Item(1053, 100000000),new Item(1055, 100000000),new Item(1057, 100000000),new Item(1050, 100000000),new Item(962, 100000000)}, false);
			setPointShop(true);
		}

		@Override
		public void open(final Player player) {
			TaskExecutor.executeSQL(new Runnable() {
				@Override
				public void run() {
					if (player.getDetails().getShop().syncCredits()) {
						CreditStore.super.open(player);
						int credits = player.getDetails().getShop().getCredits();
						player.sendMessage("<col=CC0000>You currently have " + credits + (credits == 1 ? " credit" : " credits") + " to spend.");
					}
				}

			});
		}

		@Override
		public void buy(final Player player, final int slot, final int amount, final int tabIndex) {
			TaskExecutor.executeSQL(new Runnable() {
				@Override
				public void run() {
					if (player.getDetails().getShop().syncCredits()) {
						if (checkConditions(player, slot, amount, tabIndex)) {
							CreditStore.super.buy(player, slot, amount, tabIndex);
							player.sendMessage("You now have <col=CC0000>" + player.getDetails().getShop().getCredits() + "</col> credit(s) remaining.");
						}
					} else {
						player.sendMessages("Sorry, you cannot buy from the shop right now. Our database needs to sync", "some remaining data.");
					}
				}
			});
		}

		@Override
		public boolean canSell(Player player, Item item, ItemDefinition def) {
			player.sendMessage("You cannot sell items to this store.");
			return false;
		}

		@Override
		public String getPointsName() {
			return "credit";
		}

		@Override
		public void value(Player player, ShopViewer viewer, Item item, boolean sell) {
			if (sell) {
				player.sendMessage("You cannot sell items to this store.");
			} else {
				player.sendMessage(item.getName().contains("ring") ? "This item costs " + getBuyPrice(item, player) + " " + getPointsName() + "s plus a regular unimbued version of the ring." : "This item costs " + getBuyPrice(item, player) + " " + getPointsName() + "s.");
			}
		}

		@Override
		public int getBuyPrice(Item item, Player player) {
			return item.getDefinition().getConfiguration("point_price", DEFAULT_PRICE);
		}

		@Override
		public void decrementPoints(Player player, int decrement) {
			player.getDetails().getShop().setCredits(player.getDetails().getShop().getCredits() - decrement, true);
		}

		@Override
		public int getPoints(Player player) {
			return player.getDetails().getShop().getCredits();
		}

		/**
		 * Checks to see if the player is eligible to buy the item.
		 * 
		 * @return true if so.
		 */
		private boolean checkConditions(Player player, int slot, int amount, int tabIndex) {
			String itemName = CreditStore.super.getItems()[slot].getName();
			int buyId = CreditStore.super.getItems()[slot].getId();
			if (itemName.contains("ring")) {
				if (!player.getInventory().containsItem(getUnimbued(buyId, amount))) {
					player.sendMessage("You need 35 credits plus a regular version of the ring in order to upgrade to imbued.");
					return false;
				} else {
					if (player.getDetails().getShop().getCredits() > getBuyPrice(CreditStore.super.getItems()[slot], player) * amount) {
						if (player.getInventory().remove(getUnimbued(buyId, amount))) {
							return true;
						} else {
							player.sendMessage("You don't have that many unimbued rings.");
						}
					} else {
						player.sendMessage("You don't have that many credits.");
						return false;
					}
				}
			} else {
				return true;
			}
			return false;
		}

		/**
		 * Gets the un-imbued ring ID.
		 * 
		 * @return
		 */
		private Item getUnimbued(int imbuedRing, int amount) {
			switch (imbuedRing) {
			case 14810:
				return new Item(6737, amount);
			case 14808:
				return new Item(6733, amount);
			case 14809:
				return new Item(6735, amount);
			case 14807:
				return new Item(6731, amount);
			}
			return new Item(-1);
		}

	}

	/**
	 * Represents the dialogue plugin for the GE rewards trader
	 * 
	 * @author Splinter
	 */
	public final class RewardTraderDialogue extends DialoguePlugin {

		/**
		 * Constructs a new {@code RewardTraderDialogue} {@code Object}.
		 */
		public RewardTraderDialogue() {
			/**
			 * empty.
			 */
		}

		/**
		 * Constructs a new {@code RewardTraderDialogue} {@code Object}.
		 * 
		 * @param player
		 *            the player.
		 */
		public RewardTraderDialogue(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new RewardTraderDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			interpreter.sendDialogues(8631, FacialExpression.OSRS_NORMAL, "Greetings, " + player.getUsername() + ". I offer a wide assortment", "of valuable goods that you may be interested in.");
			stage = 0;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 0:
				interpreter.sendDialogues(8631, FacialExpression.OSRS_NORMAL, "The items I have for sale are in exchange for your", "continued dedication in assisting " + GameWorld.getName() + ". We", "truly appreciate the dedication of our players", "here on Aincrad.");
				stage = 1;
				break;
			case 1:
				interpreter.sendOptions("Select an Option", "How do I obtain credits?", "I would like to see your store.", "Nevermind.");
				stage = 2;
				break;
			case 2:
				switch (buttonId) {
				case 1:
					interpreter.sendDialogues(player, null, "How do I obtain " + GameWorld.getName() + " credits?");
					stage = 3;
					break;
				case 2:
					interpreter.sendDialogues(player, null, "I would like to see your store.");
					stage = 10;
					break;
				case 3:
					interpreter.sendDialogues(player, null, "Nevermind.");
					stage = 15;
					break;
				}
				break;
			case 3:
				interpreter.sendDialogues(8631, FacialExpression.OSRS_NORMAL, "Visit our website by heading to www.aincrad.co", "Log-in to the website with your forum details", "and then simply head over to the donations page", "to obtain some credits.");
				stage = 1;
				break;
			case 10:
				interpreter.sendDialogues(8631, FacialExpression.OSRS_NORMAL, "Certainly.");
				stage = 11;
				break;
			case 11:
				end();
				CREDIT_STORE.open(player);
				break;
			case 15:
				end();
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { 8631, 123656 };
		}
	}
}