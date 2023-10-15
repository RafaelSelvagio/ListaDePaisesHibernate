package dao;

import model.Pais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PaisHibernate {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private SessionFactory sessionFactory;
	
	public PaisHibernate() {
		// Inicializa a fábrica de sessões do Hibernate
        sessionFactory = new Configuration().configure().buildSessionFactory();
	}
    
    public void adicionarPais(Pais pais) {
    	entityManager.getTransaction().begin();
    	entityManager.persist(pais);
    	entityManager.getTransaction().commit();
    }
    
    public boolean atualizarPais(Integer id, String nome, String capital) {
    	Pais pais = buscarPaisPorId(id);
    	
    	entityManager.getTransaction().begin();
    	pais.setNome(nome);
    	pais.setCapital(capital);
    	entityManager.getTransaction().commit();
    	
    	return true;
    }
    
    public Pais buscarPaisPorId(Integer id) {
    	return entityManager.find(Pais.class, id);
    }
    
    public Pais buscarPaisPorNome(String nome) {
    	// Abre uma nova sessão do Hibernate
        Session session = sessionFactory.openSession();

        try {
            // Inicia uma transação
            session.beginTransaction();

            // Cria uma consulta HQL para buscar um país com base no nome
            String hql = "FROM Pais WHERE nome = :nome";
            Pais pais = (Pais) session.createQuery(hql).setParameter("nome", nome).uniqueResult();
            
            // Confirma a transação
            session.getTransaction().commit();

            return pais;
        } catch (Exception e) {
            // Em caso de erro, faça o tratamento adequado, como um rollback da transação.
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            // Fecha a sessão do Hibernate
            session.close();
        }
    }
    
    public List<Pais> listarPaises() {
    	// Abre uma nova sessão do Hibernate
        Session session = sessionFactory.openSession();

        try {
            // Inicia uma transação
            session.beginTransaction();

            // Cria uma consulta HQL para buscar todos os países
            String hql = "FROM Pais ORDER BY nome";
            Query<Pais> query = session.createQuery(hql, Pais.class);
            
            // Execute a consulta e obtenha a lista de países
            List<Pais> paises = query.list();
            
            // Confirma a transação
            session.getTransaction().commit();

            return paises;
        } catch (Exception e) {
            // Em caso de erro, faça o tratamento adequado, como um rollback da transação.
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null; // Retorna null em caso de erro
        } finally {
            // Fecha a sessão do Hibernate
            session.close();
        }
    }
    
    public boolean removerPais(Pais pais) {
    	// Variavel de retorno
    	boolean retorno = false;
    	
    	// Abre uma nova sessão do Hibernate
        Session session = sessionFactory.openSession();

        try {
            // Inicia uma transação
            session.beginTransaction();

            // Verifica se o objeto Pais está associado à sessão. Se não, faz a associação.
            // Isso é importante quando o objeto foi obtido fora da sessão.
            if (!session.contains(pais)) {
                pais = session.get(Pais.class, pais.getId());
            }

            // Remove o objeto Pais da sessão
            session.delete(pais);

            // Confirma a transação
            session.getTransaction().commit();
            
            // Retorne true
            retorno = true;
        } catch (Exception e) {
            // Em caso de erro, faça o tratamento adequado, como um rollback da transação.
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                retorno = false;
            }
            e.printStackTrace();
        } finally {
            // Fecha a sessão do Hibernate
            session.close();
        }
        
        return retorno;
    }

}
