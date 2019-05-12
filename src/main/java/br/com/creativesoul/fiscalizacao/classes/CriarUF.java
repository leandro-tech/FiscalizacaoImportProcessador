package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarUF {
	
	public Uf criarUF(EntityManager em, String nomeUF, String sigla) {
		
		if(nomeUF.equals("Sem Informação")) {
			return null;
		}
		
		UfDao ufDao = new UfDao(em);
		Uf uf = ufDao.buscaPorNome(nomeUF);
		
    	if(uf == null) {
    		uf = new Uf();
    		uf.setSigla(sigla);
    		uf.setNome(nomeUF);
    		ufDao.add(uf);
    	}
    	return uf;
 	}
	
}
