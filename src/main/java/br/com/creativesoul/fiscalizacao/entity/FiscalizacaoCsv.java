package br.com.creativesoul.fiscalizacao.entity;

import java.time.LocalDate;
import java.util.StringTokenizer;

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
	
	private String[] content;
	private final String csvDivisor = ";";
	
	private boolean valido = false;
	
	public FiscalizacaoCsv(String linha) {
		
		content = linha.split(csvDivisor);		
		
		if(content.length != 9) {
			System.out.println("Linha inválida.");
			return;
		}
		
		ValidarCNPJ validadorCnpj = new ValidarCNPJ();
		cnpj = validadorCnpj.isCNPJ(content[2]);	
		
		cnpj = content[2];
		StringTokenizer st = new StringTokenizer(content[1]);
		ano = Integer.parseInt(st.nextToken("/"));
		mes = Integer.parseInt(st.nextToken("/"));		
		LastFriday lastFriday = new LastFriday(ano, mes);
		data = lastFriday.getData();
		razao = content[3].replaceAll("\"","").toUpperCase();
		logradouro = content[4];
		cep = content[5];
		bairro = content[6].toUpperCase();
		cidade = content[7].toUpperCase();
		uf = content[8].toUpperCase();
		valido = true;
	}
	
	public String[] getFiscalizacao() {
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
	public boolean isValido() {
		return valido;
	}
	
}
