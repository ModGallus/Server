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
public final class MysteryBoxElitePlugin extends OptionHandler {

	/**
     * The rewards received from a mystery box.
     */
    private static final ChanceItem[] REWARDS = new ChanceItem[] { 
	
    	
    	new ChanceItem(11257, 2, 10, DropFrequency.COMMON),//dragon impling jar
    	new ChanceItem(4740, 1, 1500, DropFrequency.COMMON),//bolt racks
    	new ChanceItem(985, 1, 1, DropFrequency.COMMON),//half key
    	new ChanceItem(987, 1, 1, DropFrequency.COMMON),//half key
    	new ChanceItem(12158, 10, 18, DropFrequency.COMMON),//green charm
    	new ChanceItem(12160, 10, 18, DropFrequency.COMMON),//crimson charm
    	new ChanceItem(565, 100, 120, DropFrequency.COMMON),//blood rune
    	new ChanceItem(5302, 9, 25, DropFrequency.COMMON),//lantadyme seed
    	new ChanceItem(5300, 6, 20, DropFrequency.COMMON),//snap seed
    	new ChanceItem(390, 18, 260, DropFrequency.COMMON),//raw manta ray
    	new ChanceItem(396, 20, 280, DropFrequency.COMMON),//raw sea turtle
    	new ChanceItem(5297, 4, 30, DropFrequency.COMMON),//irit seed
    	new ChanceItem(12183, 2000, 9999, DropFrequency.COMMON),//spirit shard
    	new ChanceItem(11255, 1, 5, DropFrequency.COMMON),//ninja impling jar
    	new ChanceItem(2358, 15, 280, DropFrequency.COMMON),//noted gold bar
    	new ChanceItem(450, 16, 270, DropFrequency.COMMON),//addy ore
    	new ChanceItem(8781, 48, 750, DropFrequency.COMMON),//teak plank
    	new ChanceItem(8427, 1, 1, DropFrequency.COMMON),//bagged yew tree
    	new ChanceItem(6688, 8, 170, DropFrequency.COMMON),//sara brew(3)
    	
    	new ChanceItem(4153, 1, 1, DropFrequency.UNCOMMON),//granite maul
    	new ChanceItem(990, 1, 5, DropFrequency.UNCOMMON),//full key
    	new ChanceItem(1333, 1, 1, DropFrequency.UNCOMMON),//rune scimitar
    	new ChanceItem(9194, 9, 180, DropFrequency.UNCOMMON),//onyx bolt tips
    	new ChanceItem(6809, 1, 1, DropFrequency.UNCOMMON),//granite legs
    	new ChanceItem(3122, 1, 1, DropFrequency.UNCOMMON),//granite shield
    	new ChanceItem(4131, 1, 1, DropFrequency.UNCOMMON),//rune booties 
    	new ChanceItem(1359, 1, 1, DropFrequency.UNCOMMON),//rune axe
    	new ChanceItem(2364, 9, 275, DropFrequency.UNCOMMON),//rune bar
    	new ChanceItem(12922, 1, 1, DropFrequency.UNCOMMON),//rune spikeshield
    	new ChanceItem(12929, 1, 1, DropFrequency.UNCOMMON),//rune berserker shield
    	new ChanceItem(1149, 1, 1, DropFrequency.UNCOMMON),//dragon med helm
    	new ChanceItem(1319, 1, 1, DropFrequency.UNCOMMON),//rune 2h
    	new ChanceItem(5315, 1, 2, DropFrequency.UNCOMMON),//yew seed
    	new ChanceItem(8787, 1, 10, DropFrequency.UNCOMMON),//marble block
    	new ChanceItem(12163, 12, 21, DropFrequency.UNCOMMON),//blue charm
    	new ChanceItem(8429, 1, 1, DropFrequency.UNCOMMON),//bagged magic tree
    	new ChanceItem(1631, 1, 1, DropFrequency.UNCOMMON),//uncut dstone 
    	new ChanceItem(1127, 1, 1, DropFrequency.UNCOMMON),//rune pl8
    	
    	new ChanceItem(5316, 1, 5, DropFrequency.RARE),//magic seed
    	new ChanceItem(2, 1, 250, DropFrequency.RARE),//whip
    	new ChanceItem(6, 1, 1, DropFrequency.RARE),//ranger boots
    	new ChanceItem(8, 1, 1, DropFrequency.RARE),//ahrim hood
    	new ChanceItem(10, 1, 1, DropFrequency.RARE),//ahrim staff
    	new ChanceItem(11, 1, 1, DropFrequency.RARE),//ahrim top
    	new ChanceItem(6572, 1, 1, DropFrequency.RARE),//noted onyx
    	new ChanceItem(14807, 1, 1, DropFrequency.RARE),//bond
    	new ChanceItem(14782, 1, 10, DropFrequency.RARE),//scmbp
    	new ChanceItem(14600, 1, 1, DropFrequency.RARE),//santa costume
    	new ChanceItem(14602, 1, 1, DropFrequency.RARE),
    	new ChanceItem(14603, 1, 1, DropFrequency.RARE),
    	new ChanceItem(14605, 1, 1, DropFrequency.RARE),
    	
	};

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(15081).getConfigurations().put("option:open", this);
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
