package plugin.interaction.item;

import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.portal.Perks;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.node.item.Item;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.zone.impl.WildernessZone;
import org.areillan.plugin.Plugin;

/**
 * Represents the plugin used to handle the elf tele crystal
 * @author Splinter
 * @version 1.0
 */
public class TeleportCrystalPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ItemDefinition.forId(6099).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6100).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6101).getConfigurations().put("option:activate", this);
		ItemDefinition.forId(6102).getConfigurations().put("option:activate", this);
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		if (!WildernessZone.checkTeleport(player, 20)) {
			player.getPacketDispatch().sendMessage("The crystal is unresponsive.");
			return true;
		}
		player.getTeleporter().send(new Location(2329, 3172), TeleportType.NORMAL);
		degrade(player, node.asItem());
		return true;
	}

	/*
	 * Degrades the crystal from use.
	 * @param p 	the player
	 * @param item	the crystal used
	 */
	private void degrade(Player p, Item item) {
		if (p.hasPerk(Perks.UNBREAKABLE_CRYSTAL)) {
			return;
		}
		int id = item.getId();
		int newItem = item.getId() + 1;
		if (id < 6102) {
			p.getInventory().remove(new Item(id, 1));
			p.getInventory().add(new Item(newItem, 1));
			p.getPacketDispatch().sendMessage("Your teleportation crystal has degraded from use.");
		} else {
			p.getInventory().remove(new Item(id, 1));
			p.getInventory().add(new Item(6103, 1));
			p.getPacketDispatch().sendMessage("Your teleportation crystal has turned into a small seed.");
		}
	}

	@Override
	public boolean isWalk() {
		return false;
	}

}
