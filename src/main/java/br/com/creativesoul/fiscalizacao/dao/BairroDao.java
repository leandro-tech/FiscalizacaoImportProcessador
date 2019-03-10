package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Bairro;

public class BairroDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private final DAO<Bairro> dao;
	private final  EntityManager em;

	public BairroDao(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Bairro>(em, Bairro.class);
	}

	public void add(Bairro objeto) {
		dao.add(objeto);
	}

	public void update(Bairro objeto) {
		dao.update(objeto);
	}

	public Bairro buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}
	
	public Bairro buscaPorBairro(String bairro, Long cidade, Long uf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select b from Bairro b");
		jpql.append("     join b.cidade c");
		jpql.append("     join b.uf u");
		jpql.append(" where ");
		jpql.append("   b.nome = :pNome ");
		jpql.append("   and c.id = :pCidade");
		jpql.append("   and u.id = :pUf");
		
		TypedQuery<Bairro> query = em.createQuery(jpql.toString(), Bairro.class);
		
		query.setParameter("pNome", bairro);
		query.setParameter("pCidade", cidade);
		query.setParameter("pUf", uf);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
