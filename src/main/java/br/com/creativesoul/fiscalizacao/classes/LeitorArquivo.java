package br.com.creativesoul.fiscalizacao.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.creativesoul.fiscalizacao.negocio.ProcessadorLinha;

public class LeitorArquivo {

	public void leia(String arquivo, ProcessadorLinha processadorLinha) {
		BufferedReader br = null;
		String linha = "";
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-8"));
			while ((linha = br.readLine()) != null) {
				processadorLinha.processa(linha);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
