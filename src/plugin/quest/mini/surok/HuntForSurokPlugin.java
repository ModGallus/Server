package plugin.quest.mini.surok;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.Location;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles the hunt for surok mini quest.
 * @author Vexia
 */
public class HuntForSurokPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		PluginManager.definePlugin(new DakhThoulanAegisDialogue());
		PluginManager.definePlugin(new MishkalunDornDialogue());
		PluginManager.definePlugin(new SilasDahcsnuDialogue());
		PluginManager.definePlugin(new SurokMagisDialogue());
		ObjectDefinition.forId(28780).getConfigurations().put("option:use", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "use":
			switch (node.getId()) {
			case 28780:
				player.teleport(new Location(3326, 5469, 0));
				break;
			}
			break;
		}
		return true;
	}

}
