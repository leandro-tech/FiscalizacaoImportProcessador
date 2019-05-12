package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Cidade;


public class CidadeDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final DAO<Cidade> dao;
	private final  EntityManager em;

	public CidadeDao(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Cidade>(em, Cidade.class);
	}

	public void add(Cidade objeto) {
		dao.add(objeto);
	}

	public void update(Cidade objeto) {
		dao.update(objeto);
	}

	public Cidade buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}
	
	public Cidade buscaPorCidadeEUf(String cidade, Long id_uf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Cidade u ");
		jpql.append(" 				join u.uf b ");
		jpql.append(" where ");
		jpql.append("   u.nome = :pCidade ");
		jpql.append("   and b.id = :pUf ");

		TypedQuery<Cidade> query = em.createQuery(jpql.toString(), Cidade.class);

		query.setParameter("pCidade", cidade);
		query.setParameter("pUf", id_uf);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
