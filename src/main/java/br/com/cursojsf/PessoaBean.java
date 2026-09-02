package br.com.cursojsf;


import java.io.Serializable;


import br.com.dao.DAOGeneric;
import br.com.entidades.Pessoa;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named(value = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa= new Pessoa();
	private DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
	
	public String salvar() {
	    daoGeneric.salvar(pessoa);
	    return "";
	}
	
	public Pessoa getPessoa() {
	    return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
	    this.pessoa = pessoa;
	}
	public DAOGeneric<Pessoa> getDaoGeneric() {
	    return daoGeneric;
	}
	public void setDaoGeneric(DAOGeneric<Pessoa> daoGeneric) {
	    this.daoGeneric = daoGeneric;
	}
	
	
	
}