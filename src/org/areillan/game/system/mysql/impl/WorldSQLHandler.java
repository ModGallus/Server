package org.areillan.game.system.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.areillan.game.content.skill.Skills;
import org.areillan.game.node.entity.player.Player;
import org.areillan.game.system.mysql.SQLEntryHandler;
import org.areillan.game.system.mysql.SQLManager;

public class WorldSQLHandler extends SQLEntryHandler<Player>{

	public WorldSQLHandler(Player entry) {
		super(entry, "aincradc_global.members", "username", entry.getName());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() throws SQLException {
		// TODO Auto-generated method stub
		super.read();
		if (result == null || !result.next()) {
			create();
			return;
		}
		StringBuilder b = new StringBuilder("UPDATE members SET current_world='2'");
		b.append("WHERE username='" + value + "'");
		PreparedStatement statement = connection.prepareStatement(b.toString());
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}

}
