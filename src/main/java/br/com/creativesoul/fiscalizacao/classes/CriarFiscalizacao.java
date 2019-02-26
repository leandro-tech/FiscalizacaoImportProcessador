package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.Fiscalizacao;

public class CriarFiscalizacao {
	
	Fiscalizacao fiscal = new Fiscalizacao();
	
	
	public CriarFiscalizacao(EntityManager em, Empresa empresa) {
		DAO<Fiscalizacao> dao = new DAO<>(em, Fiscalizacao.class);
		fiscal.setEmpresa(empresa);
		fiscal.setData(empresa.getDt_atualizacao());
		fiscal.setRazao(empresa.getRazao());
		fiscal.setLogradouro(empresa.getLogradouro());
		fiscal.setCep(empresa.getCep());
		fiscal.setCidade(empresa.getCidade());
		fiscal.setBairro(empresa.getBairro());
		fiscal.setUf(empresa.getUf());
		dao.add(fiscal);
	}
	
	public Fiscalizacao getFiscalizacao() {
		return fiscal;
	}

}
