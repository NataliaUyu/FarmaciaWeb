package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;
import br.com.farmacia.util.HibernateUtil;




public class ProdutoDAO {
	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo)");
		sql.append("VALUES (?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setInt(3, p.getQuantidade());
		comando.setInt(4, p.getFornecedores().getCodigo());

		comando.executeUpdate();
	}


	
	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1, p.getCodigo());
		comando.executeUpdate();
	}

	

	public ArrayList<Produtos> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
		
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));

			Produtos p = new Produtos();
			p.setCodigo(resultado.getInt("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setFornecedores(f);
			
			
			lista.add(p);
		}
		return lista;
	}
	
	
	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setInt(3, p.getQuantidade());
		comando.setInt(4, p.getFornecedores().getCodigo());
		comando.setInt(5, p.getCodigo());
		comando.executeUpdate();
	}

	
	// Testando outro jeito de listar
	 public ArrayList<Produtos> buscarTodos() throws Exception {

	        ArrayList<Produtos> listaRetorno = new ArrayList<Produtos>();
	        Session sessao = HibernateUtil.getSessionFactory().openSession();
	        Criteria criteria = sessao.createCriteria(Produtos.class);
	        criteria.addOrder(Order.asc("codigo"));
	        listaRetorno = (ArrayList<Produtos>) criteria.list();
	        sessao.close();
	        return listaRetorno;
	    }

}

