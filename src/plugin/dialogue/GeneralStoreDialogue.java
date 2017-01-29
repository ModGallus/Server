package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Represents the gem trade dialogue plugin.
 * @author 'Life
 * @version 1.0
 */
public final class GeneralStoreDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GeneralStoreDialogue} {@code Object}.
	 */
	public GeneralStoreDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GeneralstoreDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GeneralStoreDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GeneralStoreDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Hello adventurer! I have various good, if interested");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Yes, let me take a look!", "No, thank you.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Yes, let me take a look!");
				stage = 10;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "No, thanks though.");
				stage = 20;
				break;

			}
			break;
		case 10:
			end();
			npc.openShop(player);
			break;
		case 20:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 7967 };
	}
}