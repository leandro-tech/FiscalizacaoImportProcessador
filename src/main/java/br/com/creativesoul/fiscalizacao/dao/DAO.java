package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private final Class<T> classe;
	EntityManager em;

	public DAO(EntityManager em, Class<T> classe) {
		this.classe = classe;
		this.em = em;
	}

//	public void add(T objeto) {
//		em.persist(objeto);
//	}
//
//	public void remove(T objeto) {
//		em.remove(em.merge(objeto));
//	}
//
//	public void atualiza(T objeto) {
//		em.merge(objeto);
//	}

//	public List<T> listaTodos() {
//
//		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
//		query.select(query.from(classe));
//
//		List<T> lista = em.createQuery(query).getResultList();
//
//		return lista;
//	}
//
//	public T buscaPorId(Long id) {
//		T instancia = em.find(classe, id);
//		return instancia;
//	}
//
//	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
//
//		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
//		query.select(query.from(classe));
//
//		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
//				.setMaxResults(maxResults).getResultList();
//
//		return lista;
//	}

}