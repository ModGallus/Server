package plugin.skill.runecrafting;

import org.areillan.game.content.skill.free.runecrafting.Altar;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Handles the tiara enchanting.
 * @author Vexia
 */
public final class EnchantTiaraPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code EnchantTiaraPlugin} {@code Object}.
	 */
	public EnchantTiaraPlugin() {
		super(1438, 1448, 1444, 1440, 1442, 5516, 1446, 1454, 1452, 1462, 1458, 1456, 1450, 1460, 5525);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Altar altar : Altar.values()) {
			addHandler(altar.getObject(), OBJECT_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		Player player = event.getPlayer();
		Altar altar = Altar.forObject(((GameObject) event.getUsedWith()));
		if (!player.getInventory().containsItem(altar.getTalisman().getTalisman())) {
			return false;
		}
		event.getPlayer().getDialogueInterpreter().open(8432482, event);
		return true;
	}

}
