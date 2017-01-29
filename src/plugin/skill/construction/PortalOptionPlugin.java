package plugin.skill.construction;

import org.areillan.cache.def.impl.ObjectDefinition;
import org.areillan.game.content.dialogue.DialogueInterpreter;
import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.content.skill.member.construction.HouseManager;
import org.areillan.game.interaction.OptionHandler;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.RunScript;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.world.repository.Repository;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;
import org.areillan.plugin.PluginManifest;
import org.areillan.plugin.PluginType;

/**
 * Handles the house portal options.
 * @author Emperor
 *
 */
public final class PortalOptionPlugin extends OptionHandler {

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (int objectId = 15477; objectId < 15483; objectId++) {
			ObjectDefinition.forId(objectId).getConfigurations().put("option:enter", this);
		}
		ObjectDefinition.forId(13405).getConfigurations().put("option:lock", this);
		ObjectDefinition.forId(13405).getConfigurations().put("option:enter", this);
		PluginManager.definePlugin(new PortalDialogue());
		return this;
	}

	@Override
	public boolean handle(Player player, Node node, String option) {
		GameObject object = node.asObject();
		if (object.getId() == 13405 && option.equals("enter")) {
			HouseManager.leave(player);
			return true;
		}
		if (option.equals("lock")) {
			if (player.getHouseManager().isLoaded()) {
				player.getHouseManager().setLocked(player.getHouseManager().isLocked() ? false : true);
				player.sendMessage("Your house is now "+(player.getHouseManager().isLocked() ? "locked." : "unlocked." ));
				return true;
			} else if (!player.getHouseManager().isLoaded()) {
				player.sendMessage("This is not your house.");
				return true;
			}
		}
		player.setAttribute("con:portal", object.getId());
		player.getDialogueInterpreter().open("con:portal");
		return true;
	}
	
	/**
	 * Handles the portal dialogue.
	 * @author Emperor
	 *
	 */
	@PluginManifest(type=PluginType.DIALOGUE)
	private class PortalDialogue extends DialoguePlugin {
		
		/**
		 * Constructs a new {@code PortalDialogue} {@code Object}
		 */
		public PortalDialogue() {
			
		}
		
		/**
		 * Constructs a new {@code PortalDialogue} {@code Object}
		 * @param player The player.
		 */
		public PortalDialogue(Player player) {
			super(player);
		}
		
		@Override
		public DialoguePlugin newInstance(Player player) {
			return new PortalDialogue(player);
		}

		@Override
		public boolean open(Object... args) {
			options("Go to your house", "Go to your house (building mode)", "Go to a friend's house", "Never mind");
			stage = 1;
			return true;
		}

		@Override
		public boolean handle(int interfaceId, int buttonId) {
			end();
			switch (stage) {
			case 1:
				switch (buttonId) {
				case 1:
				case 2:
					if (!player.getHouseManager().hasHouse()) {
						player.getPacketDispatch().sendMessage("You don't have a house, talk to an estate agent to purchase a house.");
						break;
					}
					if (player.getHouseManager().getLocation().getPortalId() != player.getAttribute("con:portal", -1)) {
						player.getPacketDispatch().sendMessage("Your house is in " + player.getHouseManager().getLocation().name().toLowerCase() + ".");
						break;
					}
					player.getHouseManager().enter(player, buttonId == 2);
					break;
				case 3:
					player.getDialogueInterpreter().sendInput(true, "Enter friend's name:");
					player.setAttribute("runscript", new RunScript() {
						@Override
						public boolean handle() {
							Player p = Repository.getPlayer((String) getValue());
							if (p == null || !p.isActive()) {
								player.getPacketDispatch().sendMessage("This player is not online.");
								return true;
							}
							if (p.getUsername().equals(player.getUsername())) {
								player.getPacketDispatch().sendMessage("You aren't a friend of yourself!");
								return true;
							}
							if (!p.getHouseManager().isLoaded()) {
								player.getPacketDispatch().sendMessage("This player is not at home right now.");
								return true;
							}
							if (p.getHouseManager().isBuildingMode()) {
								player.getPacketDispatch().sendMessage("This player is in building mode.");
								return true;
							}
							if (p.getHouseManager().isLocked()) {
								player.getPacketDispatch().sendMessage("The other player has locked their house.");
								return true;
							}
							p.getHouseManager().enter(player, false);
							return true;
						}
					});
					break;
				}
				break;
			}
			return true;
		}

		@Override
		public int[] getIds() {
			return new int[] { DialogueInterpreter.getDialogueKey("con:portal") };
		}
	}
}