package br.com.creativesoul.fiscalizacao.negocio;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.BairroDao;
import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class BairroProcessadorLinha implements ProcessadorLinha {

	private BairroDao bairroDao;
	private CidadeDao cidadeDao;
	private UfDao ufDao;

	public BairroProcessadorLinha(EntityManager em) {
		ufDao = new UfDao(em);
		cidadeDao = new CidadeDao(em);
		bairroDao = new BairroDao(em);
	}

	@Override
	public void processa(String linha) {
		FiscalizacaoCsv fiscalizacaoCsv = new FiscalizacaoCsv(linha);
		Uf uf = ufDao.buscaPorNome(fiscalizacaoCsv.getUf());
		if (uf != null) {
			Cidade cidade = cidadeDao.buscaPorNome(uf, fiscalizacaoCsv.getCidade());
			if (cidade != null) {
				if (bairroDao.naoExisteBairro(cidade, fiscalizacaoCsv.getBairro())) {
					Bairro bairro = new Bairro();
					bairro.setUf(uf);
					bairro.setCidade(cidade);
					bairro.setNome(fiscalizacaoCsv.getBairro());
					bairroDao.add(bairro);
				}
			}
		}
	}

}
