package org.areillan.game.node.entity.player.link;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;

import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.login.SavingModule;
import org.areillan.game.node.entity.player.link.SavedData;
import org.areillan.game.world.repository.Repository;
import org.areillan.net.packet.PacketRepository;
import org.areillan.net.packet.context.ChildPositionContext;
import org.areillan.net.packet.out.RepositionChild;

/**
 * Handles the spawn data for the spawn world.
 *
 */
/*public class QuestDatacustom implements SavingModule {

	*//**
	 * The kill streak messages.
	 *//*
	private static final String[] KILLSTREAKS = new String[] {"rampage", "massacre", "frenzy", "annihilation", "decimation", "butchery", "extermination", "genocide", "carnage", "slaughter", "bloodshed", "assassination", "obliteration"};




	*//**
	 * The pk points.
	 *//*
	private int pkPoints;

	*//**
	 * The amount of kills.
	 *//*
	private int kills;

	*//**
	 * The ammount of deaths.
	 *//*
	private int deaths;

	*//**
	 * The skill streak.
	 *//*
	private int killStreak;

	*//**
	 * Constructs a new {@Code QuestData} {@Code Object}
	 *//*
	public QuestDatacustom() {
		*//**
		 * empty.
		 *//*
	}

	public void save(ByteBuffer buffer) {
		SavedData.save(buffer, pkPoints, 1);
		SavedData.save(buffer, kills, 2);
		SavedData.save(buffer, deaths, 3);
		SavedData.save(buffer, killStreak, 4);
		buffer.put((byte) 0);
	}

	public void parse(ByteBuffer buffer) {
		int opcode;
		while ((opcode = buffer.get()) != 0) {
			switch(opcode) {
			case 1:
				pkPoints = buffer.getInt();
				break;
			case 2:
				kills = buffer.getInt();
				break;
			case 3:
				deaths = buffer.getInt();
				break;
			case 4:
				killStreak = buffer.getInt();
				break;
			}
		}
	}

	*//**
	 * Draws the stats tab. 
	 * @param player the player.
	 *//*
	public void drawStatsTab(Player player) {
		for (int i = 20; i < 153; i++) {
			if (i == 32) {
				continue;
			}
			player.getPacketDispatch().sendInterfaceConfig(274, i, true);
		}
		sendString(player, "Players online: " + Repository.getPlayers().size(), 10, null);
		sendString(player, "Information:", 13, null);
		sendString(player, "Aincrad credits: " + player.getDetails().getShop().getCredits(), 14);
		sendString(player, "PK Points: " + player.getSavedData().getQuestData().getPkPoints(), 15);
		sendString(player, "Aincrad Kills: " + getKills(), 16);
		sendString(player, "Aincrad Deaths: " + getDeaths(), 17);
		sendString(player, "Aincrad KDR: " + getKdr(), 18);
		sendString(player, "", 32);
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 60, 10, 298));
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 33, 10, 300));
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 34, 11, 314));
		PacketRepository.send(RepositionChild.class, new ChildPositionContext(player, 274, 32, 10, 119));
	}

	*//**
	 * Sends the string.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 * @param color the color.
	 *//*
	private void sendString(Player player, String string, int child, String color) {
		player.getPacketDispatch().sendInterfaceConfig(274, child, false);
		player.getPacketDispatch().sendString((color != null ? color : "") + string, 274, child);
	}

	*//**
	 * Sends the string.
	 * @param player the player.
	 * @param string the string.
	 * @param child the child.
	 *//* 
	private void sendString(Player player, String string, int child) {
		sendString(player, string, child, "<col=FE9A2E>");
	}

	*//**
	 * Shouts spawn info of the player.
	 * @param p the player.
	 * @param button the button.
	 *//*
	public void handleButton(Player p, int button) {
			p.getAppearance().sync();
			drawStatsTab(p);
		}
	

	*//**
	 * Gets a kill streak message.
	 * @param killer the killer.
	 * @param killed the killed.
	 * @return the kill streak message.no1 readd him
	 *//*
	private String getStreakMessage(Player killer, Player killed) {
		int streak = getKillStreak();	
		String message = "killstreak";
		if (streak > 5) {
			int index = streak - 6;
			if (index > KILLSTREAKS.length-1) {
				return killer.getUsername() + " is unstoppable! " + (killer.getAppearance().isMale() ? "He" : "She") + " is on a killstreak of " + streak + "!";
			} else {
				message = "kill " + KILLSTREAKS[index];
			}
		}
		return killer.getUsername() + " is on a " + streak + " " + message + "! Kill " + (killer.getAppearance().isMale() ? "him" : "her") + " to gain " + streak + " PKP!";
	}

	*//**
	 * Increments the kill streak.
	 *//*
	public void incrementStreak() {
		killStreak++;
	}

	*//**
	 * Increments the deaths.
	 *//*
	public void incrementDeaths() {
		deaths++;
	}

	*//**
	 * Increments the kills.
	 *//*
	public void incrementKills() {
		kills++;
	}

	*//**
	 * Increments the pk points.
	 * @param increment the increment.
	 *//*
	public void incrementPkPoints(int increment) {
		setPkPoints(getPkPoints() + increment);
	}

	*//**
	 * Gets the kdr.
	 * @return the kdr.
	 *//*
	public String getKdr() {
		return new DecimalFormat().format(deaths == 0 ? kills : (double) ((double) kills / (double) deaths));
	}



	*//**
	 * Gets the pkPoints.
	 * @return the pkPoints
	 *//*
	public int getPkPoints() {
		return pkPoints;
	}
	
	

	*//**
	 * Sets the bapkPoints.
	 * @param pkPoints the pkPoints to set.
	 *//*
	public void setPkPoints(int pkPoints) {
		this.pkPoints = pkPoints;
	}



	*//**
	 * Gets the kills.
	 * @return the kills
	 *//*
	public int getKills() {
		return kills;
	}

	*//**
	 * Sets the bakills.
	 * @param kills the kills to set.
	 *//*
	public void setKills(int kills) {
		this.kills = kills;
	}

	*//**
	 * Gets the deaths.
	 * @return the deaths
	 *//*
	public int getDeaths() {
		return deaths;
	}

	*//**
	 * Decrements the points.
	 *//*
	public void decrementPoints(int decrement) {
		pkPoints-= decrement;
	}
	

	*//**
	 * Sets the badeaths.
	 * @param deaths the deaths to set.
	 *//*
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	*//**
	 * Gets the killStreak.
	 * @return the killStreak
	 *//*
	public int getKillStreak() {
		return killStreak;
	}

	*//**
	 * Sets the bakillStreak.
	 * @param killStreak the killStreak to set.
	 *//*
	public void setKillStreak(int killStreak) {
		this.killStreak = killStreak;
	}

}
*/