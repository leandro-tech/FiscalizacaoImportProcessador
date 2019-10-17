package br.com.creativesoul.fiscalizacao.main;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.classes.LeitorArquivo;
import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;
import br.com.creativesoul.fiscalizacao.negocio.ImportadorProcessadorLinha;
import br.com.creativesoul.fiscalizacao.negocio.ImportadorUF;
import br.com.creativesoul.fiscalizacao.negocio.ProcessadorLinha;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Inicio do procesamento!");
		String ufCSV = "C:\\Users\\TI\\Documents\\Repositorios\\fiscalizacao\\uf.csv";
		String fiscalizacaoCSV = "C:\\Users\\TI\\Documents\\Repositorios\\fiscalizacao\\teste5.csv";
		EntityManager em = JPAUtil.getEntityManager();
		
		LeitorArquivo leitor = new LeitorArquivo();
		
		ProcessadorLinha processadorUf = new ImportadorUF(em);
		leitor.leia(ufCSV, processadorUf);
		
		ProcessadorLinha processadorLinha = new ImportadorProcessadorLinha(em);
		leitor.leia(fiscalizacaoCSV, processadorLinha);
		
//		leitorArquivo.readFiscalizacao();
		System.out.println("Fim do procesamento!");
		System.exit(0);
	}
	
}
