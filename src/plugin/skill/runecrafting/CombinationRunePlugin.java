package plugin.skill.runecrafting;

import org.areillan.game.content.skill.free.runecrafting.Altar;
import org.areillan.game.content.skill.free.runecrafting.CombinationRune;
import org.areillan.game.content.skill.free.runecrafting.Rune;
import org.areillan.game.content.skill.free.runecrafting.RuneCraftPulse;
import org.areillan.game.content.skill.free.runecrafting.Talisman;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.plugin.Plugin;

/**
 * Handles the combination runes.
 * @author Vexia
 */
public final class CombinationRunePlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code CombinationRunePlugin} {@code Object}.
	 */
	public CombinationRunePlugin() {
		super(Talisman.AIR.getTalisman().getId(), Talisman.WATER.getTalisman().getId(), Talisman.EARTH.getTalisman().getId(), Talisman.FIRE.getTalisman().getId(), Rune.WATER.getRune().getId(), Rune.EARTH.getRune().getId(), Rune.AIR.getRune().getId(), Rune.FIRE.getRune().getId());
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
		final Player player = event.getPlayer();
		final Altar altar = Altar.forObject(((GameObject) event.getUsedWith()));
		final CombinationRune combo = CombinationRune.forAltar(altar, event.getUsedItem());
		if (combo == null) {
			return false;
		}
		player.getPulseManager().run(new RuneCraftPulse(player, event.getUsedItem(), altar, true, combo));
		return true;
	}

}
