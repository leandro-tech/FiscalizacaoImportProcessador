package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.entity.Erros;

public class ErrosDao implements Serializable{
	
private static final long serialVersionUID = 1L;	
	
	private final DAO<Erros> dao;
	private final EntityManager em;

	public ErrosDao (EntityManager em) {
		this.em = em;
		this.dao = new DAO<Erros>(em, Erros.class);
	}

	public void add(Erros objeto) {
		dao.add(objeto);
	}

	public void update(Erros objeto) {
		dao.update(objeto);
	}

	public Erros buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

}
