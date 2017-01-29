package plugin.zone.neitiznot;

import org.areillan.game.interaction.Option;
import org.areillan.game.node.Node;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.map.zone.MapZone;
import org.areillan.game.world.map.zone.ZoneBuilder;
import org.areillan.plugin.Plugin;
import org.areillan.plugin.PluginManager;

/**
 * Handles the neitiznot zone.
 * @author Vexia
 */
public class NeitiznotZone extends MapZone implements Plugin<Object> {

	/**
	 * Constructs a new {@code NeitiznotZone} {@code Object}
	 */
	public NeitiznotZone() {
		super("Neitiznot zone", true);
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		ZoneBuilder.configure(this);
		PluginManager.definePlugin(new MawnisBurowgarDialogue(), new ThakkradYakDialogue(), new YakArmourPlugin(), new YakArmourPlugin());
		return this;
	}

	@Override
	public boolean interact(Entity e, Node target, Option option) {
		if (e instanceof Player) {
			Player player = e.asPlayer();
			switch (target.getId()) {
			case 21301:
				player.getBank().open();
				return true;
			case 5506:
				if (option.getName().equals("Craft-goods")) {
					player.getDialogueInterpreter().open("thakkrad-yak");
					return true;
				}
				break;
			}
		}
		return super.interact(e, target, option);
	}

	@Override
	public Object fireEvent(String identifier, Object... args) {
		return null;
	}

	@Override
	public void configure() {
		registerRegion(9275);
	}

}
