package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Represents the hooknodes jack dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class HooknosedJackDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code HooknosedJackDialogue} {@code Object}
	 */
	public HooknosedJackDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code HooknosedJackDialogue} {@code Object}.
	 * @param player the player.
	 */
	public HooknosedJackDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new HooknosedJackDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NORMAL, "Hello.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "What?");
			stage = 1;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Actually I've got no time for this. I don't want to talk to", "you.");
			stage = 2;
			break;
		case 2:
			end();
			break;
		}

		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 2948 };
	}

}