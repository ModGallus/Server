package plugin.skill.slayer.dungeon;

import org.areillan.game.content.skill.Skills;
import org.areillan.game.content.skill.member.agility.AgilityHandler;
import org.areillan.game.interaction.Option;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Direction;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneBorders;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;

/**
 * Represents the femennik dungeon zone.
 * @author Vexia
 */
public final class FremennikDungeon extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code FremennikDungeon} {@code Object}.
	 */
	public FremennikDungeon() {
		super("fremennik", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		return this;
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return true;
	}

	@Override
	public boolean interact(Entity entity, Node target, Option option) {
		if (entity instanceof Player && target instanceof GameObject) {
			final Player player = (Player) entity;
			final GameObject object = (GameObject) target;
			final Direction dir = Direction.getLogicalDirection(player.getLocation(), object.getLocation());
			switch (target.getId()) {
			case 9326:// pyrefiend area.
				if (player.getSkills().getLevel(Skills.AGILITY) < 81) {
					player.getPacketDispatch().sendMessage("You need an Agility level of at least 81 to do this.");
					return true;
				}
				player.lock();
				GameWorld.submit(new Pulse(1, player) {
					int count;

					@Override
					public boolean pulse() {
						switch (++count) {
						case 1:
							Location start = object.getLocation().transform(dir.getOpposite(), 2);
							player.getWalkingQueue().reset();
							player.getWalkingQueue().addPath(start.getX(), start.getY());
							break;
						case 2:
							player.faceLocation(object.getLocation());
							break;
						case 3:
							Location end = object.getLocation().transform(dir, 1);
							AgilityHandler.forceWalk(player, -1, player.getLocation(), end, Animation.create(1995), 20, 0.0, null);
							break;
						case 4:
							final boolean fail = AgilityHandler.hasFailed(player, 20, 0.1);
							if (fail) {
								player.getImpactHandler().manualHit(player, RandomFunction.random(6), HitsplatType.NORMAL);
								player.getPacketDispatch().sendMessage("You trigger the trap as you jump over it.");
							}
							player.animate(Animation.create(1603));
							player.unlock();
							return true;
						}
						return false;
					}
				});
				return true;
			case 9321:
				if (player.getSkills().getLevel(Skills.AGILITY) < 62) {
					player.getPacketDispatch().sendMessage("You need an Agility level of at least 62 to do this.");
					return true;
				}
				Location end = object.getLocation().transform(dir, 4);
				AgilityHandler.walk(player, -1, player.getLocation(), end, Animation.create(156), 10, "You climb your way through the narrow crevice.");
				return true;
			}
		}
		return super.interact(entity, target, option);
	}

	@Override
	public void configure() {
		register(new ZoneBorders(2671, 9950, 2813, 10054));
	}

}
