package org.areillan.net.packet.in;

import java.io.File;

import org.areillan.ServerConstants;
import org.areillan.game.content.global.report.AbuseReport;
import org.areillan.game.content.global.report.Rule;
import org.areillan.game.node.entity.player.Player;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;
import org.areillan.tools.StringUtils;

/**
 * Represents the incoming packet to handle a report against a player.
 * @author Vexia
 */
public class ReportAbusePacket extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		final Player player = useAIP(p);
		String target = StringUtils.longToString(buffer.getLong());
		Rule rule = Rule.forId(buffer.get());
		boolean mute = buffer.get() == 1;
		File file = new File(ServerConstants.PLAYER_SAVE_PATH + target + ".arios");
		if (!file.exists()) {
			player.getPacketDispatch().sendMessage("Invalid player name.");
			return;
		}
		if (target.equalsIgnoreCase(player.getUsername())) {
			player.getPacketDispatch().sendMessage("You can't report yourself!");
			return;
		}
		AbuseReport abuse = new AbuseReport(player.getName(), target, rule);
		abuse.construct(player, mute);
	}
}
