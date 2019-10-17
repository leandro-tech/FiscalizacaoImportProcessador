package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class ImportadorUF implements ProcessadorLinha {
	
	private UfDao ufDao;
	private EntityManager em;

	public ImportadorUF(EntityManager em) {
		this.em = em;
		ufDao = new UfDao(em);
 	}
	@Override
	public void processa(String linha) {
		em.getTransaction().begin();
		String csv[] = linha.split(";");
		Uf uf = new Uf();
		uf.setSigla(csv[1].toUpperCase().trim());
		uf.setNome(csv[0].toUpperCase().trim());
		ufDao.add(uf);
		em.getTransaction().commit();
	}
	
}
