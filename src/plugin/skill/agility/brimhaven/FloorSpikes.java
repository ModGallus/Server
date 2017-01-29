package plugin.skill.agility.brimhaven;

import org.areillan.game.content.skill.Skills;
import org.areillan.game.content.skill.member.agility.AgilityHandler;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.task.LocationLogoutTask;
import org.areillan.game.system.task.LogoutTask;
import org.areillan.game.system.task.MovementHook;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Direction;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.update.flag.context.Animation;

/**
 * Handles floor spikes trap.
 * @author Emperor
 */
public final class FloorSpikes implements MovementHook {

	@Override
	public boolean handle(Entity e, final Location l) {
		final Direction dir = e.getDirection();
		final Player player = (Player) e;
		final Location start = l.transform(-dir.getStepX(), -dir.getStepY(), 0);
		e.lock(5);
		e.addExtension(LogoutTask.class, new LocationLogoutTask(5, start));
		GameWorld.submit(new Pulse(3, e) {
			@Override
			public boolean pulse() {
				player.getPacketDispatch().sendObjectAnimation(RegionManager.getObject(l), Animation.create(1111));
				if (AgilityHandler.hasFailed(player, 20, 0.25)) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 20) {
						player.getPacketDispatch().sendMessage("You need an agility of at least 20 to get past this trap!");
					}
					int hit = player.getSkills().getLifepoints() / 12;
					if (hit < 2) {
						hit = 2;
					}
					AgilityHandler.failWalk(player, 1, player.getLocation(), start, start, Animation.create(1114), 10, hit, "You were hit by some floor spikes!").setDirection(dir);
					;
				} else {
					AgilityHandler.forceWalk(player, -1, l, l.transform(dir.getStepX() << 1, dir.getStepY() << 1, 0), Animation.create(1115), 20, 26, null);
				}
				return true;
			}
		});
		return false;
	}

}