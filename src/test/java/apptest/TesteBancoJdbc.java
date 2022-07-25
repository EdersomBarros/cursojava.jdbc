package apptest;

import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UsercursoDAO;
import model.BeanUserFone;
import model.Telefone;
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

	@Test
	public void initDeletar() {

		try {
			UsercursoDAO dao = new UsercursoDAO();
			dao.deletar(7L);

		} catch (Exception e) {

		} 

	}
	@Test
	public void testeInsertTelefone() {
		
		Telefone telefone = new Telefone();
		telefone.setNumero("(55) 95555-6677");
		telefone.setTipo("Comercial");
		telefone.setUsuario(9L);
		
		UsercursoDAO dao = new UsercursoDAO();
		dao.salvarTelefone(telefone);
		
		
	}
	@Test
	public void testeCarregaFoneUser() {
		
		UsercursoDAO dao = new UsercursoDAO();
		
		List<BeanUserFone> beanUserFones=dao.listaUserFone(10L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("___________________________________");
			
		}
	}
	@Test
	public void testeDeleteUserFone() {
		UsercursoDAO dao = new UsercursoDAO();
		dao.deleteFonesPorUser(10L);
		
		
	}
	
	
	
	
	
	
}
