package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * The gnomecopter ticket handling plugin.
 * @author Emperor
 */
public final class GnomecopterTicket extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(12843).getConfigurations().put("option:read", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getInterfaceManager().open(new Component(729));
		String info = "Gnomecopter ticket:";
		info += "<br>" + "Castle Wars"; // Destination
		info += "<br>" + "Ref. #000";
		for (int i = 3; i < 8; i++) {
			info += RandomFunction.randomize(10);
		}
		player.getPacketDispatch().sendString(info, 729, 2);
		return true;
	}

}