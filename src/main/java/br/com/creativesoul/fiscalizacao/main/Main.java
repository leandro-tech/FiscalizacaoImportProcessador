package br.com.creativesoul.fiscalizacao.main;

import br.com.creativesoul.fiscalizacao.classes.LeituraArquivo;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Início do processamento");
		LeituraArquivo file = new LeituraArquivo();
		file.readUf();
		file.readFiscalizacao();
		System.out.println("Fim do processamento");
		System.exit(0);
	}
	
}
