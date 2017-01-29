package plugin.skill.thieving;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.skill.member.thieving.Pickpocket;
import org.areillan.game.content.skill.member.thieving.PickpocketPulse;
import org.areillan.game.content.skill.member.thieving.Stall;
import org.areillan.game.content.skill.member.thieving.StallThiefPulse;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle thieving options.
 * @author 'Vexia
 * @date 22/10/2013
 */
public class ThievingOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.setOptionHandler("pick-pocket", this);
		NPCDefinition.setOptionHandler("pickpocket", this);
		ObjectDefinition.setOptionHandler("steal-from", this);
		ObjectDefinition.setOptionHandler("steal from", this);
		NPCDefinition.forId(2082).getConfigurations().put("option:pickpocket", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "pick-pocket":
		case "pickpocket":
			player.getPulseManager().run(new PickpocketPulse(player, (NPC) node, Pickpocket.forNPC((NPC) node)));
			break;
		case "steal-from":
		case "steal from":
			player.getPulseManager().run(new StallThiefPulse(player, (GameObject) node, Stall.forObject((GameObject) node)));
			break;
		}
		return true;
	}

}
