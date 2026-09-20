package br.com.cursojsf;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import br.com.dao.DAOGeneric;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repository.IDAOLancamento;
import br.com.repository.IDAOLancamentoImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "lancamentoBean")
@ViewScoped
public class LancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Lancamento lancamento = new Lancamento();
    private DAOGeneric<Lancamento> daoGeneric = new DAOGeneric<Lancamento>();
    private List<Lancamento> lancamentoList = new ArrayList<Lancamento>();
    private Pessoa usuario = new Pessoa();
    private IDAOLancamento daoLancamento = new IDAOLancamentoImpl();
    
    @PostConstruct
    public void carregarLancamentos(){
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
	
	
	lancamentoList = daoLancamento.consultar(pessoaUser.getId());
	
	
	
    }

    public String salvar() {

	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
	
	lancamento.setUsuario(pessoaUser);
	lancamento = daoGeneric.merge(lancamento);
	
	carregarLancamentos();	
	
	return "";
    }

    public String novo() {
	lancamento = new Lancamento();
	return "";
    }

    public String remove() {
	daoGeneric.delete(lancamento);
	lancamento = new Lancamento();
	carregarLancamentos();
	return "";
    }

    public Lancamento getLancamento() {
	return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
	this.lancamento = lancamento;
    }

    public Pessoa getUsuario() {
	return usuario;
    }

    public void setUsuario(Pessoa usuario) {
	this.usuario = usuario;
    }

    public DAOGeneric<Lancamento> getDaoGeneric() {
	return daoGeneric;
    }

    public void setDaoGeneric(DAOGeneric<Lancamento> daoGeneric) {
	this.daoGeneric = daoGeneric;
    }

    public List<Lancamento> getLancamentoList() {
	return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
	this.lancamentoList = lancamentoList;
    }
}