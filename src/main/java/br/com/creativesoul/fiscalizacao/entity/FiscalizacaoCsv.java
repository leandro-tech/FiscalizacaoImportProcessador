package br.com.creativesoul.fiscalizacao.entity;

import java.time.LocalDate;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

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
	private String bairro;
	private String cidade;
	private String uf;
	
	String[] content;
	
	public FiscalizacaoCsv(EntityManager em, String linha) {
		String csvDivisor = ";";
		
		content = null;
		content = linha.split(csvDivisor);		
		
		if(content.length > 9) {
			System.out.println("Linha inválida.");
			return;
		}
		
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
		bairro = content[6];
		cidade = content[7];
		uf = content[8];
	}
	
	public String[] getConteudoOriginal() {
		return content;
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
	
	public String getUf() {
		return uf;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getBairro() {
		return bairro;
	}
	
}
