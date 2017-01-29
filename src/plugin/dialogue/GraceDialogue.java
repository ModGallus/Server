package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.GroundItem;
import org.areillan.game.node.item.GroundItemManager;
import org.areillan.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the Mac npc.
 * @author 'Lee
 * @version 1.0
 */
public final class GraceDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item GRACE = new Item(15105);
	
	private static final int HOOD_PRICE = 15;
	private static final int TOP_PRICE = 20;
	private static final int LEG_PRICE = 20;
	private static final int CAPE_PRICE = 15;
	private static final int BOOT_PRICE = 15;
	private static final int GLOVES_PRICE = 10;


	/**
	 * Constructs a new {@code LuthasDialogue} {@code Object}.
	 */
	public GraceDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MacDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GraceDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GraceDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
			player("I'd like to buy something.");
			stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			npc("What would you like to buy?");
			stage = 1;
			break;
		case 1:
			options("Graceful Hood (15 Marks)", "Graceful Top (20 Marks)", "Graceful Legs (20 Marks)", "Next page..");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				if (player.getInventory().getAmount(GRACE) >= HOOD_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15088, "You have received a pair of Graceful Hood.");
					player.getInventory().add(new Item(15088, 1));
					player.getInventory().remove(new Item(15105, 15));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 2:
				if (player.getInventory().getAmount(GRACE) >= TOP_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15092, "You have received a pair of Graceful Top.");
					player.getInventory().add(new Item(15092, 1));
					player.getInventory().remove(new Item(15105, 20));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 3:
				if (player.getInventory().getAmount(GRACE) >= LEG_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15094, "You have received a pair of Graceful Legs.");
					player.getInventory().add(new Item(15094, 1));
					player.getInventory().remove(new Item(15105, 20));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 4:
				options("Graceful Cape (15 Marks)", "Graceful boots (15 Marks)", "Graceful Gloves (10 Marks)", "..Previous");
				stage = 3;
				break;
			}
			break;
		case 3:
			switch (buttonId)
			{
			case 1:
				if (player.getInventory().getAmount(GRACE) >= CAPE_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15090, "You have received a pair of Graceful Cape.");
					player.getInventory().add(new Item(15090, 1));
					player.getInventory().remove(new Item(15105, 15));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 2:
				if (player.getInventory().getAmount(GRACE) >= BOOT_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15098, "You have received a pair of Graceful Boots.");
					player.getInventory().add(new Item(15098, 1));
					player.getInventory().remove(new Item(15105, 15));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 3:
				if (player.getInventory().getAmount(GRACE) >= GLOVES_PRICE) {
					player.getDialogueInterpreter().sendItemMessage(15096, "You have received a pair of Graceful Gloves.");
					player.getInventory().add(new Item(15096, 1));
					player.getInventory().remove(new Item(15105, 10));				
					stage = 100;
					break;
				}
				else
				{
					interpreter.sendDialogue("You don't have enough <col=FF0000>Marks of Grace</col> to purchase this item right now.");
					stage = 100;
					break;
				}
			case 4:
				options("Graceful Hood(15 Marks)", "Graceful Top(20 Marks)", "Graceful Legs(20 Marks)", "Next page..");
				stage = 2;
				break;
			
			}
			break;
		case 99:
			npc("Certainly.");
			stage = 100;
			break;
		case 100:
			end();
			break;

			
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8689 };
	}
}