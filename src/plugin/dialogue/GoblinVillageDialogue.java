package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.tools.RandomFunction;

/**
 * Represents the goblin village dialogue plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class GoblinVillageDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code GoblinVillageDialogue} {@code Object}.
	 */
	public GoblinVillageDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code GoblinVillageDialogue} {@code Object}.
	 * @param player the player.
	 */
	public GoblinVillageDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new GoblinVillageDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		int rand = RandomFunction.random(5);
		switch (rand) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I kill you human!");
			stage = 0;
			break;
		case 1:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Go away smelly human!");
			stage = 300;
			break;
		case 2:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Happy goblin new century!");
			stage = 100;
			break;
		case 3:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "What you doing here?");
			stage = 200;
			break;
		case 4:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Brown armour best!");
			stage = 800;
			break;
		case 5:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Go away smelly human!");
			stage = 300;
			break;
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			end();
			npc.getProperties().getCombatPulse().attack(player);
			break;
		case 100:
			interpreter.sendOptions("Select an Option", "Happy new century!", "What is the goblin new century?");
			stage = 101;
			break;
		case 101:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Happy new century!");
				stage = 169;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "What is the goblin new century?");
				stage = 102;
				break;
			}
			break;
		case 169:
			end();
			break;
		case 102:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "You tell human secrets!");
			stage = 103;
			break;
		case 103:
			end();
			break;
		case 200:
			interpreter.sendOptions("Select an Option", "I'm here to kill all you goblins!", "I'm just looking around.");
			stage = 201;
			break;
		case 201:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "I'm here to kill all you goblins!");
				stage = 143;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "I'm just looking around.");
				stage = 260;
				break;
			}
			break;
		case 143:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I kill you!");
			stage = 0;
			break;
		case 260:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Me not sure that allowed. You have to check with", "generals.");
			stage = 261;
			break;
		case 261:
			end();
			break;
		case 300:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "What you call me?");
			stage = 301;
			break;
		case 301:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "I kill you human!");
			stage = 0;
			break;
		case 800:
			interpreter.sendOptions("Select an Option", "Err or.", "Why is brown best?");
			stage = 801;
			break;
		case 801:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Err ok.");
				stage = 810;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Why is brown best?");
				stage = 820;
				break;
			}
			break;
		case 810:
			end();
			break;
		case 820:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "General Wartface and General Bentnoze both say it is.", "And normally they never agree!");
			stage = 821;
			break;
		case 821:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 4483, 4488, 4489, 4484, 4491, 4485, 4486, 4492, 4487, 4481, 4479, 4482, 4480 };
	}
}