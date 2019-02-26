package br.com.creativesoul.fiscalizacao.entity;

import java.time.LocalDate;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.classes.CriarBairro;
import br.com.creativesoul.fiscalizacao.classes.CriarCidade;
import br.com.creativesoul.fiscalizacao.classes.CriarErro;
import br.com.creativesoul.fiscalizacao.classes.CriarUF;
import br.com.creativesoul.fiscalizacao.classes.LastFriday;
import br.com.creativesoul.fiscalizacao.classes.ValidarCNPJ;

public class FiscalizacaoCsv {
	
	private int ano;
	private int mes;
	private LocalDate data;
	private String cnpj;
	private String razao;
	private String logradouro;
	private String cep;
	private Uf uf;
	private Cidade cidade;
	private Bairro bairro;
	
	public FiscalizacaoCsv(EntityManager em, String linha) {
		String csvDivisor = ";";
		String[] content = linha.split(csvDivisor);		
		
		ValidarCNPJ validadorCnpj = new ValidarCNPJ();
		cnpj = validadorCnpj.isCNPJ(content[2]);	
		
		StringTokenizer st = new StringTokenizer(content[1]);
		ano = Integer.parseInt(st.nextToken("/"));
		mes = Integer.parseInt(st.nextToken("/"));		
		LastFriday lastFriday = new LastFriday(ano, mes);
		data = lastFriday.getData();
		
		razao = content[3].replaceAll("\"","");
		
		logradouro = content[4];
		
		cep = content[5];
		
		CriarUF ufBuilder = new CriarUF(em, content[8]);
		uf = ufBuilder.getUf();
		
		CriarCidade cidadeBuilder = new CriarCidade(em, content[7], uf);
		cidade = cidadeBuilder.getCidade();
		
		CriarBairro bairroBuilder = new CriarBairro(em, content[6], cidade, uf);
		bairro = bairroBuilder.getBairro();
		
		if(cnpj == null) {
			@SuppressWarnings("unused")
			CriarErro erro = new CriarErro(
					em,
					content[0], 
					content[1],
					content[2], 
					content[3], 
					content[4], 
					content[5], 
					content[6], 
					content[7], 
					content[8]);
		}
	}
	
	public int getAno() {		
		return ano;
	}

	public int getMes() {
		return mes;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public String getRazao() {
		return razao;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public String getCep() {
		return cep;
	}
	
	public Uf getUf() {
		return uf;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public Bairro getBairro() {
		return bairro;
	}
	
}
