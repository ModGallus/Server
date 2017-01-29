package plugin.skill.crafting;

import org.areillan.game.content.dialogue.SkillDialogueHandler;
import org.areillan.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.areillan.game.content.skill.free.crafting.gem.GemCutPulse;
import org.areillan.game.content.skill.free.crafting.gem.Gems;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to cut a gem.
 * @author 'Vexia
 * @version 1.0
 */
public final class GemCutPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code GemCutPlugin} {@Code Object}
	 */
	public GemCutPlugin() {
		super(1623, 1621, 1619, 1617, 1631, 6571, 15107, 1625, 1627, 1629, 6571, 15110, 15106);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(1755, ITEM_TYPE, this);
		return null;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Gems gem = Gems.forId(event.getUsedItem().getId() == 1755 ? event.getBaseItem() : event.getUsedItem());
		SkillDialogueHandler handler = new SkillDialogueHandler(player, SkillDialogue.ONE_OPTION, gem.getGem()) {
			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new GemCutPulse(player, gem.getUncut(), amount, gem));
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(gem.getUncut());
			}

		};
		if (player.getInventory().getAmount(gem.getUncut()) == 1) {
			handler.create(0, 1);
		} else {
			handler.open();
		}
		return true;
	}

}
