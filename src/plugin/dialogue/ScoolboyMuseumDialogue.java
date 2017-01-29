package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Handles the ScoolboyMuseumDialogue dialogue.
 * @author 'Vexia
 */
public class ScoolboyMuseumDialogue extends DialoguePlugin {

	public ScoolboyMuseumDialogue() {

	}

	public ScoolboyMuseumDialogue(Player player) {
		super(player);
	}

	@Override
	public int[] getIds() {
		return new int[] { 5946 };
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
	public DialoguePlugin newInstance(Player player) {

		return new ScoolboyMuseumDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Can you find my teacher? I need the toilet!");
		stage = 0;
		return true;
	}
}