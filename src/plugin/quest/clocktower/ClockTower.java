package plugin.quest.clocktower;

import org.areillan.game.content.skill.Skills;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.quest.Quest;
import org.areillan.game.node.item.GroundItemManager;
import org.areillan.game.node.item.Item;
import org.areillan.plugin.PluginManager;

import plugin.quest.clocktower.BrotherKojoDialogue;
import plugin.quest.clocktower.ClocktowerPlugin;

/**
 * Represents the clocktower ghost quest.
 * @author Life
 * 
 */
public class ClockTower extends Quest {
	
	/**
	 * Construction junk
	 */
	private static final Item EAGLE_CAPE = new Item(10171);
	
	/**
	 * Constructs a new {@Code ClockTower} {@Code Object}
	 */
	public ClockTower() {
		super("ClockTower", 25, 24, 1, 107, 0, 4, 5);
	}	
	@Override
	public Quest newInstance(Object object) {
		PluginManager.definePlugin(new ClocktowerPlugin(), new BrotherKojoDialogue());
		return this;
	}
	@Override
	public void drawJournal(Player player, int stage) {
		super.drawJournal(player, stage);
		if (stage == 0) {
			player.getPacketDispatch().sendString("<col=08088A>I can start this quest by speaking to <col=8A0808>Brother Kojo</col> <col=08088A>at the", 275, 4+ 7);
			player.getPacketDispatch().sendString("<col=8A0808>clocktower, southwest</col> <col=08088A> of <col=8A0808>Ardougne Zoo.<col=8A0808>", 275, 5+ 7);
			player.getPacketDispatch().sendString("<col=08088A>I must be be able to survive <col=8A0808>Level 53 Ogres ", 275, 6+ 7);
		}
		if (stage == 10) {// 1/5 done
		
		}
		if (stage == 20) {// 2/5 done
		
		}
		if (stage == 30) {// 3/5 done
			
		} 
		if (stage == 40) {// 4/5 done
			
		}
		if (stage == 100) {// 5/5 done
			
		}
	}

	@Override
	public void finish(Player player) {
		super.finish(player);
		player.getPacketDispatch().sendString("1 Quest Point", 277, 8 + 2);
		player.getPacketDispatch().sendString("5,000 Coins", 277, 9 + 2);
		if (!player.getInventory().add(EAGLE_CAPE)) {
			GroundItemManager.create(EAGLE_CAPE, player);
		}
		player.getPacketDispatch().sendString("200 Construction EXP", 277, 10 + 2);
		player.getPacketDispatch().sendItemZoomOnInterface(10983, 240, 277, 3 + 2);
		player.getSkills().addExperience(Skills.CONSTRUCTION, 200);
		player.getInventory().add(new Item(995, 5000));// 5k coins
		player.getInterfaceManager().closeChatbox();
		player.getPacketDispatch().sendString("You have completed The Clock Tower Quest!", 277, 2 + 2);
		player.getConfigManager().set(728, 31, true);
		player.getPacketDispatch().sendMessage("Congratulations! Quest complete!");
		player.getGameAttributes().removeAttribute("clock-tower:kojo");
	
	}

}
