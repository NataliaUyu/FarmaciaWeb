package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();
	}
	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());
		comando.executeUpdate();
	}

	public Fornecedores buscarCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setInt(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorna = null;
		
		if(resultado.next()) {
			retorna = new Fornecedores();
			retorna.setCodigo(resultado.getInt("codigo"));
			retorna.setDescricao(resultado.getString("descricao"));
			
		}
		return retorna;
	}
	
	public ArrayList<Fornecedores> buscarDescricao(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista  = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
			}
		return lista;
		
	}
	
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista  = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
			}
		return lista;
	}
	public static void main(String[] args) {
		//salvar
	
		Fornecedores f3 = new Fornecedores();
		f3.setDescricao("Fornecedor 9 ");

		FornecedoresDAO dao = new FornecedoresDAO();
		try {
		
			dao.salvar(f3);
			System.out.println("Salvo com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao salvor");
			e.printStackTrace();
		}

		//excluir
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(2);
//
//		FornecedoresDAO dao = new FornecedoresDAO();
//
//		try {
//			dao.excluir(f1);
//			System.out.println("Deletado com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Erro ao Deletar");
//			e.printStackTrace();
//		}
//       
		//alterar
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(1);
//        f1.setDescricao("Teste");
//		FornecedoresDAO dao = new FornecedoresDAO();
//
//		try {
//			dao.editar(f1);
//			System.out.println("Alterado com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Erro ao Alterar");
//			e.printStackTrace();
//		}
//	}
		//pesquisar
//		Fornecedores f1 = new Fornecedores();
//		f1.setCodigo(3);
//        
//		FornecedoresDAO dao = new FornecedoresDAO();
//		try {
//		
//			dao.buscarCodigo(f1);
//	
//			System.out.println("Resultado 1:");
//		
//		} catch (SQLException e) {
//			System.out.println("Erro ao pesquisar");
//			e.printStackTrace();
//		}
		
		//listar
//		FornecedoresDAO dao = new FornecedoresDAO();
//		try {
//		
//			ArrayList<Fornecedores> lista = dao.listar();
//	
//			for(Fornecedores f : lista) {
//			System.out.println("lista:" + f);
//			}
//		
//		} catch (SQLException e) {
//			System.out.println("Erro ao pesquisar");
//			e.printStackTrace();
//		}
		
//		Fornecedores f1 = new Fornecedores();
//		f1.setDescricao("te");
//		FornecedoresDAO dao = new FornecedoresDAO();
//		try {
//			
//				ArrayList<Fornecedores> lista = dao.buscarDescricao(f1);
//		
//				for(Fornecedores f : lista) {
//				System.out.println("lista:" + f);
//				}
//			
//			} catch (SQLException e) {
//				System.out.println("Erro ao pesquisar");
//				e.printStackTrace();
//			}
}
	}
