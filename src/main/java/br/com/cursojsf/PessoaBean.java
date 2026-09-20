package br.com.cursojsf;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.dao.DAOGeneric;
import br.com.entidades.Framework;
import br.com.entidades.Pessoa;
import br.com.repository.IDAOPessoa;
import br.com.repository.IDAOPessoaImpl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import br.com.enums.SexoEnum;

@Named(value = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
    private DAOGeneric<Framework> daoGenericFramework = new DAOGeneric<Framework>();
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private IDAOPessoa idaoPessoa = new IDAOPessoaImpl();

    @PostConstruct
    public void carregarPessoas() {
	pessoas = daoGeneric.getListEntity(Pessoa.class);
    }

    public String logar() {
	Pessoa pessoaUser = idaoPessoa.consultar(pessoa.getLogin(), pessoa.getSenha());

	if (pessoaUser != null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
	    return "primeirapagina.jsf";
	}
	return "index.jsf";
    }

    public String deslogar() {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	externalContext.getSessionMap().remove("usuarioLogado");
	HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
		.getRequest();
	httpServletRequest.getSession().invalidate();
	return "index.jsf";
    }

    public boolean permiteAcesso(String acesso) {

	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");

	if (pessoaUser == null || pessoaUser.getPerfilUser() == null) {
	    return false;
	}

	return pessoaUser.getPerfilUser().equals(acesso);
    }

    public String salvar() {
	pessoa = daoGeneric.merge(pessoa);
	carregarPessoas();
	mostrarMsg("Cadastrado salvo com sucesso !");
	return "";
    }

    private void mostrarMsg(String msg) {
	FacesContext context = FacesContext.getCurrentInstance();
	FacesMessage message = new FacesMessage(msg);
	context.addMessage(null, message);

    }

    public String novo() {
	pessoa = new Pessoa();
	return "";
    }

    public String limpar() {
	pessoa = new Pessoa();
	return "";
    }

    public String remove() {
	daoGeneric.delete(pessoa);
	carregarPessoas();
	mostrarMsg("Cadastrado removido com sucesso !");
	return "";
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public boolean getAdministrador() {
	return permiteAcesso("Administrador");
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

    public void pesquisarCep(AjaxBehaviorEvent ajaxBehaviorEvent) {
	try {
	    URL url = URI.create("https://viacep.com.br/ws/" + pessoa.getCep() + "/json").toURL();
	    URLConnection connection = url.openConnection();
	    InputStream inputStream = connection.getInputStream();
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	    String cep = "";
	    StringBuilder jsonCep = new StringBuilder();

	    while ((cep = bufferedReader.readLine()) != null) {
		jsonCep.append(cep);
	    }

	    Pessoa gsonPessoa = new Gson().fromJson(jsonCep.toString(), Pessoa.class);

	    pessoa.setLogradouro(gsonPessoa.getLogradouro());
	    pessoa.setComplemento(gsonPessoa.getComplemento());
	    pessoa.setUnidade(gsonPessoa.getUnidade());
	    pessoa.setBairro(gsonPessoa.getBairro());
	    pessoa.setLocalidade(gsonPessoa.getLocalidade());
	    pessoa.setUf(gsonPessoa.getUf());
	    pessoa.setEstado(gsonPessoa.getEstado());
	    pessoa.setRegiao(gsonPessoa.getRegiao());
	    pessoa.setIbge(gsonPessoa.getIbge());
	    pessoa.setGia(gsonPessoa.getGia());
	    pessoa.setDdd(gsonPessoa.getDdd());
	    pessoa.setSiafi(gsonPessoa.getSiafi());
	    pessoa.setCep(gsonPessoa.getCep());

	    System.out.println(pessoa.getNome());
	    System.out.println(pessoa.getSexo());
	} catch (Exception exception) {
	    exception.printStackTrace();
	    mostrarMsg("Erro ao consultar o cep");
	}
    }

    public List<br.com.entidades.Framework> getListaFrameworks() {
	// Busca a lista diretamente do banco via DAOGeneric
	return daoGenericFramework.getListEntity(Framework.class);
    }

    public SexoEnum[] getListaSexos() {
	return SexoEnum.values();
    }
}