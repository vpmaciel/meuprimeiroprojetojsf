package br.com.entidades;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Integer idade;
    @Temporal(TemporalType.DATE)
    private Date nascimento;
    private char sexo;
    private String [] frameworks;
    private Boolean ativo;
    private String login;
    private String senha;    
    private String perfilUser;

    public String getPerfilUser() {
        return perfilUser;
    }

    public void setPerfilUser(String perfilUser) {
        this.perfilUser = perfilUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
    
    public String[] getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String[] frameworks) {
        this.frameworks = frameworks;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {	
        this.sexo = sexo;
    }

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

    public Integer getIdade() {
	return idade;
    }

    public void setIdade(Integer idade) {
	this.idade = idade;
    }

    public Date getNascimento() {
	return nascimento;
    }

    public void setNascimento(Date nascimento) {
	this.nascimento = nascimento;
    }

    @Override
    public int hashCode() {
	return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pessoa other = (Pessoa) obj;
	return Objects.equals(id, other.id);
    }

}
