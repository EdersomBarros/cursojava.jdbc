package dao;

import java.sql.Connection;

import conexaojdbc.SingleConnection;

public class UsercursoDAO {

	private Connection connection;

	public UsercursoDAO() {

		connection = SingleConnection.getConnection();

	}

}
