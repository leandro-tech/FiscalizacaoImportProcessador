package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.BairroDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarBairro {
	
	public Bairro criarBairro(EntityManager em, String nomeBairro, Cidade cidade, Uf uf) {
		
		if(nomeBairro.equals("Sem Informação")) {
			return null;
		}
		
		BairroDao bairroDao = new BairroDao(em);
		Bairro bairro = bairroDao.buscaPorBairro(nomeBairro, cidade.getId(), uf.getId());
    	if(bairro == null) {
    		bairro = new Bairro();
    		bairro.setNome(nomeBairro);
    		bairro.setCidade(cidade);
    		bairro.setUf(uf);
    		bairroDao.add(bairro);
    	}
    	return bairro;
	}
}
