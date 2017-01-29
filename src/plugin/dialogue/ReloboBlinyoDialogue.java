package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.content.eco.ge.GEGuidePrice;
import org.areillan.game.content.eco.ge.GEGuidePrice.GuideType;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Handles the ReloboBlinyoDialogue dialogue.
 * @author 'Vexia
 */
public class ReloboBlinyoDialogue extends DialoguePlugin {

	public ReloboBlinyoDialogue() {

	}

	public ReloboBlinyoDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {

		return new ReloboBlinyoDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		interpreter.sendDialogues(player, FacialExpression.NORMAL, "Hey there.");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Why hello to you too, my friend.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "You look like you've travelled a fair distance.", "I'm trying to find the prices for logs.", "Sorry, I need to be macking tracks.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "You look like you've travelled a fair distance.");
				stage = 20;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "I'm trying to find the prices for logs.");
				stage = 10;
				break;
			case 3:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Sorry, I need to be making tracks.");
				stage = 30;
				break;
			}
			break;
		case 10:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Then you've come to the right person.");
			stage = 11;
			break;
		case 11:
			end();
			GEGuidePrice.open(player, GuideType.LOGS);
			break;
		case 20:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "What gave me away?");
			stage = 21;
			break;
		case 21:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "I don't mean to be rude, but the face paint and", "hair, for startes.");
			stage = 22;
			break;
		case 22:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Ah, yes I'm from Shilo Village on Karamja. It's a style", "I've had since I was little.");
			stage = 23;
			break;
		case 23:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "Then tell me, why are you so far from home?");
			stage = 24;
			break;
		case 24:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "This Grand Exchange! Isn't it marvellous I've never seen", "anything like it in my life. My people were not pleased to", "see me break traditions to visit such a place. But i hope", "to make some serious profit. then they'll see I was right!");
			stage = 25;
			break;
		case 25:
			interpreter.sendDialogues(player, FacialExpression.NORMAL, "So, what are you selling?");
			stage = 26;
			break;
		case 26:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Logs! of all kinds! That's my plan, at least. Nature", "is one thing my people understand very well.");
			stage = 27;
			break;
		case 27:
			end();
			break;
		case 30:
			interpreter.sendDialogues(npc, FacialExpression.NORMAL, "Okay. Nice talking to you!");
			stage = 31;
			break;
		case 31:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 6526 };
	}

}