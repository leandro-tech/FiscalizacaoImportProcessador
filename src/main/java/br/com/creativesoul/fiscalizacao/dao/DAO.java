package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Class<T> classe;
	private EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

	public void add(T objeto) {
			em.persist(objeto);
	}

	public void remove(T objeto) {
		em.remove(em.merge(objeto));
	}

	public void update(T objeto) {
			em.merge(objeto);
	}
	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		return em.createQuery(query).getResultList();
	}

	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);
		return instancia;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {

		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		return em
				.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}

}