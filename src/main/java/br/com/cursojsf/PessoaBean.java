package br.com.cursojsf;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

// Nota: Sempre defina um escopo (Request, Session, View, etc.)

@Named(value = "pessoaBean")
@ViewScoped
public class PessoaBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String sobrenome;
	private String nomeCompleto;
	private boolean termosAceitos = true;
	private List<String> nomes = new ArrayList<String>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public boolean getTermosAceitos() {
		return termosAceitos;
	}
	public void setTermosAceitos(boolean termosAceitos) {
		this.termosAceitos = termosAceitos;
	}	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String mostrarNome() {
		nomeCompleto = nome + " " + sobrenome;
		return nomeCompleto;
	}	
	public String addNome() {
		nomes.add(nome);
		return "";
	}
	public List<String> getNomes() {
		return nomes;
	}
	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}	
}