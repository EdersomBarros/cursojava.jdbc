package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Usercursojava;

public class UsercursoDAO {

	private Connection connection;

	public UsercursoDAO() {

		connection = SingleConnection.getConnection();

	}

	public void salvar(Usercursojava usercursojava) {
		try {
			String sql = "insert into usercursojava (nome,email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setString(1, usercursojava.getNome());
			insert.setString(2, usercursojava.getEmail());
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

	public void salvarTelefone(Telefone telefone) {

		try {
			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa)VALUES (?, ?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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

		while (resultado.next()) {// vai retornar apenas um ou nenhum

			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {

		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

		String sql = "SELECT nome, numero, email from telefoneuser as fone ";
		sql += " inner join usercursojava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += "where userp.id = " + idUser;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserFones.add(userFone);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanUserFones;

	}

	public void atualizar(Usercursojava usercursojava) {
		try {
			String sql = "update usercursojava set nome = ? where id = " + usercursojava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usercursojava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {
		try {
			String sql = "delete from usercursojava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
