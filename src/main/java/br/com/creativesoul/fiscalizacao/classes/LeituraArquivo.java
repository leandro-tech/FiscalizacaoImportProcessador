package br.com.creativesoul.fiscalizacao.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArquivo {
	
	public void read() {
		
		String arquivoCSV = "\\Users\\TI\\Documents\\Repositorios\\fiscalizacao\\teste.csv";
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    try {

	        br = new BufferedReader(new FileReader(arquivoCSV));
	        while ((linha = br.readLine()) != null) {

	            String[] teste = linha.split(csvDivisor);
	            
	            if(teste[7] != null) {
	            	teste[7] = "erro";
	            }
	            
	            System.out.println(teste[0] 
		               + "  |  " + teste[1]
		               + "  |  " + teste[2]
		               + "  |  " + teste[3]
		               + "  |  " + teste[4]
		               + "  |  " + teste[5]
		               + "  |  " + teste[6]
		               + "  |  " + teste[7]
		               + "  |  " + teste[8]);
	            System.out.println("\n");
	        }

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
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
