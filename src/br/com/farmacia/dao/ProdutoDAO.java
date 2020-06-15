package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

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

	
	public static void main(String[] args) {
	
		Produtos p3 = new Produtos();
		p3.setDescricao("Produto 1");
		p3.setPreco(2.0);
		p3.setQuantidade(2);

        Fornecedores f= new Fornecedores();
        f.setCodigo(3);
        p3.setFornecedores(f);

	 ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.salvar(p3);
			System.out.println("salvo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
