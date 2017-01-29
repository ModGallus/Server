package org.areillan.net.packet.in;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.RunScript;
import org.areillan.net.packet.IncomingPacket;
import org.areillan.net.packet.IoBuffer;
import org.areillan.tools.StringUtils;

/**
 * Represents the incoming packet to handle a run script.
 * @author 'Vexia
 */
public class RunScriptPacketHandler extends IncomingPacket {

	@Override
	public void decode(Player p, int opcode, IoBuffer buffer) {
		Player player = useAIP(p);
		final RunScript script = player.getAttribute("runscript", null);
		if (script == null || player.getLocks().isInteractionLocked()) {
			return;
		}
		Object value = "";
		if (opcode == 244) {
			value = StringUtils.longToString(buffer.getLong());
		} else if (opcode == 23) {
			value = buffer.getInt();
		} else if (opcode == 65){
			value = buffer.getString();//"longInput"
		} else {
			value = buffer.getInt();
		}
		script.setValue(value);
		script.setPlayer(player);
		player.removeAttribute("runscript");
		script.handle();
	}
}
