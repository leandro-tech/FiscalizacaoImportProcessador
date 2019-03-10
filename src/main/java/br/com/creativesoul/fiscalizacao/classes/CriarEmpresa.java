package br.com.creativesoul.fiscalizacao.classes;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.EmpresaDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarEmpresa {
	
	public Empresa criarEmpresa(EntityManager em, LocalDate data, String cnpj, String razao, String logradouro, String cep, Uf uf, Cidade cidade, Bairro bairro) {
		EmpresaDao empresaDao = new EmpresaDao(em);
		Empresa empresa = empresaDao.buscaPorEmpresa(cnpj);
		
    	if(empresa == null) {
    		empresa = new Empresa();
    		empresa.setDt_atualizacao(data);
    		empresa.setCnpj(cnpj);
			empresa.setRazao(razao);
			empresa.setLogradouro(logradouro);
			empresa.setCep(cep);
			empresa.setBairro(bairro);
			empresa.setCidade(cidade);
			empresa.setUf(uf);
			empresaDao.add(empresa);
    	}
    	else {
    		if(empresa.getDt_atualizacao().isBefore(data)) {
    			empresa.setDt_atualizacao(data);
    			empresa.setRazao(razao);
    			empresa.setLogradouro(logradouro);
    			empresa.setCep(cep);
    			empresa.setBairro(bairro);
    			empresa.setCidade(cidade);
    			empresa.setUf(uf);
    			empresaDao.update(empresa);
        	}
    	}
    	return empresa;
	}
}
