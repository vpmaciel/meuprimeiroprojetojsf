package br.com.util;

import java.util.Date;
import java.util.List;

import br.com.entidades.Pessoa;
import br.com.enums.SexoEnum; // Import do Enum adicionado
import br.com.jpa.JPAUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;

@Singleton
@Startup
public class InicializadorBanco {

    @PostConstruct
    public void inicializar() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            // Consulta para verificar se existe algum usuário cadastrado
            List<Pessoa> pessoas = em.createQuery("SELECT p FROM Pessoa p", Pessoa.class)
                                     .setMaxResults(1)
                                     .getResultList();

            // Se a tabela estiver vazia, cria o admin default
            if (pessoas.isEmpty()) {
                em.getTransaction().begin();

                Pessoa admin = new Pessoa();
                admin.setNome("Administrador");
                admin.setSobrenome("Sistema");
                admin.setLogin("admin");
                admin.setSenha("admin");
                admin.setPerfilUser("Administrador");
                admin.setAtivo(true);
                
                // Passa o valor constante do Enum diretamente
                admin.setSexo(SexoEnum.M); 
                
                admin.setNascimento(new Date());

                em.persist(admin);
                em.getTransaction().commit();

                System.out.println(">>> Usuário Administrador default criado com sucesso no PostgreSQL! <<<");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }
}