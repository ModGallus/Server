package plugin.skill.fletching;

import org.areillan.game.content.skill.member.fletching.items.darts.Dart;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.plugin.Plugin;

//import plugin.quest.touristrap.TouristTrap;

/**
 * Represents the plugin used to create a dart.
 * @author 'Vexia
 * @version 1.0
 */
public final class DartCreatePlugin extends UseWithHandler {

	/**
	 * Constructs a new {@code DartCreatePlugin} {@code Object}.
	 */
	public DartCreatePlugin() {
		super(314);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		for (Dart dart : Dart.values()) {
			addHandler(dart.getItem().getId(), ITEM_TYPE, this);
		}
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
/*		if (event.getPlayer().getQuestRepository().getQuest(TouristTrap.NAME).getStage(event.getPlayer()) < 60) {
			event.getPlayer().getPacketDispatch().sendMessage("You need to start Tourist Trap in order to do this.");
			return true;
		}*/
		event.getPlayer().getDialogueInterpreter().open(328933, event.getUsedItem(), event.getBaseItem());
		return true;
	}

}
