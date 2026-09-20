package br.com.repository;

import br.com.entidades.Pessoa;

public interface IDAOPessoa {
    
    Pessoa consultar(String login, String senha);

}
