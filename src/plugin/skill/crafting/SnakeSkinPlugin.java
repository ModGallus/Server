package plugin.skill.crafting;

import org.areillan.game.content.dialogue.SkillDialogueHandler;
import org.areillan.game.content.dialogue.SkillDialogueHandler.SkillDialogue;
import org.areillan.game.content.skill.free.crafting.armour.SnakeSkin;
import org.areillan.game.content.skill.free.crafting.armour.SnakeSkinPulse;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.Plugin;

/**
 * Handles snake skin plugin.
 * @author Vexia
 */
public class SnakeSkinPlugin extends UseWithHandler {

	/**
	 * Constructs a new {@Code SnakeSkinPlugin} {@Code Object}
	 */
	public SnakeSkinPlugin() {
		super(1733);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(6289, ITEM_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(final NodeUsageEvent event) {
		final Player player = event.getPlayer();
		new SkillDialogueHandler(player, SkillDialogue.FIVE_OPTION, (Object[]) getSkins()) {

			@Override
			public void create(final int amount, int index) {
				player.getPulseManager().run(new SnakeSkinPulse(player, event.getUsedItem(), amount, SnakeSkin.values()[index]));
			}

			@Override
			public int getAll(int index) {
				return player.getInventory().getAmount(new Item(6289));
			}

			@Override
			public String getName(Item item) {
				return item.getId() == 6328 ? "Boots" : item.getId() == 6330 ? "Vambs" : item.getId() == 6326 ? "Bandana" : item.getId() == 6324 ? "Chaps" : "Body";
			}

		}.open();
		return true;
	}

	/**
	 * Gets the skins.
	 * @return the item.
	 */
	public Item[] getSkins() {
		Item[] items = new Item[SnakeSkin.values().length];
		for (int i = 0; i < items.length; i++) {
			items[i] = SnakeSkin.values()[i].getProduct();
		}
		return items;
	}

}
