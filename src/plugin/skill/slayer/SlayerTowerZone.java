package plugin.skill.slayer;

import org.areillan.game.content.global.action.DoorActionHandler;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.interaction.Option;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneBorders;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.game.world.map.zone.ZoneRestriction;
import org.areillan.plugin.Plugin;

/**
 * Handles the slayer tower map zone.
 * @author Vexia
 */
public final class SlayerTowerZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code SlayerTowerZone} {@code Object}.
	 */
	public SlayerTowerZone() {
		super("slayer tower", true, ZoneRestriction.CANNON);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player player = (Player) e;
			int level = player.getLocation().getZ() == 0 ? 61 : 71;
			if (target.getId() == 9319 && e.getSkills().getLevel(Skills.AGILITY) < level) {
				player.getPacketDispatch().sendMessage("You need an Agility level of at least " + level + " in order to do this.");
				return true;
			}
			if (target.getId() == 10527 || target.getId() == 10528) {
				DoorActionHandler.handleAutowalkDoor(player, (GameObject) target);
				return true;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		register(new ZoneBorders(3401, 3527, 3459, 3585));
	}

}
