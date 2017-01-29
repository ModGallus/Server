package org.areillan.game.system;

import java.util.ArrayList;
import java.util.List;

import org.areillan.game.node.entity.player.info.UIDInfo;
import org.areillan.game.world.GameWorld;

/**
 * Represents an WildScape Pk staff account.
 * @author Vexia
 *
 */
public class StaffAccount {
	
	/**
	 * If the account is an admin account.
	 */
	private final boolean administrator;

	/**
	 * The valid MAC addresses for this account.
	 */
	private final List<String> macs = new ArrayList<>();
	
	/**
	 * The valid serial addresses for this account.
	 */
	private final List<String> serials = new ArrayList<>();
	
	/**
	 * Constructs a new {@Code StaffAccount} {@Code Object}
	 * @param administrator If the account is an admin.
	 */
	public StaffAccount(boolean administrator) {
		this.administrator = administrator;
	}
	
	/**
	 * Validates the UID info.
	 * @param info The UID information.
	 * @return {@code True} if valid.
	 */
	public boolean validate(UIDInfo info) {
		if (GameWorld.getSettings().isDevMode()) {
			return true;
		}
		int count = 0;
		for (String mac : macs) {
			if (mac.equals(info.getMac())) {
				count++;
				break;
			}
		}
		for (String serial : serials) {
			if (serial.equals(info.getSerial())) {
				count++;
				break;
			}
		}
		return count >= 2;
	}

	/**
	 * Gets the administrator.
	 * @return the administrator.
	 */
	public boolean isAdministrator() {
		return administrator;
	}

	/**
	 * Gets the macs.
	 * @return the macs.
	 */
	public List<String> getMacs() {
		return macs;
	}

	/**
	 * Gets the serials.
	 * @return the serials.
	 */
	public List<String> getSerials() {
		return serials;
	}
	
	
}
