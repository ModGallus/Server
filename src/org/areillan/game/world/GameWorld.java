package org.areillan.game.world;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.areillan.ServerConstants;
import org.areillan.cache.Cache;
import org.areillan.cache.ServerStore;
import org.areillan.game.content.eco.ge.GrandExchangeDatabase;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.SystemLogger;
import org.areillan.game.system.SystemManager;
import org.areillan.game.system.SystemState;
import org.areillan.game.system.mysql.SQLManager;
import org.areillan.game.system.script.ScriptManager;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.system.task.TaskExecutor;
import org.areillan.game.world.callback.CallbackHub;
import org.areillan.game.world.repository.DisconnectionQueue;
import org.areillan.game.world.repository.Repository;
import org.areillan.plugin.PluginManager;
import org.areillan.worker.MajorUpdateWorker;

/**
 * Represents the game world.
 * @author Emperor
 */
public final class GameWorld {

	/**
	 * The major update worker.
	 */
	private static final MajorUpdateWorker MAJOR_UPDATE_WORKER = new MajorUpdateWorker();

	/**
	 * The lock object.
	 */
	private static final Lock LOCK = new ReentrantLock();

	/**
	 * The pulse tasks list.
	 */
	private static final List<Pulse> TASKS = new ArrayList<>();

	/**
	 * The game settings to use.
	 */
	private static GameSettings settings;

	/**
	 * The current amount of (600ms) cycles elapsed.
	 */
	private static int ticks;

	/**
	 * Constructs a new {@Code GameWorld} {@Code Object}
	 */
	private GameWorld() {
		/**
		 * empty.
		 */
	}

	/**
	 * Submits a pulse.
	 * @param pulse the pulse.
	 */
	public static void submit(Pulse pulse) {
		LOCK.lock();
		try {
			TASKS.add(pulse);
		} finally {
			LOCK.unlock();
		}
	}

	/**
	 * Pulses all current pulses.
	 */
	public static void pulse() {
		LOCK.lock();
		List<Pulse> pulses = null;
		try {
			pulses = new ArrayList<>(TASKS);
		} finally {
			LOCK.unlock();
		}
		for (Pulse pulse : pulses) {
			if (pulse == null) {
				continue;
			}
			try {
				if (pulse.update()) {
					TASKS.remove(pulse);
				}
			} catch (Throwable t) {
				t.printStackTrace();
				pulse.stop();
				TASKS.remove(pulse);
			}
		}
		pulses.clear();
		ticks++;
		if (ticks % 50 == 0) {
			TaskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						for (Iterator<Player> it = Repository.getPlayers().iterator(); it.hasNext();) {
							Player p = it.next();
							if (p != null && !p.isArtificial() && p.isPlaying()) {
								DisconnectionQueue.save(p, false);
							}
						}
					} catch (Throwable t) {
						t.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * Prompts the {@link GameWorld} to begin it's initialization.
	 * @param directory the directory to the properties.
	 * @throws Throwable when the exception occurs.
	 */
	public static void prompt(String directory) throws Throwable {
		prompt(true, directory);
	}

	/**
	 * Prompts the game world.
	 * @param running if running.
	 * @throws Throwable the throwable.
	 */
	public static void prompt(boolean running) throws Throwable {
		prompt(running, "server.properties");
	}

	/**
	 * Prompts the {@link GameWorld} to begin its initialization.
	 * @param run If the server should be running.
	 * @param directory the path to the dir.
	 * @throws Throwable When an exception occurs.
	 */
	public static void prompt(boolean run, String directory) throws Throwable {
		SystemLogger.log("Prompting " + GameWorld.getName() + " Game World...");
		Cache.init(ServerConstants.CACHE_PATH);
		ServerStore.init(ServerConstants.STORE_PATH);
		GrandExchangeDatabase.init();
		SQLManager.prePlugin();
		ScriptManager.load();
		PluginManager.init();
		SQLManager.postPlugin();
		CallbackHub.call();
		if (run) {
			SystemManager.flag(GameWorld.getSettings().isDevMode() ? SystemState.PRIVATE : SystemState.ACTIVE);
		}
		System.gc();
	}
	
	/**
	 * Called when the server shuts down.
	 * @throws Throwable When an exception occurs.
	 */
	public static final void shutdown() throws Throwable {
		SystemManager.flag(SystemState.TERMINATED);
	}

	/**
	 * Gets the major update worker.
	 * @return The major update worker.
	 */
	public static MajorUpdateWorker getMajorUpdateWorker() {
		return MAJOR_UPDATE_WORKER;
	}

	/**
	 * Gets the ticks.
	 * @return The ticks.
	 */
	public static int getTicks() {
		return ticks;
	}

	/**
	 * Gets the settings.
	 * @return The settings.
	 */
	public static GameSettings getSettings() {
		if (settings == null) {
			return (settings = GameSettings.parse("server.properties"));
		}
		return settings;
	}

	/**
	 * Sets the settings.
	 * @param settings The settings to set.
	 */
	public static void setSettings(GameSettings settings) {
		GameWorld.settings = settings;
	}

	/**
	 * Gets the name of the world.
	 * @return the name.
	 */
	public static String getName() {
		return getSettings().getName();
	}
}