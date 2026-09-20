package br.com.repository;
import br.com.entidades.Lancamento;

import java.util.List;

public interface IDAOLancamento {
    
    List<Lancamento> consultar(Long codUser);

}
