package br.com.creativesoul.fiscalizacao.main;

import br.com.creativesoul.fiscalizacao.classes.LeituraArquivo;

public class Main {
	
	public static void main(String[] args) {
		
		LeituraArquivo file = new LeituraArquivo();
		file.persist();
		
		System.exit(0);
	}
	
}
