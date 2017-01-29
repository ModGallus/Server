package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.map.Location;

/**
 * Handles the MosolReiDialogue dialogue.
 * @author Life
 */
public class MosolReiDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MosolReiDialogue} {@code Object}.
	 */
	public MosolReiDialogue() {

	}

	/**
	 * Constructs a new {@code MosolReiDialogue} {@code Object}.
	 * @param player The player.
	 */
	public MosolReiDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MosolReiDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		stage = args.length > 1 ? (Integer) args[1] : 0;
		if (stage == 0) {
			interpreter.sendDialogues(500, FacialExpression.NORMAL, "Good day to you bwana.");
		} else {
			handle(0, 0);
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Can I go through this fence please?", "What is inside of here?");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Can I go through this fence please?");
				stage = 10;
				break;
			case 2:
				player("What is inside of here?");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc("Most certainly, but I must charge you the sum of 10,000", "coins first.");
			stage = 11;
			break;
		case 11:
			if (!player.getInventory().contains(995, 10000)) {
				player("I don't have the money on me at the moment.");
				stage = -1;
				break;
			}
			options("Ok, here's 10,000 coins.", "Never mind.");
			stage = 12;
			break;
		case 12:
			switch (buttonId) {
			case 1:
				player("Ok, here's 10,000 coins.");
				stage = 32;
				break;
			case 2:
				player("Never mind.");
				stage = -1;
				break;
			}
			break;
		case 20:
			npc("The wonderful city of Shilo Vilage, home of", "Duradel the Slayer Master, Gem Rocks", "a wonderful bank, and fishing!");
			end();
			break;
		case 32:
			Item item = new Item(995, 10000);
			if (player.getInventory().remove(item)) {
				player.getPacketDispatch().sendMessage("You pay 10,000 coins.");
				player.setAttribute("mosol:paid", true);
				interpreter.sendItemMessage(item, "You give Mosol Rei 10,000 coins.");
				stage = 33;
				break;
			}
			end();
			break;
		case 33:
			npc("Many thanks. You may now pass into Shilo Village!");
			player.getTeleporter().send(Location.create(2867, 2953, 0), TeleportType.CABBAGE);
			stage = -1;
			break;
		case 35:
			if (player.getAttribute("mosol:paid", false)) {
				npc("You have already given me lots of nice coins, you may", "go in.");
				stage = -1;
				break;
			}
			if (!player.getInventory().contains(995, 10000)) {
				player("I don't have the money on me at the moment.");
				stage = -1;
				break;
			}
			stage = 12;
			handle(interfaceId, 1);
			break;
		case -1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 500 };
	}
}