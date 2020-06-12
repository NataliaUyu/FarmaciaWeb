package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.farmacia.dao.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private  Fornecedores fornecedores;
	private ListDataModel<Fornecedores> itens;

	public ListDataModel<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fornecedores> itens) {
		this.itens = itens;
	}

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			ArrayList<Fornecedores> lista = dao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
		} catch (SQLException e) {
               JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}

	}
	
	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}
	
	public void novo() {
		
		try {
			FornecedoresDAO dao = new FornecedoresDAO();

			dao.salvar(fornecedores);
			ArrayList<Fornecedores> lista = dao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
            JSFUtil.adicionarMensagemSucesso("Fornecedor Salvo com Sucesso!");

		} catch (SQLException e) {
            JSFUtil.adicionarMensagemErro("ex.getMessage()");

			e.printStackTrace();
		}
	}
	
	public void prepararExcluir() {
		fornecedores = itens.getRowData();
		
	}
	public void excluir() {
		
		try {
			FornecedoresDAO dao = new FornecedoresDAO();

			dao.excluir(fornecedores);
			ArrayList<Fornecedores> lista = dao.listar();
			itens = new ListDataModel<Fornecedores>(lista);
            JSFUtil.adicionarMensagemSucesso("Fornecedor Excluido com Sucesso!");

		} catch (SQLException e) {
          JSFUtil.adicionarMensagemErro("Erro ao excluir fornecedor que tenha produtos associados");

			e.printStackTrace();
		}
	}
}
