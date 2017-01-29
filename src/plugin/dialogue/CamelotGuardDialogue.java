package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Represents the dialogue plugin used for the camelot guards.
 * @author 'Vexia
 * @version 1.0
 */
public final class CamelotGuardDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code CamelotGuardDialogue} {@code Object}.
	 */
	public CamelotGuardDialogue() {
		/**
		 * empty.
		 */
	}

	public CamelotGuardDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new CamelotGuardDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Welcome to the Seer's Village courthouse. Court", "is not in session today, so you're not allowed downstairs.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 812 };
	}
}