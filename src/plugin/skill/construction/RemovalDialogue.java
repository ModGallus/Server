package plugin.skill.construction;

import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.skill.member.construction.RoomBuilder;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;

/**
 * Removal dialogue for room & decorations in construction
 * @author Clayton Williams
 * @date Jun-30-2015
 */
public final class RemovalDialogue extends DialoguePlugin {
	
	/**
	 * Type of removal
	 */
	private String type;
	
	/**
	 * Arguments
	 */
	private Object[] args;

	/**
	 * Removal Dialogue
	 */
	public RemovalDialogue() {
		super();
	}
	
	/**
	 * Removal Dialogue
	 * @param player
	 */
	public RemovalDialogue(Player player) {
		super(player);
	}
	
	@Override
	public DialoguePlugin newInstance(Player player) {
		return new RemovalDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		this.args = args;
		type = (String) args[1];
		player.getDialogueInterpreter().sendOptions("Remove this " + args[0] + "?", "Yes", "No");
		stage = 0;
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		if (buttonId == 1) {
			switch (type) {
				case "room":
					GameObject door = (GameObject) args[2];
					int[] loc = RoomBuilder.getRoomPosition(door);
					player.getHouseManager().getRooms()[player.getLocation().getZ()][loc[0]][loc[1]] = null;
					player.getHouseManager().reload(player, true);
					break;
				case "decoration":
					//TODO
					break;
			}
		}
		return super.close();
	}

	@Override
	public int[] getIds() {
		return new int[]{ DialogueInterpreter.getDialogueKey("con:remove") };
	}
	
}