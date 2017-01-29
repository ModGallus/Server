package plugin.interaction.inter;

import java.util.concurrent.TimeUnit;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.portal.DonatorType;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.system.mysql.impl.ShopSQLHandler;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;

//import plugin.zone.LunarIsaleZone.TeleporterDialogue;

/**
 * Handles the world map interface.
 * @author Emperor
 *
 */
public final class WorldMapInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(66, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}