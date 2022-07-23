package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			connection.commit(); // salva no banco

		} catch (Exception e) {
			try {
				connection.rollback(); // reverte a operação
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Usercursojava> listar() throws Exception {

		List<Usercursojava> list = new ArrayList<Usercursojava>();

		String sql = "SELECT * FROM usercursojava";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			Usercursojava usercursojava = new Usercursojava();
			usercursojava.setId(resultado.getLong("id"));
			usercursojava.setNome(resultado.getString("nome"));
			usercursojava.setEmail(resultado.getString("email"));

			list.add(usercursojava);

		}

		return list;
	}

	public Usercursojava buscar(Long id) throws Exception {

		Usercursojava retorno = new Usercursojava();

		String sql = "SELECT * FROM usercursojava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {//vai retornar apenas um ou nenhum

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));


		}

		return retorno;
	}

}
