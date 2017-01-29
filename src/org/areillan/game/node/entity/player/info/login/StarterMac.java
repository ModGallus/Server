package org.areillan.game.node.entity.player.info.login;

import java.io.File;
import java.util.HashMap;

/*public class StarterMac {

	private static HashMap<String, Integer> macs = new HashMap<String, Integer>();

	@SuppressWarnings("unchecked")
	public static void init() {
		try {
			macs = (HashMap<String, Integer>) SerializableFilesManager.loadSerializedFile(new File("data/startermacs.s"));
			if (macs == null)
				macs = new HashMap<String, Integer>();
		} catch (Exception e) {
		}
	}

	public static boolean checkStarter(String mac) {
		if (!macs.containsKey(mac)) {
			macs.put(mac, 1);
			save();
			return true;
		}
		int start = macs.remove(mac);
		if (start >= 3) {
			macs.put(mac, 3);
			save();
			return false;
		}
		macs.put(mac, start + 1);
		save();
		return true;
	}

	public static void save() {
		try {
			SerializableFilesManager.storeSerializableClass(macs, new File("data/startermacs.s"));
		} catch (Exception e) {
		}
	}

}*/