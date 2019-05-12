package br.com.creativesoul.fiscalizacao.entity;

import javax.persistence.EntityManager;

public class UfCsv {
	
	private String sigla;
	private String nome;
	String[] content;
	
	public UfCsv(EntityManager em, String linha) {
		String csvDivisor = ";";

		content = null;
		content = linha.split(csvDivisor);

		if (content.length > 2) {
			System.out.println("Linha inválida.");
			return;
		}
		nome = content[0];
		sigla = content[1];
	}

	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

}
