package plugin.activity.barrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.areillan.game.content.global.BossKillCounter;
import org.areillan.game.node.entity.npc.drop.DropFrequency;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.ChanceItem;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.repository.Repository;
import org.areillan.tools.RandomFunction;

/**
 * The reward chest.
 * @author Emperor
 */
public final class RareReward {

	/**
	 * The low profit drop table.
	 */
	private static final ChanceItem[] DROP_TABLE = { new ChanceItem(1038, 1, 1, 1, 0.0, DropFrequency.VERY_RARE), new ChanceItem(962, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(981, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1959, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1961, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1990, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1057, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1055, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1053, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1048, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1046, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1044, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1042, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), new ChanceItem(1040, 1, 1, 1000, 0.0, DropFrequency.VERY_RARE,200), };

	/**
	 * Rewards the player.
	 * @param player The player.
	 */
	public static void reward(Player player) {
		int mod = 1;; //more players online =better chance of rare
		int totalKills = mod;
		if (mod > 80) { // doesnt let the value go above 80
			mod = 80;
		}
		for (int i = 0; i < 2 + RandomFunction.random(3); i++) {
			mod = RandomFunction.random(mod);
			ChanceItem reward = null;
			List<ChanceItem> list = Arrays.asList(DROP_TABLE);
			Collections.shuffle(list, new Random());
			for (ChanceItem item : list) {
				if (item.getId() > 4000 && player.getSavedData().getActivityData().getBarrowKills() <= 0) {
					continue;
				}
				if (reward == null && item.getDropFrequency() == DropFrequency.COMMON) {
					reward = item;
					continue;
				}
				double rarity = 200 << (1 + item.getDropFrequency().ordinal() << 1);
				if (RandomFunction.random((int) rarity) <= mod) {
					reward = item;
					break;
				}
			}
			Item item = null;
			if (reward.getMaximumAmount() > reward.getMinimumAmount()) {
				int amount = reward.getMinimumAmount();
				double mult = totalKills / 100;
				if (mult > 1.0) {
					mult = 1.0;
				}
				amount += RandomFunction.random((int) ((reward.getMaximumAmount() - reward.getMinimumAmount()) * mult));
				item = new Item(reward.getId(), amount);
			} else {
				item = new Item(reward.getId(), reward.getMinimumAmount());
			}
			player.getInventory().add(item, player);
			if (item.getDefinition().getName().contains("partyhat") || item.getDefinition().getName().contains("h'w") || item.getDefinition().getName().contains("Easter") || item.getDefinition().getName().contains("half") || item.getDefinition().getName().contains("pumpk") || item.getDefinition().getName().contains("cracker") || item.getDefinition().getName().contains("returning")) {
				Repository.sendNews(player.getUsername() + " has just received: " + item.getAmount() + " x " + item.getName() + " from the cash sink.");
			}
		}
	}

}

