package org.areillan.game.system.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.areillan.game.content.skill.Skills;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.node.entity.player.info.Rights;
import org.areillan.game.system.mysql.SQLEntryHandler;
import org.areillan.game.system.mysql.SQLManager;
import org.areillan.game.world.GameWorld;

/**
 * Handles the sql handler.
 * @author Vexia
 */
public final class HighscoreSQLHandler extends SQLEntryHandler<Player> {

	/**
	 * Constructs a new {@code HighscoreSQLHandler} {@code Object}.
	 */
	public HighscoreSQLHandler(Player entry) {
		super(entry, "aincradc_global.highscores", "username", entry.getName());
	}

	@Override
	public void parse() throws SQLException {

	}

	@Override
	public void create() throws SQLException {
		if (entry.getDetails().getRights() == Rights.ADMINISTRATOR || GameWorld.getSettings().getWorldId() == 2 && !entry.getName().equalsIgnoreCase("nuaris")) {
			return;
		}
		StringBuilder b = new StringBuilder("INSERT highscores(username,overall_xp,total_level,ironManMode,attack_xp,defence_xp,strength_xp,hitpoints_xp,ranged_xp,prayer_xp,magic_xp,cooking_xp,woodcutting_xp,fletching_xp,fishing_xp,firemaking_xp,crafting_xp,smithing_xp,mining_xp,herblore_xp,agility_xp,thieving_xp,slayer_xp,farming_xp,runecrafting_xp,hunter_xp,construction_xp,summoning_xp,quest_xp) ");
		b.append("VALUES('" + value + "', '" + getTotalXp() + "', '" + entry.getSkills().getTotalLevel() + "', '" + entry.getIronmanManager().getMode().name() + "', ");
		int xp;
		for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
			xp = (int) entry.getSkills().getExperience(i);
			b.append("'" + xp + "'" + (i == Skills.SKILL_NAME.length - 1 ? "" : ","));
		}
		b.append(", '"+entry.getQuestRepository().getPoints() + "')");
		PreparedStatement statement = connection.prepareStatement(b.toString());
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void save() throws SQLException {
		if (entry.getDetails().getRights() == Rights.ADMINISTRATOR && !entry.getName().equalsIgnoreCase("nuaris")) {
			return;
		}
		super.read();
		if (result == null || !result.next()) {
			create();
			return;
		}
		StringBuilder b = new StringBuilder("UPDATE highscores SET overall_xp='" + getTotalXp() + "', total_level='" + entry.getSkills().getTotalLevel() + "', ironManMode='" + entry.getIronmanManager().getMode().name() + "', quest_xp='" + entry.getQuestRepository().getPoints() + "', ");
		int xp;
		for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
			xp = (int) entry.getSkills().getExperience(i);
			b.append(Skills.SKILL_NAME[i].toLowerCase() + "_xp='" + xp +"'" + (i == Skills.SKILL_NAME.length - 1 ? "" : ","));
		}
		b.append("WHERE username='" + value + "'");
		PreparedStatement statement = connection.prepareStatement(b.toString());
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	/**
	 * Sets the data.
	 * @param statement the statement.
	 * @param startIndex the start index.
	 * @throws SQLException the exception.
	 */
	public void setData(PreparedStatement statement, int startIndex) throws SQLException {
		for (int i = 0; i < Skills.SKILL_NAME.length; i++) {
			statement.setInt(startIndex + i, entry.getSkills().getStaticLevel(i));
		}
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}

	/**
	 * Gets the total exp.
	 * @return the exp.
	 */
	public int getTotalXp() {
		int total = 0;
		for (int skill = 0; skill < Skills.SKILL_NAME.length; skill++) {
			total += entry.getSkills().getExperience(skill);
		}
		return total;
	}

}