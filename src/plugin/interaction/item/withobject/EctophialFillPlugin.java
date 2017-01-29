package plugin.interaction.item.withobject;

import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.plugin.Plugin;

/**
 * Fills an ectophial.
 * @author Vexia
 */
public class EctophialFillPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code EctophialFillPlugin} {@code Object}
	 */
	public EctophialFillPlugin() {
		super(4252);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(5282, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		player.lock(3);
		player.animate(Animation.create(1652));
		GameWorld.submit(new Pulse(3, player) {
			@Override
			public boolean pulse() {
				if (player.getInventory().remove(new Item(4252))) {
					player.getInventory().add(new Item(4251));
				}
				player.sendMessage("You refill the ectophial.");
				return true;
			}
		});
		return true;
	}

}
