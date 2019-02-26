package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarCidade {
	
	CidadeDao cidadeDao = new CidadeDao();
	Cidade cidade = new Cidade();
	
	public CriarCidade(EntityManager em, String nomeCidade, Uf uf) {		
		DAO<Cidade> dao = new DAO<>(em, Cidade.class);
		cidade.setNome(nomeCidade);
		cidade.setUf(uf);
		
		if(!cidadeDao.checkCidade(em, cidade)) {
			dao.add(cidade);		
		}
		else {
			cidade = cidadeDao.cidadeExistente();
		}		
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
}
