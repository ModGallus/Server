package plugin.command;

import org.areillan.cache.Cache;
import org.areillan.cache.def.impl.ItemDefinition;
import org.areillan.game.content.eco.ge.GrandExchangeDatabase;
import org.areillan.game.content.eco.ge.GrandExchangeEntry;
import org.areillan.game.content.eco.ge.ResourceManager;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.item.Item;
import org.areillan.game.system.SystemLogger;
import org.areillan.game.system.SystemManager;
import org.areillan.game.system.SystemState;
import org.areillan.game.system.command.CommandPlugin;
import org.areillan.game.system.command.CommandSet;
import org.areillan.game.system.mysql.impl.ItemConfigSQLHandler;
import org.areillan.game.world.repository.Repository;
import org.areillan.gui.AriosFrame;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.plugin.Plugin;

/**
 * Represents commands related to system updating.
 * @author Vexia
 */
public final class SystemCommand extends CommandPlugin {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		link(CommandSet.DEVELOPER);
		return this;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean parse(Player player, String name, String[] args) {
		switch (name) {
		case "setvalue":
			int itemId = toInteger(args[1]);
			int value = toInteger(args[2]);
			Item item = new Item(itemId);
			GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(itemId);
			if (entry == null) {
				player.getPacketDispatch().sendMessage("Could not find G.E entry for item [id=" + itemId + ", name=" + item.getName() + "]!");
				break;
			}
			entry.setValue(value);
			player.getPacketDispatch().sendMessage("Set Grand Exchange value for item [id=" + itemId + ", name=" + item.getName() + "] to " + value + "gp!");
			return true;
		case "reloadconfig":
		case "configreload":
			SystemManager.getSystemConfig().parse();
			SystemLogger.log("System configurations reloaded.");
			return true;
		case "cms":// Re-connects to the management server.
			for (Player pl : Repository.getPlayers()) {
				try {
					pl.removeAttribute("combat-time");
					pl.getPacketDispatch().sendLogout();
					pl.clear();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			WorldCommunicator.terminate();
			WorldCommunicator.connect();
			return true;
		case "showgui":
			AriosFrame.INSTANCE.setVisible(true);
			return true;
		case "update":
			if (args.length > 1) {
				SystemManager.getUpdater().setCountdown(Integer.parseInt(args[1]));
			}
			SystemManager.flag(SystemState.UPDATING);
			return true;
		case "cancel_update":
		case "cancelupdate":
		case "cancel":
			SystemManager.getUpdater().cancel();
			return true;
		case "clear_resource":
			ResourceManager.clearResource(toInteger(args[1]));
			System.out.println("Cleared resource " + args[1] + "!");
			return true;
		case "add_resource":
			boolean sell = !(args.length > 3 && args[3].equals("false"));
			ResourceManager.addResource(toInteger(args[1]), toInteger(args[2]), sell);
			System.out.println("Added " + (sell ? "selling" : "buying") + " resource " + args[1] + ", " + args[2] + "!");
			return true;
		case "kseco":
			ResourceManager.kickStartEconomy();
			return true;
		case "resetrm":
			ResourceManager.getStock().clear();
			System.out.println("Fully reset resource manager!");
			return true;
		/*case "setdefaultge":
			int changes = 0;
			for (int id = 0; id < Cache.getItemDefinitionsSize(); id++) {
				GrandExchangeEntry entry = GrandExchangeDatabase.getDatabase().get(id);
				if (entry == null) {
					continue;
				}
				ItemDefinition def = ItemDefinition.forId(id);
				int value = entry.getValue();
				if (value < def.getAlchemyValue(true)) {
					value = def.getAlchemyValue(true);
				}
				if (value < def.getAlchemyValue(false)) {
					value = def.getAlchemyValue(false);
				}
				if (value < def.getValue()) {
					value = def.getValue();
				}
				if (value < def.getConfiguration(ItemConfigSQLHandler.GE_PRICE, 0)) {
					value = def.getConfiguration(ItemConfigSQLHandler.GE_PRICE, 0);
				}
				if (value < def.getConfiguration(ItemConfigSQLHandler.SHOP_PRICE, 0)) {
					value = def.getConfiguration(ItemConfigSQLHandler.SHOP_PRICE, 0);
				}
				if (value != entry.getValue()) {
					changes++;
				}
				entry.setValue(value);
			}
			player.getPacketDispatch().sendMessage("Set default G.E prices - " + changes + " changes made!");
			return true;*/
		case "sgc":
		case "systemgc":
			System.gc();
			return true;
		}
		return false;
	}

}
