package org.areillan.net.packet.in;

import java.util.List;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.ai.AIPlayer;
import org.areillan.game.system.monitor.PlayerMonitor;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.update.flag.context.ChatMessage;
import org.areillan.game.world.update.flag.player.ChatFlag;
import org.areillan.net.amsc.MSPacketRepository;
import org.areillan.net.amsc.WorldCommunicator;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;
import org.areillan.tools.StringUtils;

/**
 * Represents the incoming chat packet.
 * @author Emperor
 */
public class ChatPacket extends IncomingPacket {

	@Override
	public void decode(final Player p, int opcode, IoBuffer buffer) {
		try {
			final Player player = useAIP(p);
			final int effects = buffer.getShort();
			final int numChars = buffer.getSmart();
			final String message = StringUtils.decryptPlayerChat(buffer, numChars);
			if (player.getDetails().isMuted()) {
				player.getPacketDispatch().sendMessage("You have been " + (player.getDetails().isPermMute() ? "permanently" : "temporarily") + " muted due to breaking a rule.");
				return;
			}
			if (message.startsWith("/") && player.getCommunication().getClan() != null) {
				StringBuilder sb = new StringBuilder(message);
				sb.append(" => ").append(player.getName()).append(" (owned by ").append(player.getCommunication().getClan().getOwner()).append(")");
				String m = sb.toString();
				player.getMonitor().log(m.replace(m.charAt(0), ' ').trim(), PlayerMonitor.CLAN_CHAT_LOG);
				if (WorldCommunicator.isEnabled()) {
					MSPacketRepository.sendClanMessage(player, message.substring(1));
				} else {
					player.getCommunication().getClan().message(player, message.substring(1));
				}
				return;
			}
			player.getMonitor().log(message, PlayerMonitor.PUBLIC_CHAT_LOG);
			GameWorld.submit(new Pulse(1, player) {
				@Override
				public boolean pulse() {
					// If we have a legion.
					if (!player.isArtificial()) {
						List<AIPlayer> legion = player.getAttribute("aip_legion");
						if (legion != null && !legion.isEmpty()) {
							for (AIPlayer aip : legion) {
								if (aip != null && aip.isArtificial()) {
									aip.getUpdateMasks().register(new ChatFlag(new ChatMessage(aip, message, effects, numChars)));
								}
							}
						}
					}
					player.getUpdateMasks().register(new ChatFlag(new ChatMessage(player, message, effects, numChars)));
					return true;
				}
			});
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}