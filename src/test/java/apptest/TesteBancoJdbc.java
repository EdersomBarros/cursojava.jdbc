package apptest;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UsercursoDAO;
import model.Usercursojava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {

		UsercursoDAO usercursoDAO = new UsercursoDAO();
		Usercursojava usercursojava = new Usercursojava();

		usercursojava.setId(6L);
		usercursojava.setNome("Johnva");
		usercursojava.setEmail("jahho@hotmail.com");

		usercursoDAO.salvar(usercursojava);
	}

}
