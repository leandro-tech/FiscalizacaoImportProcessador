package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class UfProcessadorLinha implements ProcessadorLinha {

	private UfDao ufDao;
	private EntityManager em;
	
	public UfProcessadorLinha(EntityManager em) {
		this.em = em;
		ufDao = new UfDao(em);
	}

	@Override
	public void processa(String linha) {
		em.getTransaction().begin();
		try {
			String campo[] = linha.split(";");
			Uf uf = new Uf();
			uf.setSigla(campo[1]);
			uf.setNome(campo[0].toUpperCase());
			ufDao.add(uf);
			em.getTransaction().commit();
		} catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println(ex.getMessage());
		}
	}

}
