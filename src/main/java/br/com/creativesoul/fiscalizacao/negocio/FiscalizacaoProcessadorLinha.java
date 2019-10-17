package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.EmpresaDao;
import br.com.creativesoul.fiscalizacao.dao.FiscalizacaoDao;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.Fiscalizacao;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;

public class FiscalizacaoProcessadorLinha implements ProcessadorLinha {

	private FiscalizacaoDao fiscalizacaoDao;
	private EmpresaDao empresaDao;
	
	public FiscalizacaoProcessadorLinha(EntityManager em) {
		fiscalizacaoDao = new FiscalizacaoDao(em);
		empresaDao = new EmpresaDao(em);
	}

	@Override
	public void processa(String linha) {
		FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
		
		Empresa empresa = empresaDao.buscaPorEmpresa(fiscalizacaoCsv.getCnpj());
		
		if (empresa != null) {
			Fiscalizacao fiscalizacao = new Fiscalizacao();
			fiscalizacao.setEmpresa(empresa);
			fiscalizacao.setData(empresa.getDt_atualizacao());
			fiscalizacao.setRazao(empresa.getRazao());
			fiscalizacao.setLogradouro(empresa.getLogradouro());
			fiscalizacao.setCep(empresa.getCep());
			fiscalizacao.setCidade(empresa.getCidade());
			fiscalizacao.setBairro(empresa.getBairro());
			fiscalizacao.setUf(empresa.getUf());
			fiscalizacaoDao.add(fiscalizacao);
		}
	}

}
