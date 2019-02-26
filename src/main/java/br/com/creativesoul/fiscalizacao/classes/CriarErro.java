package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.entity.Erros;

public class CriarErro {

	Erros erro = new Erros();
	
	public CriarErro(EntityManager em, String ano_termino, String mes_termino, String cnpj, String razao, String logradouro, String cep, String bairro, String cidade, String uf) {
		DAO<Erros> dao = new DAO<>(em, Erros.class);
		erro.setAno_termino(ano_termino);
		erro.setMes_termino(mes_termino);
		erro.setCnpj(cnpj);
		erro.setRazao(razao);
		erro.setLogradouro(logradouro);
		erro.setCep(cep);
		erro.setBairro(bairro);
		erro.setCidade(cidade);
		erro.setUf(uf);
		dao.add(erro);
	}

}
