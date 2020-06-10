package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores");
		sql.append("(descricao)");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public static void main(String[] args) {
//		Fornecedores f1 = new Fornecedores();
//		f1.setDescricao("Descriçao 1");
//		Fornecedores f2 = new Fornecedores();
//		f2.setDescricao("Descriçao 2");
//
//		FornecedoresDAO dao = new FornecedoresDAO();
//		try {
//			dao.salvar(f1);
//			dao.salvar(f2);
//			System.out.println("Salvo com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Erro ao salvor");
//			e.printStackTrace();
//		}

		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2);

		FornecedoresDAO dao = new FornecedoresDAO();

		try {
			dao.excluir(f1);
			System.out.println("Deletado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao Deletar");
			e.printStackTrace();
		}

	}
}
