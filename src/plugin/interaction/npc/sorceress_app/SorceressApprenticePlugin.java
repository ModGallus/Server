package plugin.interaction.npc.sorceress_app;

import org.areillan.cache.def.impl.NPCDefinition;
import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.global.action.ClimbActionHandler;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Teleport option for Sorceress Apprentice
 * @author SonicForce41
 */
public class SorceressApprenticePlugin extends OptionHandler {

	private static final Location TOP = Location.create(3322, 3138, 1);

	@Override
	public boolean handle(Player player, Node node, String option) {
		switch (option) {
		case "teleport":
			if (player.getSavedData().getGlobalData().hasSpokenToApprentice()) {
				NPC npc = (NPC) node;
				SorceressApprenticeDialogue.teleport(npc, player);
			} else {
				player.getDialogueInterpreter().sendDialogues(((NPC) node), null, "I can't do that now, I'm far too busy sweeping.");
			}
			break;
		case "climb-up":
			if (node.getLocation().getX() == 3322) {
				ClimbActionHandler.climb(player, new Animation(828), TOP);
			} else {
				ClimbActionHandler.climbLadder(player, (GameObject) node, option);
				return true;
			}
			break;
		}
		return true;
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		NPCDefinition.forId(5532).getConfigurations().put("option:teleport", this);
		ObjectDefinition.forId(21781).getConfigurations().put("option:climb-up", this);
		new SorceressStairs().newInstance(arg);
		PluginManager.definePlugin(new SorceressApprenticeDialogue());
		return this;
	}

	/**
	 * Represents the option handler used for the sorcceress stairs.
	 * @author 'Vexia
	 * @version 1.0
	 */
	public final static class SorceressStairs extends OptionHandler {

		@Override
		public Plugin<Object> newInstance(Object arg) throws Throwable {
			ObjectDefinition.forId(35645).getConfigurations().put("option:climb-down", this);
			return this;
		}

		@Override
		public boolean handle(Player player, Node node, String option) {
			player.getProperties().setTeleportLocation(Location.create(3325, 3143, 0));
			return true;
		}

	}
}
