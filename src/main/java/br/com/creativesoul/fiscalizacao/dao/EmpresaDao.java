package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Empresa;

public class EmpresaDao implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	private final DAO<Empresa> dao;
	private final EntityManager em;

	public EmpresaDao(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Empresa>(em, Empresa.class);
	}

	public void add(Empresa objeto) {
		dao.add(objeto);
	}

	public void update(Empresa objeto) {
		dao.update(objeto);
	}

	public Empresa buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}
	
	public Empresa buscaPorEmpresa(String cnpj) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select u from Empresa u ");
		jpql.append(" where ");
		jpql.append(" u.cnpj = :pCnpj ");
		
		TypedQuery<Empresa> query = em.createQuery(jpql.toString(), Empresa.class);
		
		query.setParameter("pCnpj", cnpj);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
