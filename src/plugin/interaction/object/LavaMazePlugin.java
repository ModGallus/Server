package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.ClimbActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Handles the lava maze.
 * @author Vexia
 */
public final class LavaMazePlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(1767).getConfigurations().put("option:climb-down", this);
		ObjectDefinition.forId(1768).getConfigurations().put("option:climb-up", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (node.getId()) {
		case 1767:
			if (node.getLocation().getX() == 3069) {
				ClimbActionHandler.climb(player, null, Location.create(3017, 10248, 0));
				return true;
			}
			ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			return true;
		case 1768:
			if (node.getLocation().getX() == 3017) {
				ClimbActionHandler.climb(player, null, Location.create(3069, 3857, 0));
				return true;
			}
			ClimbActionHandler.climbLadder(player, (GameObject) node, option);
			return true;
		}
		return true;
	}

}
