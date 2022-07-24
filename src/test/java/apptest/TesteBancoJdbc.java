package apptest;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UsercursoDAO;
import model.Usercursojava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {

		UsercursoDAO usercursoDAO = new UsercursoDAO();
		Usercursojava usercursojava = new Usercursojava();

		usercursojava.setNome("Johnva");
		usercursojava.setEmail("jahho@hotmail.com");

		usercursoDAO.salvar(usercursojava);
	}

	@Test
	public void initListar() {

		UsercursoDAO dao = new UsercursoDAO();

		try {
			List<Usercursojava> list = dao.listar();
			for (Usercursojava usercursojava : list) {
				System.out.println(usercursojava);
				System.out.println("-------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initBuscar() {

		UsercursoDAO dao = new UsercursoDAO();
		try {
			Usercursojava usercursojava = dao.buscar(2L);
			System.out.println(usercursojava);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void initAtualizar() {
		try {
			UsercursoDAO dao = new UsercursoDAO();

			Usercursojava objetoBanco = dao.buscar(5L);
			
			objetoBanco.setNome("nome mudado com método atualizar");

			dao.atualizar(objetoBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
