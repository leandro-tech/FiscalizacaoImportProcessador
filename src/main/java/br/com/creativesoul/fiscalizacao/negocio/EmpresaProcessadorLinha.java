package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.BairroDao;
import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.dao.EmpresaDao;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class EmpresaProcessadorLinha implements ProcessadorLinha {

	private EmpresaDao empresaDao;
	private UfDao ufDao;
	private CidadeDao cidadeDao;
	private BairroDao bairroDao;
	
	public EmpresaProcessadorLinha(EntityManager em) {
		empresaDao = new EmpresaDao(em);
		ufDao = new UfDao(em);
		cidadeDao = new CidadeDao(em);
		bairroDao = new BairroDao(em);
	}

	@Override
	public void processa(String linha) {
		FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
		
		Empresa empresa = empresaDao.buscaPorEmpresa(fiscalizacaoCsv.getCnpj());
		
		Uf uf = ufDao.buscaPorNome(fiscalizacaoCsv.getUf());
		Cidade cidade = cidadeDao.buscaPorNome(uf, fiscalizacaoCsv.getCidade());
		Bairro bairro = bairroDao.buscaPorBairro(fiscalizacaoCsv.getBairro(), cidade.getId(), uf.getId());
		
		if (empresa == null) {
			empresa = new Empresa();
			empresa.setDt_atualizacao(fiscalizacaoCsv.getData());
    		empresa.setCnpj(fiscalizacaoCsv.getCnpj());
			empresa.setRazao(fiscalizacaoCsv.getRazao());
			empresa.setLogradouro(fiscalizacaoCsv.getLogradouro());
			empresa.setCep(fiscalizacaoCsv.getCep());
			empresa.setBairro(bairro);
			empresa.setCidade(cidade);
			empresa.setUf(uf);
			empresaDao.add(empresa);
		}
		else {
    		if(empresa.getDt_atualizacao().isBefore(fiscalizacaoCsv.getData())) {
    			empresa.setDt_atualizacao(fiscalizacaoCsv.getData());
    			empresa.setRazao(fiscalizacaoCsv.getRazao());
    			empresa.setLogradouro(fiscalizacaoCsv.getLogradouro());
    			empresa.setCep(fiscalizacaoCsv.getCep());
    			empresa.setBairro(bairro);
    			empresa.setCidade(cidade);
    			empresa.setUf(uf);
    			empresaDao.update(empresa);
        	}
    	}
	}

}
