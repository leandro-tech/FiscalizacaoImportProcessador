package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.ErrosDao;
import br.com.creativesoul.fiscalizacao.entity.Erros;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;

public class ErrosProcessadorLinha implements ProcessadorLinha {
	
	private ErrosDao errosDao;
	
	public ErrosProcessadorLinha(EntityManager em) {
		errosDao = new ErrosDao(em);
	}

	@Override
	public void processa(String linha) {
		FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
		String[] conteudoErro = fiscalizacaoCsv.getFiscalizacao();
		if (fiscalizacaoCsv.isValido() == false) {
			Erros linhaErro = new Erros();
			linhaErro.setAno_termino(conteudoErro[0]);
			linhaErro.setMes_termino(conteudoErro[1]);
			linhaErro.setCnpj(conteudoErro[2]);
			linhaErro.setRazao(conteudoErro[3]);
			linhaErro.setLogradouro(conteudoErro[4]);
			linhaErro.setCep(conteudoErro[5]);
			linhaErro.setBairro(conteudoErro[6]);
			linhaErro.setCidade(conteudoErro[7]);
			linhaErro.setUf(conteudoErro[8]);
			errosDao.add(linhaErro);
		}
		
		
		
	}

}
