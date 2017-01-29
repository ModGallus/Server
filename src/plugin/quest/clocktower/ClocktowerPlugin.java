package plugin.quest.clocktower;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.ClimbActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.quest.Quest;
import org.areillan.game.node.item.Item;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.plugin.Plugin;

/**
 * Represents the clocktower plugin used to handle relative node interactions.
 * @author Life
 */
public final class ClocktowerPlugin extends OptionHandler {

	/**
	 * Represents the object id of the drain.
	 */
	public static final int DRAIN_ID = 17424;

	/**
	 * Represents the location of the lumbridge sewers.
	 */
	private static final Location SEWER_LOCATION = new Location(3237, 9858, 0);

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(881).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(882).getConfigurations().put("option:close", this);
		ObjectDefinition.forId(882).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(DRAIN_ID).getConfigurations().put("option:search", this);
		ObjectDefinition.forId(17429).getConfigurations().put("option:take", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final Quest quest = player.getQuestRepository().getQuest("Clock Tower");
		final int id = node instanceof GameObject ? ((GameObject) node).getId() : node instanceof Item ? ((Item) node).getId() : ((NPC) node).getId();
		switch (id) {
		case DRAIN_ID:
			if (quest.getStage(player) == 20) {
				player.getDialogueInterpreter().open(883, 883, "key");
				return true;
			} else {
				player.sendMessage("You search the castle drain and find nothing of value.");
			}
			return true;
		case 881:
			ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(882));
			break;
		case 882:
			switch (option) {
			case "climb-down":
				if (node.getLocation().equals(new Location(3237, 3458, 0))) {
					ClimbActionHandler.climb(player, new Animation(828), SEWER_LOCATION);
				} else {
					ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				}
				break;
			case "close":
				ObjectBuilder.replace(((GameObject) node), ((GameObject) node).transform(881));
				break;
			}
			break;
		}
		return true;
	}

}
