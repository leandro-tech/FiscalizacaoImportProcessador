package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Uf;

public class UfDao implements Serializable {

	private static final long serialVersionUID = 1L;

	private final DAO<Uf> dao;
	private final EntityManager em;

	public UfDao(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Uf>(em, Uf.class);
	}

	public void add(Uf objeto) {
		dao.add(objeto);
	}

	public void update(Uf objeto) {
		dao.update(objeto);
	}

	public Uf buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public Uf buscaPorNome(String nome) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Uf u ");
		jpql.append(" where ");
		jpql.append("   u.nome = :pNome ");

		TypedQuery<Uf> query = em.createQuery(jpql.toString(), Uf.class);

		query.setParameter("pNome", nome);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
