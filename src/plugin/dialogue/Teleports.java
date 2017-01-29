package plugin.dialogue;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;

/**
 * Holds functionality for the teleport dialogues.
 * 
 * @author Areillan2016
 */
public final class Teleports {

	public static boolean sendDialogue(Player player, int button) {
		if(button != 66) {

			DialoguePlugin dial = new TeleportDialoguePlugin(player);
			if (dial != null && dial.open()) {
				player.getDialogueInterpreter().setDialogue(dial);
			}
			return true;
		}

		return false;
	}

	private static final class TeleportDialoguePlugin extends DialoguePlugin {

		public TeleportDialoguePlugin(Player player) {
			super(player);
		}

		@Override
		public DialoguePlugin newInstance(Player player) {
			return new TeleportDialoguePlugin(player);
		}

		@Override
		public boolean open(Object... args) {
			interpreter.sendOptions("Select an option.", "Monster Teleport", "Boss Teleport", "Minigame Teleport");
			stage = 1;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			switch (stage) {
			case 2:
				switch (buttonId) {// Cows, Rock Crabs, Yaks, Armoured Zombies
				case 1:
					player.teleport(new Location(2672, 3712, 0)); // rockcrabs
					break;
				case 2:
					player.teleport(new Location(2326, 3803, 0));// yaks
					break;
				case 3:
					player.teleport(new Location(3176, 2987, 0)); // bandits
					break;
				case 4:
					player.teleport(new Location(3239, 3606, 0));// azombies
					break;
				case 5:
					interpreter.sendOptions("Select an option.", "Chaos Druids", "Cockroach Soldiers", "Rock Lobsters", "Dagganoths", "More to be added.");
					stage = 3;
					break;
				}
			case 3: // monsters2
				switch (buttonId) {
				case 1:
					player.teleport(new Location(2572, 9500, 0)); // chaosdruids
					break;
				case 2:
					player.teleport(new Location(3159, 4276, 0));// COCKroaches
					break;
				case 3:
					player.teleport(new Location(1884, 4409, 0)); // rocklobs
					break;
				case 4:
					player.teleport(new Location(2528, 3739, 0)); // dags
					break;
				}
				break;
			case 4:
				switch (buttonId) { // bossesfirst
				case 1:
					player.teleport(new Location(3170, 3802, 0));// callisto
					break;
				case 2:
					player.teleport(new Location(1763, 5283, 0));// venenatis
					break;
				case 3:
					player.teleport(new Location(2916, 3749, 0));// gw
					break;
				case 4:
					player.teleport(new Location(2977, 3692, 0));// crzyarch
					break;
				case 5:
					interpreter.sendOptions("Select an option.", "Dagganoth Kings", "Giant Mole", "Kalphite Queen", "King Black Dragon", "More to be added.");
					stage = 5;
					break;
				}
			case 5:
				switch (buttonId) {// secondboss
				case 1:
					player.teleport(new Location(1912, 4367, 0));// dagkings
					break;
				case 2:
					player.teleport(new Location(1763, 5183, 0));// giantmole
					break;
				case 3:
					player.teleport(new Location(3503, 9494, 0));// KQ
					break;
				case 4:
					player.teleport(new Location(3069, 10257, 0));// KBD
					break;
				}
				break;

			case 6:
				switch (buttonId) { // firstlineminigames
				case 1:
					player.teleport(new Location(2592, 4317, 0));// puro puro
					break;//
				case 2:
					player.teleport(new Location(3315, 3163, 0)); // sorceershit
					break;
				case 3:
					player.teleport(new Location(3566, 3304, 0)); // barrows
					break;
				case 4:
					player.teleport(new Location(2659, 2649, 0)); // pc
					break;
				}
				break;

			case 7: // GamesFirst
				switch (buttonId) {
				case 1:
					player.teleport(new Location(2444, 5172, 0));// pits
					break;
				case 2:
					player.teleport(new Location(2875, 3546, 0));// warriorguild
					break;
				case 3:
					player.teleport(new Location(3363, 3312, 0));// magearena
					break;
				case 4:
					player.teleport(new Location(3314, 3235, 0));// duelarena
					break;
				case 5:
					interpreter.sendOptions("Select an option.", "Puro Puro", "Sorcerous Garden", "Barrows", "Pest control");
					stage = 6;
					break;
				}
				break;

			case 8:
				switch (buttonId) {
				case 1:
					interpreter.sendOptions("Select an option.", "Rock Crabs", "Yaks", "Bandits", "Armoured Zombies", "Next page");
					stage = 1;
					break;
				case 2:
					interpreter.sendOptions("Select an option.", "Callisto", "Venenatis", "Godwars", "Crazy Archaeologist", "Next page");
					stage = 4;
					break;
				case 3:
					interpreter.sendOptions("Select an option.", "Fight Pits", "Warriors Guild", "Mage Training Arena", "Duel Arena", "Next page");
					stage = 7;
					break;
				case 6:
					interpreter.sendOptions("Select an option.", "Dagganoth King", "Giant Mole", "Kalphite Queen", "King Black Dragon", "More to be added.");
					stage = 5;
					break;
				case 4:
					interpreter.sendOptions("Select an option.", "Chaos Druids", "Cockroach Soldiers", "Lesser Demons", "Dagganoths", "More to be added.");
					stage = 3;
					break;
				case 5:
					interpreter.sendOptions("Select an option.", "Puro Puro", "Sorcerous Garden", "Barrows", "Pest control", "More to be added.");
					stage = 6;
					break;
				}
				break;

			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] {};
		}

	}
}
