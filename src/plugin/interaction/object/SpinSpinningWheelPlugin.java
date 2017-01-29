package plugin.interaction.object;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

/**
 * Handles the spining interface open.
 * @author Adam
 */
public class SpinSpinningWheelPlugin extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getInterfaceManager().open(new Component(459));
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(2644).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(4309).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(8748).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(20365).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(21304).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(25824).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(26143).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(34497).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(36970).getConfigurations().put("option:spin", this);
		ObjectDefinition.forId(37476).getConfigurations().put("option:spin", this);
		return this;
	}

	@Override
	public Location getDestination(Node node, Node n) {
		return null;
	}

}
