package plugin.npc.cerberus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.areillan.game.content.global.BossKillCounter;
import org.areillan.game.content.skill.Skills;
import org.areillan.game.node.entity.Entity;
import org.areillan.game.node.entity.combat.BattleState;
import org.areillan.game.node.entity.combat.CombatStyle;
import org.areillan.game.node.entity.combat.CombatSwingHandler;
import org.areillan.game.node.entity.combat.ImpactHandler.HitsplatType;
import org.areillan.game.node.entity.combat.equipment.SwitchAttack;
import org.areillan.game.node.entity.combat.handlers.MultiSwingHandler;
import org.areillan.game.node.entity.impl.Projectile;
import org.areillan.game.node.entity.npc.AbstractNPC;
import org.areillan.game.node.entity.npc.IdleAbstractNPC;
import org.areillan.game.node.entity.npc.NPC;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.link.prayer.PrayerType;
import org.areillan.game.system.task.Pulse;
import org.areillan.game.world.GameWorld;
import org.areillan.game.world.map.Location;
import org.areillan.game.world.map.RegionManager;
import org.areillan.game.world.update.flag.context.Animation;
import org.areillan.game.world.update.flag.context.Graphics;
import org.areillan.tools.RandomFunction;

/**
 * Handles the Cerberus NPC.
 * @author Empathy
 * @author Vexia
 * 
 */
public class CerberusNPC extends IdleAbstractNPC {

	/**
	 * Cerberus's projectile animation.
	 */
	public static final Animation PROJECTILE = new Animation(88);

	/**
	 * Cerberus's death animation.
	 */
	public static final Animation DEATH = new Animation(6564);

	/**
	 * Cerberus's melee animation.
	 */
	public static final Animation MELEE = new Animation(6562);

	/**
	 * Cerberus's aarrooo animation.
	 */
	public static final Animation HOWL = new Animation(6562);

	/**
	 * Cerberus's grrr attack.
	 */
	public static final Animation GR = new Animation(6562);

	/**
	 * The range soul animation.	
	 */
	public static final Animation RANGE_SOUL = new Animation(6562);

	/**
	 * The mage soul animation.
	 */
	public static final Animation MAGE_SOUL = new Animation(6562);

	/**
	 * Cerberus's grrr graphic.
	 */
	public static final Graphics LAVA = new Graphics(1189);//1189

	/**
	 * The cerberus swing handler.
	 */
	private final CombatSwingHandler swingHandler = new MultiSwingHandler(true, new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), PROJECTILE, null, null, Projectile.create((Entity) null, null, 3243, 15, 30, 50, 50, 14, 255)), new SwitchAttack(CombatStyle.MELEE.getSwingHandler(), MELEE), new SwitchAttack(CombatStyle.RANGE.getSwingHandler(), PROJECTILE, null, null, Projectile.create((Entity) null, null, 3241, 15, 30, 50, 50, 14, 255)));

	/**
	 * Checks if there is an aaroo already being handled.
	 */
	private boolean howlInProgress;

	/**
	 * Constructs a new {@code CerberusNPC} {@code Object}.
	 */
	public CerberusNPC() {
		this(8634, 8632, null);
	}

	/**
	 * Constructs a new {@code CerberusNPC} {@code Object}.
	 * @param id The active NPC id.
	 * @param location The location.
	 */
	public CerberusNPC(int id, Location location) {
		this(8634, id, location);
	}

	/**
	 * Constructs a new {@code CerberusNPC} {@code Object}.
	 * @param idleId The idle NPC id.
	 * @param id The active NPC id.
	 * @param location The location.
	 */
	public CerberusNPC(int idleId, int activeId, Location location) {
		super(idleId, activeId, location);
	}

	@Override
	public AbstractNPC construct(int id, Location location, Object... objects) {
		return new CerberusNPC(id, location);
	}

	@Override
	public void handleTickActions() {
		super.handleTickActions();
		if (isIdle() && !isInvisible()) {
			List<Player> players = RegionManager.getLocalPlayers(this);
			if (players.size() > 0) {
				disturbCerb(players.get(0));
			}
		}
	}

	@Override
	public void finalizeDeath(Entity killer) {
		super.finalizeDeath(killer);
		if (killer instanceof Player) {
			BossKillCounter.addtoKillcount((Player) killer, getId());
		}
	}

	@Override
	public void sendImpact(BattleState state) {
		if (state.getEstimatedHit() > 23) {
			state.setEstimatedHit(23);
		}
		if (!howlInProgress) {
			if (RandomFunction.getRandom(1) <= 1) {
				sendHowlAttack(state.getVictim());
				return;
			}
		}
		if (getSkills().getLifepoints() < 200) {
			if (RandomFunction.getRandom(7) == 1) {
				sendGrAttack(state.getVictim());
				return;
			}
		}
		super.sendImpact(state);
	}

	@Override
	public boolean isAttackable(Entity entity, CombatStyle style) {
		if (entity instanceof Player && entity.asPlayer().getSkills().getStaticLevel(Skills.SLAYER) < 91) {
			entity.asPlayer().sendMessage("You need a slayer level of 91 to know how to wound this monster.");
			return false;
		}
		return super.isAttackable(entity, style);
	}

	
	@Override
	public void disturb(Entity disturber) {
		super.disturb(disturber);
	}
	
	
	@Override
	public boolean canDisturb(Entity disturber) {
		return false;
	}

	@Override
	public CombatSwingHandler getSwingHandler(boolean swing) {
		return swingHandler;
	}

	@Override
	public int[] getIds() {
		return new int[] { 8632, 8634 };
	}
	
	/**
	 * Handles the Cerberus disturbtion
	 * @param disturber The disturber.
	 */
	private void disturbCerb(final Entity disturber) {
		GameWorld.submit(new Pulse(3) {

			@Override
			public boolean pulse() {
				disturb(disturber);
				return true;
			}
			
		});
	}

	/**
	 * Handles the Aarooo attack.
	 * @param entity The entity.
	 */
	private void sendHowlAttack(final Entity entity) {
		List<Location> locations = new ArrayList<>(Arrays.asList(Location.create(1303, 1327, 0), Location.create(1304, 1327, 0), Location.create(1305, 1327, 0)));
		animate(HOWL);
		sendChat("Aaarrrooooooo");
		howlInProgress = true;
		final NPC mageSoul = NPC.create(8671, RandomFunction.getRandomElement(locations.toArray(new Location[locations.size()])));
		locations.remove(mageSoul.getLocation());
		final NPC rangeSoul = NPC.create(8670, RandomFunction.getRandomElement(locations.toArray(new Location[locations.size()])));
		locations.remove(rangeSoul.getLocation());
		final NPC meleeSoul = NPC.create(8672, locations.get(0));
		final List<NPC> souls = new ArrayList<>(Arrays.asList(mageSoul, rangeSoul, meleeSoul));
		GameWorld.submit(new Pulse(1) {
			int count = 0;
			@Override
			public boolean pulse() {
				if (meleeSoul.getLocation().getY() == 1320) {
					GameWorld.submit(new Pulse(3) {

						int soulAttack = 0;

						@Override
						public boolean pulse() {
							if (soulAttack >= 3 && RegionManager.getLocalNpcs(entity).size() == 1) {
								howlInProgress = false;
								return true;
							}
							for (NPC n : souls) {
								if (soulAttack == 0) {
									if (n.getLocation().equals(Location.create(1303, 1320, 0))) {
										handleSoulAttack(entity.asPlayer(), n, Location.create(1303, 1327, 0));
										souls.remove(n);
										break;
									}
								}
								if (soulAttack == 1) {
									if (n.getLocation().equals(Location.create(1304, 1320, 0))) {
										handleSoulAttack(entity.asPlayer(), n, Location.create(1304, 1327, 0));
										souls.remove(n);
										break;
									}
								}
								if (soulAttack == 2) {
									if (n.getLocation().equals(Location.create(1305, 1320, 0))) {
										handleSoulAttack(entity.asPlayer(), n, Location.create(1305, 1327, 0));
										souls.remove(n);
										break;
									}
								}
							}
							soulAttack++;
							return false;
						}
					});
					return true;
				}
				if (count == 0) {
					for (NPC n : souls) {
						n.init();
						n.getWalkingQueue().reset();
						n.getWalkingQueue().addPath(n.getLocation().getX(), n.getLocation().getY() - 7);
					}
				}
				count++;
				return false;
			}

		});


	}

	/**
	 * Handles the soul attacks.
	 * @param player The player.
	 * @param npc The soul npc.
	 */
	private void handleSoulAttack(final Player player, final NPC npc, final Location location) {

		//TODO: The projectile for all three of them, the gfxs are fucked.

		Animation anim = null;
		PrayerType type = null;
		boolean hit = false;

		switch (npc.getId()) {
		case 8670:
			anim = RANGE_SOUL;
			type = PrayerType.PROTECT_FROM_MISSILES;
			break;
		case 8671:
			anim = MAGE_SOUL;
			type = PrayerType.PROTECT_FROM_MAGIC;
			break;
		case 8672:
			anim = RANGE_SOUL;
			type = PrayerType.PROTECT_FROM_MELEE;
			break;

		}
		npc.animate(anim);
		npc.face(player);
		if (player.getPrayer().getActive().contains(type)) {
			double ppoints = player.getSkills().getPrayerPoints() - 30;
			player.getSkills().setPrayerPoints(ppoints <= 0 ? 0 : ppoints);
		} else {
			hit = true;
		}
		final boolean hitPlayer = hit;
		GameWorld.submit(new Pulse(2, npc) {
			int count = 0;			
			@Override
			public boolean pulse() {
				if (npc.getLocation().getY() == 1327) {
					npc.clear();
					return true;
				}				
				if (count == 0) {
					if (hitPlayer) {
						player.getImpactHandler().manualHit(npc, 30, HitsplatType.NORMAL);
					}
					count++;
					return false;
				}
				npc.faceLocation(location);
				npc.getWalkingQueue().reset();
				npc.getWalkingQueue().addPath(location.getX(), location.getY());
				return false;
			}
		});
	}

	/**
	 * Handles the gr attack.
	 * @param entity The entity.
	 */
	private void sendGrAttack(Entity entity) {
		List<Location> spots = new ArrayList<>();
		sendChat("Grrrrrrrrrr");
		animate(GR);
		spots.add(entity.getLocation());
		while (spots.size() < 3) {
			Location l = Location.getRandomLocation(getLocation(), 3, true);
			if (!spots.contains(l)) {
				spots.add(l);
			}
		}
		final Location[] locations = spots.toArray(new Location[spots.size()]);
		final Location cerbLoc = getLocation();

		GameWorld.submit(new Pulse(2) {
			int count = 0;

			@Override
			public boolean pulse() {
				if (count == 3) {
					return true;
				}
				if (count <= 2) {
					final Location loc = locations[count];
					Graphics.send(LAVA, loc);
					GameWorld.submit(new Pulse(1) {
						int damageCount = 0;

						@Override
						public boolean pulse() {
							if (damageCount == 3) {
								return true;
							}
							for (Player p : RegionManager.getLocalPlayers(cerbLoc)) {
								if (p.getLocation().equals(loc)) {
									p.getImpactHandler().manualHit(p, 10, HitsplatType.NORMAL);
								}
							}
							damageCount++;
							return false;
						}

					});
				}

				count++;
				return false;
			}

		});

	}

}
