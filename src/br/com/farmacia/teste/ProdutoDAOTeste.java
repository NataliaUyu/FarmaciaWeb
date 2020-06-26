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
	public void listar() throws SQLException {
		 ProdutoDAO dao = new ProdutoDAO();
		 ArrayList<Produtos> lista = dao.listar();
		
		 for(Produtos p : lista) {
			 System.out.println("codigo produto  " + p.getCodigo());
			 System.out.println("nome produto  " + p.getDescricao());
			 System.out.println("preco  " + p.getPreco());
			 System.out.println("quantidade  " + p.getQuantidade());
			 System.out.println("codigo fornecedor  " + p.getFornecedores().getCodigo());
			 System.out.println("nome fornecedor  " + p.getFornecedores().getDescricao());
			 
			 
		 }
	}
	
	@Test
	public void editar() throws SQLException {
		Produtos p = new Produtos();
		p.setCodigo(5);
		p.setDescricao("Cataflan");
		p.setPreco(1.00);
		p.setQuantidade(2);
		
		Fornecedores f = new Fornecedores();
		f.setCodigo(3);
		p.setFornecedores(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
	}


}

