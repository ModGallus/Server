package plugin.skill.gather;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.skill.free.gather.GatheringSkillPulse;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Handles the gathering skill option handler plugin.
 * @author Emperor
 * @version 1.0
 */
public final class GatheringSkillOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("chop down", this);
		ObjectDefinition.setOptionHandler("chop-down", this);
		ObjectDefinition.setOptionHandler("mine", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		player.getPulseManager().run(new GatheringSkillPulse(player, (GameObject) node));
		return true;
	}

}