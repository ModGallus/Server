package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.DoorActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle the brass key door plugin.
 * @author 'Vexia
 * @version 1.0
 */
public final class BrassKeyDoorPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (player.getInventory().contains(983, 1)) {
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
		} else {
			player.getPacketDispatch().sendMessage("This door is locked.");
			return true;
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(1804).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return DoorActionHandler.getDestination(((Player) node), ((GameObject) n));
	}

}
