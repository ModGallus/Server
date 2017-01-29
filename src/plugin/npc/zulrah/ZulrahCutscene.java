package plugin.npc.zulrah;

import org.areillan.game.content.activity.ActivityPlugin;
import org.areillan.game.content.activity.CutscenePlugin;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.object.GameObject;
import org.areillan.game.node.object.ObjectBuilder;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Direction;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.build.DynamicRegion;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.CameraContext;
import org.areillan.net.packet.context.CameraContext.CameraType;
import org.areillan.net.packet.out.CameraViewPacket;

/**
 * Handles the cutscene for Zulrah.
 * @author Vexia
 *
 */
public class ZulrahCutscene extends CutscenePlugin {
	
	/**
	 * The zulrah npc.
	 */
	private ZulrahNPC zulrah;
	
	/**
	 * Constructs a new @{Code ZulrahCutscene} object.
	 * @param p The player.
	 */
	public ZulrahCutscene(Player p) {
		super("zulrah cutscene");
		this.player = p;
	}
	
	/**
	 * Constructs a new @{Code ZulrahCutscene} object.
	 */
	public ZulrahCutscene() {
		this(null);
	}
	
	@Override
	public void open() {
		super.open();
		player.getDialogueInterpreter().close();
		player.getInterfaceManager().closeChatbox();
		player.getInterfaceManager().openDefaultTabs();
		int x = player.getLocation().getX() - 10;
		int y = player.getLocation().getY() - 4;
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.POSITION, x, y,  630, 1, 100));
		PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.ROTATION, x + 3, y + 3, 630, 1, 100));
		player.unlock();
		GameWorld.submit(new Pulse(6, player) {
			@Override
			public boolean pulse() {
				PacketRepository.send(CameraViewPacket.class, new CameraContext(player, CameraType.RESET, player.getLocation().getX(), player.getLocation().getY(),  630, 1, 100));
				return true;
			}
		});
		zulrah = new ZulrahNPC(8620, base.transform(26, 64, 0), ZulrahPattern.getPattern());
		zulrah.setBase(base);
		GameObject object = new GameObject(42013, zulrah.getBase().transform(28, 44, 0)); 
		ObjectBuilder.add(object);
		zulrah.setDirection(Direction.SOUTH);
		zulrah.setWalks(false);
		zulrah.init();
	}
	
	@Override
	public boolean leave(final Entity e, boolean logout) {
		return false;
	}

	@Override
	public ActivityPlugin newInstance(Player p) throws Throwable {
		return new ZulrahCutscene(p);
	}

	@Override
	public Location getSpawnLocation() {
		return null;
	}
	
	@Override
	public Location getStartLocation() {
		return base.transform(28, 60, 0);
	}

	@Override
	public void configure() {
		region = DynamicRegion.create(9007, 9008);
		setMulticombat(true);
		setRegionBase();
		registerRegion(region.getId());
		player.getDialogueInterpreter().sendPlainMessage(true, "The priestess rows you to Zulrah's shrine.", "then hurridlly paddles away.");
	}
	
	@Override
	public int getMapState() {
		return 0;
	}
	
}
