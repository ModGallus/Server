package plugin.skill.cooking;

import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * @author Adam
 */
public class NettleWaterPlugin extends UseWithHandler {

	public NettleWaterPlugin() {
		super(1921);
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		player.getInventory().remove(new Item(1921, 1));
		player.getInventory().remove(new Item(4241, 1));
		player.getInventory().add(new Item(4237, 1));
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(4241, ITEM_TYPE, this);
		return this;
	}

}
