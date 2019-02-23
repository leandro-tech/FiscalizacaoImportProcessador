package br.com.creativesoul.fiscalizacao.entity;

import java.util.StringTokenizer;

public class FiscalizacaoCsv {
	
	private int ano;
	private int mes;
	
	public FiscalizacaoCsv(String linha) {
		String csvDivisor = ";";
		String[] content = linha.split(csvDivisor);		
		StringTokenizer st = new StringTokenizer(content[1]);
		ano = Integer.parseInt(st.nextToken("/"));
		mes = Integer.parseInt(st.nextToken("/"));
	}
	
	public int getAno() {		
		return ano;
	}

	public int getMes() {
		return mes;
	}
	
	

}
