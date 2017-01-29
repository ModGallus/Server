package org.areillan.game.system.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.areillan.game.component.ComponentDefinition;
import org.areillan.game.system.mysql.SQLEntryHandler;
import org.areillan.game.system.mysql.SQLManager;

/**
 * Handles the parsing of component definitions.
 * @author Vexia
 *
 */
public class ComponentSQLHandler extends SQLEntryHandler<Object> {

	/**
	 * Constructs a new {@Code ComponentSQLHandler} {@Code Object}
	 */
	public ComponentSQLHandler() {
		super(null, "aincradc_server.interface_configs", "", "");
	}

	@Override
	public void parse() throws SQLException {
		connection = getConnection();
		if (connection == null) {
			SQLManager.close(connection);
			return;
		}
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table);
		ResultSet set = statement.executeQuery();
		int id = -1;
		while (set.next()) {
			id = set.getInt("id");
			if (ComponentDefinition.getDefinitions().containsKey(id)) {
				ComponentDefinition.getDefinitions().get(id).parse(set);
			}
			ComponentDefinition.getDefinitions().put(id, new ComponentDefinition().parse(set));
		}
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void create() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}

	@Override
	public void save() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("");
		statement.executeUpdate();
		SQLManager.close(statement.getConnection());
	}
	
	@Override
	public Connection getConnection() {
		return SQLManager.getConnection();
	}
}
