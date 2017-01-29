package plugin.skill.construction;

import org.areillan.game.component.Component;
import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.component.ComponentPlugin;
import org.areillan.game.content.skill.member.construction.BuildHotspot;
import org.areillan.game.content.skill.member.construction.Decoration;
import org.areillan.game.content.skill.member.construction.HouseManager;
import org.areillan.game.content.skill.member.construction.RoomBuilder;
import org.areillan.game.content.skill.member.construction.RoomProperties;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Handles the creating of a decoration object.
 * @author Emperor
 *
 */
public final class ConstructionInterface extends ComponentPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ComponentDefinition.put(396, this);
		ComponentDefinition.put(398, this);
		ComponentDefinition.put(400, this);
		ComponentDefinition.put(402, this);
		return this;
	}

	@Override
	public boolean handle(Player player, Component component, int opcode, int button, int slot, int itemId) {
		switch (component.getId()) {
		case 396:
			switch (button) {
			case 132:
				player.getInterfaceManager().close();
				BuildHotspot hotspot = player.getAttribute("con:hotspot");
				GameObject object = player.getAttribute("con:hsobject");
				if (hotspot == null || object == null) {
					break;
				}
				slot = ((slot % 2 != 0) ? 4 : 0) + (slot >> 1);
				if (slot >= hotspot.getDecorations().length) {
					break;
				}
				Decoration deco = hotspot.getDecorations()[slot];
				RoomBuilder.buildDecoration(player, hotspot, deco, object);
				return true;
			}
			break;
		case 398:
			switch (button) {
			case 14:
				player.getHouseManager().toggleBuildingMode(player, true);
				return true;
			case 1:
				player.getHouseManager().toggleBuildingMode(player, false);
				return true;
			case 15:
				player.getHouseManager().expelGuests(player);
				return true;
			case 13:
				if (player.getHouseManager().getRegion() != player.getViewport().getRegion()) {
					player.getPacketDispatch().sendMessage("You can't do this outside of your house.");
					return true;
				}
				HouseManager.leave(player);
				return true;
			}
			break;
		case 402:
			int index = button - 160;
			System.err.println("BuildRoom Interface Index: " + index);
			if (index > -1 && index < RoomProperties.values().length) {
				player.getDialogueInterpreter().open("con:room", RoomProperties.values()[index]);
				return true;
			}
			break;
		}
		return false;
	}

}