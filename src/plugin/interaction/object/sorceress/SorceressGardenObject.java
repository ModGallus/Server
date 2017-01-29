package plugin.interaction.object.sorceress;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.DoorActionHandler;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Hanldes the sorceress garden gates.
 * @author 'Vexia
 */
public class SorceressGardenObject extends OptionHandler {

	@Override
	public boolean handle(Player player, Node node, String option) {
		GardenObjectsPlugin.SeasonDefinitions def = GardenObjectsPlugin.SeasonDefinitions.forGateId(((GameObject) node).getId());
		if (def != null) {
			if (player.getSkills().getStaticLevel(Skills.THIEVING) < def.getLevel()) {
				player.getDialogueInterpreter().sendItemMessage(10692, "You need Thieving level of " + def.getLevel() + " to pick the lock of this gate.");
				return true;
			}
			DoorActionHandler.handleAutowalkDoor(player, (GameObject) node);
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.forId(21709).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(21753).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(21731).getConfigurations().put("option:open", this);
		ObjectDefinition.forId(21687).getConfigurations().put("option:open", this);
		return this;
	}

}
