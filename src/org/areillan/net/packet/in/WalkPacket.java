package org.areillan.net.packet.in;

import java.util.List;

import org.areillan.game.interaction.MovementPulse;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.world.map.Location;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.PlayerContext;
import org.areillan.net.packet.out.ClearMinimapFlag;

/**
 * Handles an incoming walk packet.
 * @author Emperor
 */
public final class WalkPacket extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		// If we have a legion make them regroup and follow chain.
		if (!p.isArtificial()) {
			List<AIPlayer> legion = p.getAttribute("aip_legion");
			if (legion != null && !legion.isEmpty()) {
				Player last = p;
				for (AIPlayer aip : legion) {
					aip.follow(last);
				last = aip;
				}
			}
		}
		Player player = useAIP(p);
		player = getPlayer(player);
		if (player == null) {
			return;
		}
		if (player.getLocks().isMovementLocked() || !player.getInterfaceManager().close() || !player.getInterfaceManager().closeSingleTab() || !player.getDialogueInterpreter().close()) {
			PacketRepository.send(ClearMinimapFlag.class, new PlayerContext(player));
			player.debug("[WalkPacket] did not handle - [locked=" + player.getLocks().isMovementLocked() + "]!");
			return;
		}
		player.getProperties().setSpell(null);
		player.getInterfaceManager().close();
		player.getInterfaceManager().closeChatbox();
		boolean running = buffer.getA() == 1;
		int x = buffer.getShort();
		int y = buffer.getShortA();
		int steps = (buffer.toByteBuffer().remaining() - (buffer.opcode() == 39 ? 14 : 0)) >> 1;
		for (int i = 0; i < steps; i++) {
			int offsetX = buffer.getA();
			int offsetY = buffer.getS();
			if (i == steps - 1) {
				x += offsetX;
				y += offsetY;
			}
		}
		if (opcode == 77) {
			player.getWalkingQueue().setRunning(running);
			return; // Action walking.
		}
		player.face((Entity) null);
		player.faceLocation((Location) null);
		player.getWalkingQueue().reset(running);
		player.getPulseManager().run(new MovementPulse(player, Location.create(x, y, player.getLocation().getZ()), running) {
			@Override
			public boolean pulse() {
				return true;
			}
		}, "movement");
		if (opcode == 39) {
			buffer.get(); // The x-coordinate of where we clicked on the
			// minimap.
			buffer.get(); // The y-coordinate of where we clicked on the
			// minimap.
			buffer.getShort(); // The rotation of the minimap.
			buffer.get(); // Always 57.
			buffer.get();
			buffer.get();
			buffer.get(); // Always 89
			buffer.getShort();
			buffer.getShort();
			buffer.get();
			buffer.get(); // Always 63
		}
	}

	/**
	 * Gets the player instance (used for AIP controlling).
	 * @param player The player.
	 * @return The player instance, or the AIP when the player is controlling an
	 * AIP.
	 */
	private static Player getPlayer(Player player) {
		if (player == null) {
			return null;
		}
		AIPlayer aip = player.getAttribute("aip_select");
		if (aip != null && aip.getLocation().withinDistance(player.getLocation())) {
			if (!player.getAttribute("aip_perm_select", true)) {
				player.removeAttribute("aip_select");
			}
			return aip;
		}
		return player;
	}

}