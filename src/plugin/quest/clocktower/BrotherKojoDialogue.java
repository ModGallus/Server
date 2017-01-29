package plugin.quest.clocktower;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;

/**
 * Handles the MosolReiDialogue dialogue.
 * @author Life
 */
public class BrotherKojoDialogue extends DialoguePlugin {

	/**
	 * Constructs a new {@code MosolReiDialogue} {@code Object}.
	 */
	public BrotherKojoDialogue() {

	}

	/**
	 * Constructs a new {@code MosolReiDialogue} {@code Object}.
	 * @param player The player.
	 */
	public BrotherKojoDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new BrotherKojoDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
		stage = args.length > 1 ? (Integer) args[1] : 0;
		if (stage == 0) {
			interpreter.sendDialogues(223, FacialExpression.NORMAL, "Hello adventurer. My name is Brother Kojo.", "Do you happen to know the time?");
		} else {
			handle(0, 0);
		}
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendOptions("Select an Option", "Uhh, no sorry.", "Yeah, of course I do.");
			stage = 1;
			break;
		case 1:
			switch (buttonId) {
			case 1:
				player("Uhh, no sorry.");
				stage = 10;
				break;
			case 2:
				player("Yeah, of course I do.");
				stage = 20;
				break;
			}
			break;
		case 10:
			npc("Exactly! This Clock tower has recently broken down", "and without it nobody can tell the correct time.", " I must fix it before the town people become too angry!");
			stage = 11;
			break;
		case 11:
			npc("I don't suppose you could assist me in the repairs?", "I'll pay you for your help.");
			stage = 12;
			break;
		case 12:
			interpreter.sendOptions("Select an Option", "Ok, old monk. What can I do?", "How much reward are we talking?", "Not now, old monk.");
			stage = 44;
			break;
		case 44:
			switch (buttonId) {
			case 1:
				player("Ok, old monk. What can I do?");
				stage = 27;
				break;
			case 2:
				player("How much reward are we talking?");
				stage = 25;
				break;
			case 3:
				player("Not now, old monk.");
				end();
				break;
			}
			break;
		case 20:
			npc("WRONG. This Clock tower has recently broken down", "and without it nobody can tell the correct time.", " I must fix it before the town people become too angry!");
			stage = 11;
			break;
		case 25:
			npc("Well, I'm only a monk so I'm not exactly rich, but I assure", "you, I will give you a fair reward for the time spent", "assisting me in repairing the clock.");
			stage = 26;
			break;
		case 26:
			switch (buttonId) {
			case 1:
				player("Ok, old monk. What can I do?");
				stage = 27;
				break;
			case 2:
				player("Not now, old monk.");
				end();
				break;
			}
			break;
			
		case 27:
			npc("Oh, thank you kind sir! In the cellar below", "you'll find four cogs, They're too heavy for me", "but you should be able to carry them one at a time.");
			stage = 28;
			break;
		case 28:
			npc("I know one goes on each floor... but I can't exactly", "remember which goes where specifically. Oh well, I'm sure", "you can figure it out fairly easily.");
			stage = 29;
			break;
		case 29:
			player("Yeah, I'll do my best.");
			stage = 30;
			break;
		case 30:
			npc("Thank you again! And remember to be careful, the cellar is full of strange beasts!");
			end();
			
		case -1:
			end();
			break;
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 223 };
	}
}