package br.com.cursojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import br.com.repository.IDAOPessoa;
import br.com.repository.IDAOPessoaImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.component.FacesComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private IDAOPessoa idaoPessoa = new IDAOPessoaImpl();

    @PostConstruct
    public void carregarPessoas() {
	pessoas = daoGeneric.getListEntity(Pessoa.class);
    }
    
    public String logar() {
	Pessoa pessoaUser = idaoPessoa.consultarUsuario(pessoa.getLogin(),pessoa.getSenha());
	
	if(pessoaUser != null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    externalContext.getSessionMap().put("usuariologado", pessoaUser);
	    return "primeirapagina.jsf";
	}
	return "index.jsf";
    }

    public String salvar() {
	pessoa = daoGeneric.merge(pessoa);
	carregarPessoas();
	return "";
    }

    public String novo() {
	pessoa = new Pessoa();
	return "";
    }

    public String remove() {
	daoGeneric.delete(pessoa);
	carregarPessoas();
	return "";
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
	return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
	this.pessoas = pessoas;
    }

    public DAOGeneric<Pessoa> getDaoGeneric() {
	return daoGeneric;
    }

    public void setDaoGeneric(DAOGeneric<Pessoa> daoGeneric) {
	this.daoGeneric = daoGeneric;
    }
}