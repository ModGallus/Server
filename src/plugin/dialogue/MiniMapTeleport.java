package plugin.dialogue;

import java.util.concurrent.TimeUnit;

import org.areillan.game.content.dialogue.DialoguePlugin;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.portal.DonatorType;
import org.areillan.game.node.entity.player.link.TeleportManager.TeleportType;
import org.areillan.game.system.mysql.impl.ShopSQLHandler;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.map.Location;

/**
 * Represents the dialogue plugin used to handle the DUMP BOB button
 * @author Splinter
 */
/*public final class MiniMapTeleport extends DialoguePlugin {
	
	*//**
	 * The teleport destinations.
	 *//*
	private static final Object[][][] TELEPORTS = new Object[][][] {
		{{"Bossing"}, {"Callisto", Location.create(3172, 3799, 0)}, {"Bork", Location.create(3111, 5529, 0)}, {"Corporeal Beast", Location.create(2968, 4384, 0)}, {"Chaos Elemental", Location.create(3307, 3916, 0)}, {"Crazy Archaeologist", Location.create(2977, 3692, 0)}, {"Daggonoth Kings", Location.create(2527, 3739, 0)}, {"Giant Mole", Location.create(1763, 5183, 0)}, {"Godwars Dungeon", Location.create(2914, 3746, 0)}, {"Kalphite Queen", Location.create(3503, 9494, 0)}, {"KBD", Location.create(3005, 3850, 0)}, {"Kraken", Location.create(3696, 5798, 0)}, {"Tormented Demons", Location.create(2574, 5734, 0)}, {"Venenatis", Location.create(3371, 3746, 0)}},
		{{"Monsters"}, {"Al-Kharid Warriors", Location.create(3293, 3180, 0)}, {"Armoured Zombies", Location.create(3239, 3606, 0)}, {"Brine Rat Cave", Location.create(2690, 10124, 0)}, {"Cows", Location.create(3256, 3266, 0)}, {"Desert Bandits", Location.create(3176, 2987, 0)}, {"Ice Warriors", Location.create(2956, 3852, 0)}, {"Lava dragons", Location.create(3202, 3859, 0)}, {"Neitiznot Yaks", Location.create(2326, 3803, 0)}, {"Stronghold of Security", Location.create(3080, 3421, 0)}, {"Rock crabs", Location.create(2672, 3712, 0)}, {"Slayer Tower", Location.create(3429, 3535, 0)}, {"TzHaar", Location.create(2450, 5165, 0)}},
		{{"Dungeons"}, {"Ape Atoll Dungeon", Location.create(2764, 9103, 0)}, {"Ancient Dungeon", Location.create(3014, 9579, 0)}, {"Asgarnian Ice Caves", Location.create(3014, 9579, 0)}, {"Brimhaven Dungeon", Location.create(2744, 3152, 0)}, {"Chaos Tunnels", Location.create(3145, 5555, 0)}, {"Edgeville Dungeon", Location.create(3097, 9870, 0)}, {"Experiment Cave", Location.create(3555, 9947, 0)}, {"Fremmenik Slayer Dungeon", Location.create(2796, 3615, 0)}, {"Lumbridge Swamp Dungeon", Location.create(3224, 9600, 0)}, {"Mos'le Harmless Dungeon", Location.create(3749, 2973, 0)}, {"Slayer Dungeon", Location.create(2441, 9825, 0)}, {"Smoke Dungeon", Location.create(3312, 2958, 0)}, {"Taverly Dungeon", Location.create(2884, 9796, 0)}, {"Varrock Sewers", Location.create(3237, 9860, 0)}},
		{{"Minigames"}, {"Barrows", Location.create(3565, 3311, 0)}, {"Clan Wars", Location.create(3272, 3687, 0)}, {"Duel Arena", Location.create(3352, 3265, 0)}, {"Fight Pits", Location.create(2399, 5178, 0)}, {"Magic Training Arena", Location.create(3363, 3298, 0)}, {"Pest Control", Location.create(2659, 2649, 0)}, {"Puro Puro", Location.create(2592, 4317, 0)}, {"Sorceress' Garden", Location.create(3315, 3163, 0)}, {"Warrior's Guild", Location.create(2881, 3546, 0)}, {"Wilderness Resource Area", Location.create(3184, 3945, 0)}},
	};
	
	*//**
	 * The lastIndex displayed.
	 *//*
	private int firstIndex;

	*//**
	 * The option index.
	 *//*
	private int optionIndex;

	*//**
	 * Constructs a new {@code DumpBOBDialogue} {@code Object}.
	 *//*
	public MiniMapTeleport() {
		*//**
		 * empty.
		 *//*
	}

	*//**
	 * Constructs a new {@code DumpBOBDialogue} {@code Object}.
	 * @param player the player.
	 *//*
	public MiniMapTeleport(Player player) {
		super(player);
	}

	@Override
	public DialoguePlugin newInstance(Player player) {
		return new MiniMapTeleport(player);
	}

	@Override
	public boolean open(Object... args) {
		int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(player.getSavedData().getGlobalData().getGlobalTeleporterDelay() - System.currentTimeMillis());
		if (minutes < 1) {
			minutes = 1;
		}
		interpreter.sendItemMessage(563, "This is the Aincrad teleport System. You may use it every","five minutes to freely teleport to many places.", (player.getSavedData().getGlobalData().getGlobalTeleporterDelay() > System.currentTimeMillis() ? "<col=CC4000>You are on cooldown for the next "+ (minutes)+" minute(s)." : ""));
		stage = -5;
		return true;
	}


	@Override
	public boolean handle(int interfaceId, int buttonId) {
		int index;
		switch (stage) {
		case -10:
			end();
			break;
		case -5:
			interpreter.sendOptions("Select an Option", "Buy teletabs", "Use teleporter");
			stage = 0;
			break;
		case 0:
			switch(buttonId){
			case 1:
				end();
				ShopSQLHandler.openUid(player, 200);
				break;
			case 2:
				if (player.getSavedData().getGlobalData().getGlobalTeleporterDelay() > System.currentTimeMillis()) {
					long millis = player.getSavedData().getGlobalData().getGlobalTeleporterDelay() - System.currentTimeMillis();
					int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(millis);
					if (minutes < 1) {
						minutes = 1;
					}
					interpreter.sendDialogue("You need to wait " + minutes + " more minute" + (minutes > 1 ? "s" : "") + " in order to use the free", "teleportation system again.");
					stage = -10;
					return true;
				}
				player.removeAttribute("global_teleporter");
				String[] options = new String[TELEPORTS.length];
				for (int i = 0; i < TELEPORTS.length; i++) {
					options[i] = (String) TELEPORTS[i][0][0];
				}
				interpreter.sendOptions("Select an Option", options);
				stage = 1;
				break;
			}
			break;
		case 1:
			index = buttonId - 1;
			optionIndex = index;
			sendOptions();
			stage = 2;
			break;
		case 2:
			index = buttonId - 1;
			int teleIndex = (firstIndex + index) + 1;
			int optionSize = 3;
			for (int i = 0; i < 3; i++) {
				if (firstIndex + 1 + i > TELEPORTS[optionIndex].length-1) {
					optionSize = i;
					break;
				}
			}
			if (teleIndex > firstIndex + optionSize) {
				firstIndex = firstIndex + optionSize;
				sendOptions();
				break;
			}
			Object teleports[] = TELEPORTS[optionIndex][teleIndex];
			if (optionIndex == 2 && teleports[teleIndex] == "Puro Puro(requires Lost City)" && !player.getQuestRepository().isComplete("Lost City")) {
				interpreter.sendDialogue("You need to have completed Lost City to teleport here.");
				break;
			}
			send(player, (Location) teleports[1]);
			break;
		}
		return true;
	}

	*//**
	 * Sends the options.
	 *//*
	private void sendOptions() {
		if (firstIndex >= TELEPORTS[optionIndex].length -1) {
			firstIndex = 0;
		}
		int optionSize = 3;
		Object[][] data = TELEPORTS[optionIndex];
		for (int i = 0; i < 3; i++) {
			if (firstIndex + 1 + i > data.length-1) {
				optionSize = i;
				break;
			}
		}
		String [] options = new String[optionSize + 1];
		for (int i = 0; i < optionSize; i++) {
			options[i] = (String) data[firstIndex + (i + 1)][0];
		}
		options[options.length - 1] = "More Options";
		interpreter.sendOptions("Select an Option", options);
	}

	*//**
	 * Sends the player to the specified location.
	 *//*
	private void send(final Player player, final Location loc){
		player.getDialogueInterpreter().close();
		player.getPulseManager().run(new Pulse(1, player) {
			int counter = 5;
			@Override
			public boolean pulse() {
				switch(--counter){
				case -1:
					player.getInterfaceManager().closeChatbox();
					player.getSavedData().getGlobalData().setGlobalTeleporterDelay(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(player.getDonatorType() == DonatorType.EXTREME ? 1 : player.getDonatorType() == DonatorType.REGULAR ? 3 : 5));
					player.getTeleporter().send(loc, TeleportType.TELE_OTHER);
					return true;
				default:
					player.getDialogueInterpreter().sendPlainMessage(true, "You will be teleported in... "+counter+".", "Move away to cancel.");
					return false;
				}
			}
		});
	}

	@Override
	public int[] getIds() {
		return new int[] { 628375 };
	}

}*/