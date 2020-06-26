package br.com.farmacia.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.dao.FornecedoresDAO;
import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;





public class ProdutoDAOTeste {
@Ignore
	public void salvar() throws SQLException {
	Produtos p1 = new Produtos();
	p1.setDescricao("Dramin");
	p1.setPreco(2.00);
	p1.setQuantidade(10);
    
	Fornecedores f1 = new Fornecedores();
	f1.setCodigo(5);
	p1.setFornecedores(f1);
	
	ProdutoDAO dao = new ProdutoDAO();
	
	dao.salvar(p1);
	
	}


	@Ignore
public void excluir() throws SQLException {
	Produtos p = new Produtos();
	p.setCodigo(4);
	
	ProdutoDAO dao = new ProdutoDAO();
	dao.excluir(p);
}
	
	@Ignore
	public ArrayList<Produtos> buscarNome(String nome) {
		ArrayList<Produtos> listaRetorno = new ArrayList<Produtos>();
		ProdutoDAO dao = new ProdutoDAO();
		
		try {
			listaRetorno = dao.buscarNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	
	@Test
	public void editar()  {
		Produtos p = new Produtos();
		p.setCodigo(5);
		p.setDescricao("Cataflan");
		p.setPreco(1.00);
		p.setQuantidade(2);
		
		Fornecedores f = new Fornecedores();
		f.setCodigo(3);
		p.setFornecedores(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.editar(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

