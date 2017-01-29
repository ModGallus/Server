package plugin.interaction.item.withobject;

import org.areillan.game.content.global.Bones;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.interaction.NodeUsageEvent;
import org.areillan.game.interaction.UseWithHandler;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.plugin.Plugin;

/**
 * Handles the guilded altar.
 * @author Dylan
 *
 */
public class GildedAltarPlugin extends UseWithHandler {

	/**
	 * The animation for the player.
	 */
	private static final Animation ANIMATION = new Animation(713);
	
	/**
	 * The graphics for the player.
	 */
	private static final Graphics GRAPHICS = new Graphics(624);

	/**
	 * Constructs the {@code GuildedAltarPlugin}
	 */
	public GildedAltarPlugin() {
		super(Bones.getArray());
	}

	@Override
	public Plugin<Object> newInstance(Object arg) throws Throwable {
		addHandler(13197, OBJECT_TYPE, this);
		return this;
	}

	@Override
	public boolean handle(NodeUsageEvent event) {
		final Player player = event.getPlayer();
		final Bones bone = Bones.forId(event.getUsedItem().getId());
		if (bone == null) {
			return true;
		}
		if (player.getInventory().remove(event.getUsedItem(), event.getUsedItem().getSlot(), true)) {
			player.lock(ANIMATION.getDelay());
			player.visualize(ANIMATION, GRAPHICS);
			player.getSkills().addExperience(Skills.PRAYER, bone.getExperience() * 3.5);
			player.sendMessage("The gods are very pleased with your offering.");
		}
		return true;
	}

}
