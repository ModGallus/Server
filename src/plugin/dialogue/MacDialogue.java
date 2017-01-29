package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.dialogue.FacialExpression;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;

/**
 * Represents the dialogue plugin used for the Mac npc.
 * @author 'Lee
 * @version 1.0
 */
public final class MacDialogue extends DialoguePlugin {

	/**
	 * Represents the coins item.
	 */
	private static final Item COINS = new Item(995, 2277000);
	
	private static final Item MAX_CAPE = new Item(14839);
	
	private static final Item MAX_HOOD = new Item(14841);

	/**
	 * Constructs a new {@code LuthasDialogue} {@code Object}.
	 */
	public MacDialogue() {
		/**
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code MacDialogue} {@code Object}.
	 * @param player the player.
	 */
	public MacDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MacDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		npc = (NPC) args[0];
			player("Hello.");
			stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		switch (stage) {
		case 0:
			interpreter.sendDialogue("The man glances at you and grunts something unintelligible.");
			stage = 1;
			break;
		case 1:
			interpreter.sendOptions("Select an Option", "Who are you?", "What do you have in your sack?","Why are you so dirty?","Bye.");
			stage = 2;
			break;
		case 2:
			switch (buttonId) {
			case 1:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "Who are you?");
				stage = 11;
				break;
			case 2:
				interpreter.sendDialogues(player, FacialExpression.NORMAL, "What do you have in your sack?");
				stage = 20;
				break;
			case 3:
				player("Why are you so dirty?");
				stage = 30;
				break;
			case 4:
				stage = 54;
				break;
			}
			break;
		case 11:
			npc("Mac. What's it you?");
			stage = 12;
			break;
		case 12:
			player("Only trying to be friendly");
			stage = 1;
			break;
		case 20:
			npc("S'me Cape.");
			stage = 21;
			break;
		case 21:
			player("Your cape?");
			stage = 22;
			break;
		case 22:
			interpreter.sendOptions("Select an Option", "Can I have it?", "Why do you keep it in a sack?");
			stage = 23;
			break;
		case 23:
			switch (buttonId) 
			{
			case 1:
				player("Can I have it?");
				stage = 40;
				break;
			case 2:
				player("Why do you keep it in a sack?");
				stage = 25;
				break;
			}
			break;		
		case 25:
			npc("Get it dirty.");
			stage = 1;
			break;
		case 30:
			npc("Bath XP Waste.");
			stage = 1;
			break;
		case 40:
			if(player.getSkills().getTotalLevel() == 2376 )
			{
				npc("Mebe.");
				stage = 41;
				break;
			}
			else
			{
				npc("nope.");
				player.sendMessage("You've not reached the level of a master yet.");
				stage = 54;
				break;
			}
		case 41:
			player("I'm sure I could make it worth your while.");
			stage = 42;
			break;
		case 42:
			npc("How much?");
			stage = 43;
			break;
		case 43:
			player("How about 2277000 gold?");
			stage = 44;
			break;
		case 44:
			interpreter.sendOptions("Buy Mac's Cape for 2277000 Gold?", "Yes, pay the man.", "No.");
			stage = 45;
			break;
		case 45:
			switch (buttonId) {
			case 1:
				if (!player.getInventory().containsItem(COINS)) {
					player.sendMessage("You don't have enough coins.");
					end();
					return true;
				}
				if (player.getInventory().remove(COINS)) {
					interpreter.sendItemMessage(MAX_CAPE, "Mac grunts and hands over his cape, pocketing your","money swiftly.");
					//player.getInventory().add(MAX_CAPE);
					player.getInventory().add(MAX_HOOD);
					
					
					if (!player.getInventory().add(MAX_CAPE) && !player.getInventory().add(MAX_HOOD)) {
						end();
						player.sendMessage("You don't have enough room in your inventory.");
						break;
					}
					stage = 46;
					break;
				}
				break;
			
			case 2:
				stage = 54;
				break;
			}
			break;
		case 46:
			interpreter.sendDialogues(2253, FacialExpression.NORMAL, "Hello?");
			stage = 47;
			break;
		case 47:
			player("Huh? Who's there?");
			stage = 48;
			break;
		case 48:
			interpreter.sendDialogues(2253, FacialExpression.NORMAL, "It's Dionysius here. I'm using my magic to","communicate to you. Don't worry.");
			stage = 49;
			break;
		case 49:
			player("What are you doing in my head?");
			stage = 50;
			break;
		case 50:
			interpreter.sendDialogues(2253, FacialExpression.NORMAL, "I just wanted to say congratulations, I've been","watching you in your adventures - very well done.");
			stage = 51;
			break;
		case 51:
			interpreter.sendDialogues(0, FacialExpression.NORMAL, "Oh very well done. It's taken you awhile but i","knew you could do it.");
			stage = 52;
			break;
		case 52:
			interpreter.sendDialogues(8688, FacialExpression.NORMAL, "Ahhh, I knew you had potential when I first","met you. My compliments on reaching the pinnacle of","your achievements.");
			stage = 53;
			break;
		case 53:
			interpreter.sendDialogues(2478, FacialExpression.NORMAL, "Meow!");
			stage = 54;
			break;
		case 54:
			end();
			break;

			
			
		case 60:
			npc("You not strong enough.");
			stage = 51;
			break;
			
		}
		return true;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8687 };
	}
}