package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class MunicipioProcessadorLinha implements ProcessadorLinha {

	private CidadeDao cidadeDao;
	private UfDao ufDao;

	public MunicipioProcessadorLinha(EntityManager em) {
		ufDao = new UfDao(em);
		cidadeDao = new CidadeDao(em);
	}

	@Override
	public void processa(String linha) {
		FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
		Uf uf = ufDao.buscaPorNome(fiscalizacaoCsv.getUf());
		if ((uf != null) && (cidadeDao.naoExisteCidade(uf, fiscalizacaoCsv.getCidade()))) {
			Cidade cidade = new Cidade();
			cidade.setNome(fiscalizacaoCsv.getCidade());
			cidade.setUf(uf);
			cidadeDao.add(cidade);
		}

	}

}
