package plugin.random;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.npc.drop.DropFrequency;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.ChanceItem;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.repository.Repository;
import org.areillan.plugin.Plugin;
import org.areillan.tools.RandomFunction;
import org.areillan.tools.StringUtils;

/**
 * Handles the mystery box item.
 * @author Life
 */
public final class MysteryBoxPlugin extends OptionHandler {

	/**
     * The rewards received from a mystery box.
     */
    private static final ChanceItem[] REWARDS = new ChanceItem[] { 
	
    	new ChanceItem(1322, 1, 1, DropFrequency.COMMON),//bronzescim
    	new ChanceItem(563, 10, 15, DropFrequency.COMMON),//lawrunes
    	new ChanceItem(52, 20, 250, DropFrequency.COMMON),//arrowshaft
    	new ChanceItem(995, 500, 15000, DropFrequency.COMMON),//coinz
    	new ChanceItem(1624, 1, 7, DropFrequency.COMMON),//Uncutsapphire
    	new ChanceItem(1620, 1, 5, DropFrequency.COMMON),//uncutruby
    	new ChanceItem(230, 1, 20, DropFrequency.COMMON),//vial
    	new ChanceItem(7937, 76, 150, DropFrequency.COMMON),//noted pure ess
    	new ChanceItem(232, 26, 50, DropFrequency.COMMON),//snape grass noted
    	new ChanceItem(8779, 27, 35, DropFrequency.COMMON),//oak planks
    	new ChanceItem(10476, 15, 25, DropFrequency.COMMON),//purple sweets
    	new ChanceItem(437, 5, 33, DropFrequency.COMMON),//copperore
    	new ChanceItem(336, 5, 30, DropFrequency.COMMON),//rawtrout
    	new ChanceItem(1520, 1, 50, DropFrequency.COMMON),//willowlogs
    	new ChanceItem(884, 1, 50, DropFrequency.COMMON),//ironarrow
    	new ChanceItem(2350, 1, 16, DropFrequency.COMMON),//bronzebar
    	new ChanceItem(200, 13, 26, DropFrequency.COMMON),//grimyguam
    	new ChanceItem(1512, 1, 55, DropFrequency.COMMON),//logs
    	new ChanceItem(882, 1, 200, DropFrequency.COMMON),//bronzeararrow
    	new ChanceItem(1074, 1, 1, DropFrequency.COMMON),//addylegs
    	new ChanceItem(863, 1, 50, DropFrequency.COMMON),//ironknife
    	new ChanceItem(39, 1, 100, DropFrequency.COMMON),//bronzetips
    	
    	new ChanceItem(985, 1, 1, DropFrequency.UNCOMMON),//half key
    	new ChanceItem(987, 1, 1, DropFrequency.UNCOMMON),//half key
    	new ChanceItem(12158, 10, 18, DropFrequency.UNCOMMON),//green charm
    	new ChanceItem(12160, 10, 18, DropFrequency.UNCOMMON),//crimson charm
    	new ChanceItem(565, 100, 120, DropFrequency.UNCOMMON),//blood rune
    	new ChanceItem(5302, 9, 18, DropFrequency.UNCOMMON),//lantadyme seed
    	new ChanceItem(5300, 6, 12, DropFrequency.UNCOMMON),//snap seed
    	new ChanceItem(390, 18, 26, DropFrequency.UNCOMMON),//raw manta ray
    	new ChanceItem(396, 20, 28, DropFrequency.UNCOMMON),//raw sea turtle
    	new ChanceItem(5297, 4, 11, DropFrequency.UNCOMMON),//irit seed
    	new ChanceItem(12183, 2000, 9999, DropFrequency.UNCOMMON),//spirit shard
    	new ChanceItem(11255, 1, 3, DropFrequency.UNCOMMON),//ninja impling jar
    	new ChanceItem(2358, 15, 28, DropFrequency.UNCOMMON),//noted gold bar
    	new ChanceItem(450, 16, 27, DropFrequency.UNCOMMON),//addy ore
    	new ChanceItem(8781, 48, 75, DropFrequency.UNCOMMON),//teak plank
    	new ChanceItem(8427, 1, 1, DropFrequency.UNCOMMON),//bagged yew tree
    	new ChanceItem(6688, 8, 17, DropFrequency.UNCOMMON),//sara brew(3)
    	
    	new ChanceItem(4153, 1, 1, DropFrequency.RARE),//granite maul
    	new ChanceItem(989, 1, 1, DropFrequency.RARE),//full key
    	new ChanceItem(1333, 1, 1, DropFrequency.RARE),//rune scimitar
    	new ChanceItem(9194, 9, 18, DropFrequency.RARE),//onyx bolt tips
    	new ChanceItem(6809, 1, 1, DropFrequency.RARE),//granite legs
    	new ChanceItem(3122, 1, 1, DropFrequency.RARE),//granite shield
    	new ChanceItem(4131, 1, 1, DropFrequency.RARE),//rune booties 
    	new ChanceItem(1359, 1, 1, DropFrequency.RARE),//rune axe
    	new ChanceItem(2363, 9, 17, DropFrequency.RARE),//rune bar
    	new ChanceItem(12922, 1, 1, DropFrequency.RARE),//rune spikeshield
    	new ChanceItem(12929, 1, 1, DropFrequency.RARE),//rune berserker shield
    	new ChanceItem(1149, 1, 1, DropFrequency.RARE),//dragon med helm
    	new ChanceItem(1319, 1, 1, DropFrequency.RARE),//rune 2h
    	new ChanceItem(5315, 1, 2, DropFrequency.RARE),//yew seed
    	new ChanceItem(8787, 1, 2, DropFrequency.RARE),//marble block
    	new ChanceItem(12163, 12, 21, DropFrequency.RARE),//blue charm
    	new ChanceItem(8429, 1, 1, DropFrequency.RARE),//bagged magic tree
    	new ChanceItem(1631, 1, 1, DropFrequency.RARE),//uncut dstone 
    	new ChanceItem(1127, 1, 1, DropFrequency.RARE),//rune pl8
    	
    	new ChanceItem(5316, 1, 2, DropFrequency.VERY_RARE),//magic seed
    	new ChanceItem(2366, 1, 1, DropFrequency.VERY_RARE),//shield left half
    	new ChanceItem(1305, 1, 1, DropFrequency.VERY_RARE),//dragon longsword
    	new ChanceItem(3204, 1, 1, DropFrequency.VERY_RARE),//dragon halberd
    	new ChanceItem(10394, 1, 1, DropFrequency.VERY_RARE),//flared trousers
    	new ChanceItem(11257, 2, 4, DropFrequency.VERY_RARE),//dragon impling jar
    	new ChanceItem(10508, 1, 1, DropFrequency.VERY_RARE),//wintumber tree
    	new ChanceItem(11902, 1, 1, DropFrequency.VERY_RARE),//enchanted robe set
    	new ChanceItem(13107, 1, 1, DropFrequency.VERY_RARE),//sheep mask
    	new ChanceItem(10398, 1, 1, DropFrequency.VERY_RARE),//sleeping cap
    	new ChanceItem(2577, 1, 1, DropFrequency.VERY_RARE),//ranger boots
	
	};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(6199).getConfigurations().put("option:open", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		final ChanceItem item = RandomFunction.getChanceItem(REWARDS);
		final String name = item.getName().toLowerCase();
		final Item box = (Item) node;
		if (player.getInventory().remove(box, box.getSlot(), true)) {
			player.lock(1);
			player.getInventory().add(new Item(item.getId(), RandomFunction.random(item.getMinimumAmount(), item.getMaximumAmount())));
			player.sendMessage("Inside the box you find " + (item.getId() == 995 ? "some" : (StringUtils.isPlusN(name) ? "an" : "a")) + " " + name + "!");
			if(item.getChanceRate() <= 10){
				Repository.sendNews(player.getUsername()+" has just recieved "+item.getAmount()+" x "+item.getName()+" from a Mystery box.");
			}
		}
		return true;
	}
	

	@Override
	public boolean isWalk() {
		return false;
	}

}
