package plugin.command;

import org.areillan.game.content.skill.member.slayer.Master;
import org.areillan.game.content.skill.member.slayer.Task;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.node.entity.player.info.portal.Perks;
import org.areillan.game.node.item.Item;
import org.areillan.game.system.command.CommandPlugin;
import org.areillan.game.system.command.CommandSet;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;

/**
 * Handles the perk related commands.
 * @author Vexia
 */
public class PerkCommandPlugin extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.PLAYER);
		return this;
	}

	@Override
	public boolean parse(Player player, String name, String[] args) {
		switch (name) {
		case "bonecrusher":
		case "bone":
		case "enablebonecrusher":
		case "enablebone":
		case "bc":
			if (!player.hasPerk(Perks.BONECRUSHER) && !player.isAdmin()) {
				player.sendMessage("You do not own the bonecrusher perk.");
				return false;
			}
			boolean bone = player.getGlobalData().isEnableBoneCrusher();
			player.getGlobalData().setEnableBoneCrusher(bone ? false : true);
			player.sendMessage("You have " + (bone ? "disabled" : "enabled") + " your bonecrusher perk.");
			return true;
		case "coinmachine":
		case "cm":
			if (!player.hasPerk(Perks.COIN_MACHINE) && !player.isAdmin()) {
				player.sendMessage("You do not own the coin machine perk.");
				return false;
			}
			boolean coin = player.getGlobalData().isEnableCoinMachine();
			player.getGlobalData().setEnableCoinMachine(coin ? false : true);
			player.sendMessage("You have " + (coin ? "disabled" : "enabled") + " your coin machine perk.");
			return true;
		case "charmcollector":
		case "charm":
			if (!player.hasPerk(Perks.CHARM_COLLECTOR) && !player.isAdmin()) {
				player.sendMessage("You do not own the charm collector perk.");
				return false;
			}
			boolean charm = player.getGlobalData().isEnableCoinMachine();
			player.getGlobalData().setEnableCharmCollector(charm ? false : true);
			player.sendMessage("You have " + (charm? "disabled" : "enabled") + " your charm collector perk.");
			return true;
		case "swap":
		case "spellswap":
			if (!player.hasPerk(Perks.SPELL_SWAP) && player.getDetails().getRights() != Rights.ADMINISTRATOR) {
				player.sendMessage("You don't have the spell swap perk.");
				return false;
			}
			if (player.inCombat() || player.getLocks().isInteractionLocked() || player.getSkullManager().isWilderness() || player.getAttribute("activity", null) != null) {
				player.getPacketDispatch().sendMessage("You can't do that right now.");
				return true;
			}
			player.animate(new Animation(6299));
			player.graphics(new Graphics(1062));
			player.getDialogueInterpreter().open(3264731, true, true);
			return true;
		case "resettask":
		case "cleartask":
		case "ct":
		case "rt":
			if (!player.getSlayer().hasTask()) {
				player.sendMessage("You don't have a slayer task!");
				return true;
			}
			if (player.getSlayer().getMaster() == Master.WISE_OLD_MAN) {
				player.sendMessage("You can't do this with tasks from the Wise Old Man.");
				return true;
			}
			Task task = player.getSlayer().getTask();
			if (task.isDisabled() || player.getDetails().getShop().hasPerk(Perks.SLAYER_BETRAYER)) {
				player.sendMessage("You have reset your task.");
				player.getSlayer().clear();		
				return true;
			} 
			else 
			{
				
				player.sendMessage("You have reset your task.");
				player.getSlayer().clear();	
				return true;
			}
		}
		return false;
	}

}
