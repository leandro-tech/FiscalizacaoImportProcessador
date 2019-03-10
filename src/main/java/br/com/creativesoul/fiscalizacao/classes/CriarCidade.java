package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarCidade {
	
	public Cidade criarCidade(EntityManager em, String nomeCidade, Uf uf) {
		
		if(nomeCidade.equals("Sem Informação")) {
			return null;
		}
		
		CidadeDao cidadeDao = new CidadeDao(em);
		Cidade cidade = cidadeDao.buscaPorCidade(nomeCidade);
		
    	if(cidade == null) {
    		cidade = new Cidade();
    		cidade.setNome(nomeCidade);
    		cidade.setUf(uf);
    		cidadeDao.add(cidade);
    	}
    	return cidade;
	}
}
