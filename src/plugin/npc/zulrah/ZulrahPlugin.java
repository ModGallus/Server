package plugin.npc.zulrah;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.activity.ActivityManager;
import org.areillan.game.content.dialogue.DialogueAction;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.GameWorld;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles interactions related to Zulrah.
 * @author Vexia
 *
 */
public class ZulrahPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(10075).getConfigurations().put("option:board", this);
		PluginManager.definePlugin(new ZulrahCutscene(), new ZulrahNPC());
		return this;
	}

	@Override
	public boolean handle(final Player player, Node node, String option) {
		if (node.asObject().getId() == 10075) {
			player.getDialogueInterpreter().sendOptions("Return to Zulrah's shrine?", "Yes", "No");
			player.getDialogueInterpreter().addAction(new DialogueAction() {
				@Override
				public void handle(Player p, int buttonId) {
					switch(buttonId) {
					case 2:
						ActivityManager.start(p, "zulrah cutscene", false);
						break;
					case 3:
						p.getDialogueInterpreter().close();
						break;
					}
					
				}
				
			});
			return true;
		}
		return true;
	}
	
}
