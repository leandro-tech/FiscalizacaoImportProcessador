package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.BairroDao;
import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarBairro {
	
	BairroDao bairroDao = new BairroDao();
	Bairro bairro = new Bairro();
	
	public CriarBairro(EntityManager em, String nomeBairro, Cidade cidade, Uf uf) {	
		DAO<Bairro> dao = new DAO<>(em, Bairro.class);
		bairro.setNome(nomeBairro);
		bairro.setCidade(cidade);
		bairro.setUf(uf);
		
		if(!bairroDao.checkBairro(em, bairro)) {
			dao.add(bairro);	
		}
		else {
			bairro = bairroDao.bairroExistente();
		}
	}
	
	public Bairro getBairro() {
		return bairro;
	}
	
	public Cidade getCidade() {
		return bairro.getCidade();
	}
	
	public Uf getUf() {
		return bairro.getUf();
	}
	
	
}
