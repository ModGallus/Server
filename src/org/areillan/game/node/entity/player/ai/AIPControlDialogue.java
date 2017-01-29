package org.areillan.game.node.entity.player.ai;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.InterfaceContext;
import org.areillan.net.packet.context.PlayerContext;
import org.areillan.net.packet.out.Interface;
import org.areillan.net.packet.out.LoginPacket;

/**
 * Handles artificial intelligent player controls.
 * @author Emperor
 */
public final class AIPControlDialogue extends DialoguePlugin {

	/**
	 * The AIP to control.
	 */
	private AIPlayer aip;

	/**
	 * Constructs a new {@code AIPControlDialogue} {@code Object}.
	 */
	public AIPControlDialogue() {
		/*
		 * empty.
		 */
	}

	/**
	 * Constructs a new {@code AIPControlDialogue} {@code Object}.
	 * @param player The player.
	 */
	public AIPControlDialogue(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new AIPControlDialogue(player);
	}

	@Override
	public boolean open(Object... args) {
		Player player2 = null;
		if (player.isArtificial()) {
			Player controller = ((AIPlayer)player).getControler();
			// Make sure that we are using become, otherwise we should treat it as aip.
			if ((Player)controller.getAttribute("aip_became") == player) {
				player2 = controller;
			}
		}
		if (player2 == null)
			player2 = player;
		// Make sure either the user is admin, or that it is going through an aip whos controller is an admin.
		// Even though if it is an aip it should have been an admin, but just in case a user happens to do this.
		if (player.getDetails().getRights() != Rights.ADMINISTRATOR && 
			!(player instanceof AIPlayer) && ((AIPlayer)player).isArtificial() && ((AIPlayer)player).getControler().getRights() != Rights.ADMINISTRATOR) {
			return false;
		}
		aip = (AIPlayer) args[0];
		String select = "Select";
		String select2 = "Become";
		if (player2.getAttribute("aip_became") == aip) {
			select = "Deselect";
			select2 = "Unbecome";
		} else if (player2.getAttribute("aip_select") == aip) {
			select = "Deselect";
		}
		interpreter.sendOptions("AIP#" + aip.getUid() + " controls", select, "Settings", "Follow", select2, "Clear");
		return true;
	}

	@Override
	public boolean handle(int interfaceId, int buttonId) {
		System.out.println("interface: "+interfaceId+" button: "+buttonId);
		Player player2 = null;
		if (player.isArtificial()) {
			Player controller = ((AIPlayer)player).getControler();
			// Make sure that we are using become, otherwise we should treat it as aip.
			if ((Player)controller.getAttribute("aip_became") == player) {
				player2 = controller;
			}
		}
		if (player2 == null)
			player2 = player;
		switch (interfaceId) {
		case 234:
			switch (buttonId) {
			case 1:
				if (player2.getAttribute("aip_select") == aip) {
					player2.removeAttribute("aip_select");
					break;
				}
				player2.setAttribute("aip_select", aip);
				break;
			case 2:
				interpreter.sendOptions("AIP#" + aip.getName() + " settings", "Toggle run", "Toggle retaliate", "Toggle special attack", "Swap");
				return true;
			case 3:
				aip.follow(player);
				player2.removeAttribute("aip_select");
				break;
			case 4:
				player.getInterfaceManager().closeChatbox();
				// Instead of swapping, actually control them as if you are them.
				if (player2.getAttribute("aip_select") == aip || player.getAttribute("aip_became") == aip) {
					player2.removeAttribute("aip_select");
					player2.removeAttribute("aip_became");
				} else {
					player2.setAttribute("aip_select", aip);
					player2.setAttribute("aip_became", aip);
					// Send the login packet so they can use the new uid...
					//PacketRepository.send(LoginPacket.class, new PlayerContext((Player)aip));
				}
				
				// Refresh everything.
				// Wondering why there is three? player is the player/aip proxy, player2 is always player, aip is always target ai player.
				Player[] playerTask = {player, player2, aip};
				Player clearer;
				for(int i=0; i<playerTask.length; i++) {
					clearer = playerTask[i];
					clearer.getInventory().refresh();
					clearer.getSkills().configure();
					clearer.getSkills().refresh();
					clearer.getEquipment().refresh();
					clearer.getAppearance().sync();
				}
				// New option become, allows you to swap with aip.
				/*Player playercopy = (Player)AIPBuilder.copy(player, player.getName(), player.getLocation());
				Player aicopy = (Player)AIPBuilder.copy(aip, aip.getName(), aip.getLocation());
				MSPacketRepository.sendInfoUpdate(player);
				// Now set them to each other.
				Player player1;
				Player player2;
				for(int i=0; i<2; i++) {
					player1 = i == 0 ? player : aip;
					player2 = i == 0 ? aicopy : playercopy;
					//player1.setName(player2.getName());
					player1.getProperties().setTeleportLocation(player2.getLocation());
					player1.getInventory().copy(player2.getInventory());
					player1.getInventory().refresh();
					player1.getSkills().copy(player2.getSkills());
					player1.getSkills().configure();
					player1.getEquipment().copy(player2.getEquipment());
					player1.getEquipment().refresh();
					player1.getAppearance().sync();
				}*/
				//aip.getPulseManager().clear();
				//player.removeAttribute("aip_select");
				break;
			case 5:
				AIPlayer.deregister(aip.getUid());
				player.getInterfaceManager().closeChatbox();
				player2.removeAttribute("aip_select");
				player2.removeAttribute("aip_became");
				break;
			}
			close();
			return true;
		case 232:
			switch (buttonId) {
			case 1:
				aip.getSettings().toggleRun();
				break;
			case 2:
				aip.getSettings().toggleRetaliating();
				break;
			case 3:
				aip.getSettings().toggleSpecialBar();
				break;
			case 4:
				Player playercopy = (Player)AIPBuilder.copy(player, player.getName(), player.getLocation());
				Player aicopy = (Player)AIPBuilder.copy(aip, aip.getName(), aip.getLocation());
				MSPacketRepository.sendInfoUpdate(player);
				// Now set them to each other.
				Player playera;
				Player playerb;
				for(int i=0; i<2; i++) {
					// This is if the we are the ai player trying to swap with ourselves.
					// Instead lets swap with the player and then later return control to player.
					if (player == aip) {
						playercopy = (Player)AIPBuilder.copy(player2, player2.getName(), player2.getLocation());
						playera = i == 0 ? player2 : aip;
						playerb = i == 0 ? aicopy : playercopy;
					} else {
						playera = i == 0 ? player : aip;
						playerb = i == 0 ? aicopy : playercopy;
					}
					//player1.setName(player2.getName());
					playera.getProperties().setTeleportLocation(playerb.getLocation());
					playera.getInventory().copy(playerb.getInventory());
					playera.getInventory().refresh();
					playera.getSkills().copy(playerb.getSkills());
					playera.getSkills().configure();
					playera.getEquipment().copy(playerb.getEquipment());
					playera.getEquipment().refresh();
					playera.getAppearance().sync();
				}
				aip.getPulseManager().clear();
				// If we have become an aip, use the new aip.
				// Otherwise use otherselves.
				// AIP proxying gets confusing xD
				if (player != player2 && player != aip) {
					//player2.setAttribute("aip_select", aip);
					//player2.setAttribute("aip_became", aip);
				} else {
					player.getInterfaceManager().closeChatbox();
					player2.removeAttribute("aip_select");
					player2.removeAttribute("aip_became");
				}
				break;
			}
			close();
			return true;
		}
		return false;
	}

	@Override
	public int[] getIds() {
		return new int[0];
	}

}