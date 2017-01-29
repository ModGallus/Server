package plugin.skill.agility.shortcuts;

import org.areillan.game.content.skill.member.agility.AgilityShortcut;
import org.areillan.game.node.entity.impl.ForceMovement;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.update.flag.context.Animation;

/**
 * Handles the stepping stone shortcut.
 * @author Vexia
 */
public class SteppingStoneShortcut extends AgilityShortcut {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(741);

	/**
	 * Constructs a new {@Code SteppingStoneShortcut} {@Code
	 * Object}
	 */
	public SteppingStoneShortcut() {
		super(new int[] { 9315 }, 1, 0.0, "jump-onto");
	}

	@Override
	public void run(final Player player, GameObject object, String option, boolean failed) {
		final int offset = player.getLocation().getX() == 3149 ? 1 : -1;
		player.lock();
		GameWorld.submit(new Pulse(2, player) {
			int counter = 1;

			@Override
			public boolean pulse() {
				if (counter == 6) {
					player.unlock();
					return true;
				}
				ForceMovement.run(player, player.getLocation(), player.getLocation().transform(offset, 0, 0), ANIMATION, 10);
				counter++;
				return false;
			}
		});
	}

}
