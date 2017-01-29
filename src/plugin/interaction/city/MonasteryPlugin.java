package plugin.interaction.city;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.ClimbActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Handles the edgeville monastery.
 * @author Vexia
 */
public final class MonasteryPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2641).getConfigurations().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 2641:
			final boolean abbot = node.getLocation().equals(new Location(3057, 3483, 0));
			if (!player.getSavedData().getGlobalData().isJoinedMonastery()) {
				player.getDialogueInterpreter().open(abbot ? 801 : 7727, true);
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			}
			break;
		}
		return true;
	}

}
