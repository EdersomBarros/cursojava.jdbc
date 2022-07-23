package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexaojdbc.SingleConnection;
import model.Usercursojava;

public class UsercursoDAO {

	private Connection connection;

	public UsercursoDAO() {

		connection = SingleConnection.getConnection();

	}

	public void salvar(Usercursojava usercursojava) {
		try {
			String sql = "insert into usercursojava (id,nome,email) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setLong(1, usercursojava.getId());
			insert.setString(2, usercursojava.getNome());
			insert.setString(3, usercursojava.getEmail());
			insert.execute();
			connection.commit(); //salva no banco
			
		} catch (Exception e) {
			try {
				connection.rollback(); //reverte a operação
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
