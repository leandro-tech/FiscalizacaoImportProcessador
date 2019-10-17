package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.entity.Fiscalizacao;

public class FiscalizacaoDao implements Serializable{
	
private static final long serialVersionUID = 1L;	
	
	private final DAO<Fiscalizacao> dao;
	private final EntityManager em;

	public FiscalizacaoDao(EntityManager em) {
		this.em = em;
		this.dao = new DAO<Fiscalizacao>(em, Fiscalizacao.class);
	}

	public void add(Fiscalizacao objeto) {
		dao.add(objeto);
	}

	public void update(Fiscalizacao objeto) {
		dao.update(objeto);
	}

	public Fiscalizacao buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}
	
}
