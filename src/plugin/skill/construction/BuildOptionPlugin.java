package plugin.skill.construction;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.component.Component;
import org.areillan.game.content.skill.member.construction.BuildHotspot;
import org.areillan.game.content.skill.member.construction.Decoration;
import org.areillan.game.content.skill.member.construction.RoomBuilder;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * The build option handling plugin.
 * @author Emperor
 *
 */
public final class BuildOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ObjectDefinition.setOptionHandler("build", this);
		ObjectDefinition.setOptionHandler("remove", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!player.getHouseManager().isBuildingMode()) {
			player.getPacketDispatch().sendMessage("You have to be in building mode to do this.");
			return true;
		}
		if (option.equals("remove")) {
			Decoration decoration = Decoration.getDecoration(player, node.getLocation());
			GameObject object = ((GameObject) node);	
			if (decoration == null || !object.isActive()) {
				return false;
			}
			RoomBuilder.removeDecoration(player, (GameObject) node);
			return true;
		}
		player.setAttribute("con:hsobject", node);
		//TODO if hotspot != door?
		if (node.asObject().getType() < 4 && !node.asObject().getDefinition().getName().equalsIgnoreCase("fencing") && !node.asObject().getDefinition().getName().equalsIgnoreCase("combat ring space") && !node.asObject().getDefinition().getName().equalsIgnoreCase("window space")) {
			if (RoomBuilder.roomExists(player, node.asObject())) {
				player.getDialogueInterpreter().open("con:remove", "room", "room", node.asObject());
				return true;
			}
			player.getInterfaceManager().open(new Component(402));
			return true;
		}
		BuildHotspot hotspot = BuildHotspot.forId(((GameObject) node).getId());
		if (hotspot == null) {
			return false;
		}
		if (player.getHouseManager().getRoomAmount() < 15) {
			player.setAttribute("con:hotspot", hotspot);
			RoomBuilder.openBuildInterface(player, hotspot);
		} else {
			player.getPacketDispatch().sendMessage("You currently have the maximum amount of rooms available");
		}
		return true;
	}

}