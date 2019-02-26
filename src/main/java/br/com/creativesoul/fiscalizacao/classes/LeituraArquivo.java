package br.com.creativesoul.fiscalizacao.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;

public class LeituraArquivo {
	
	EntityManager em = JPAUtil.getEntityManager();
	Empresa empresa = new Empresa();
	
	public void read() {
		String arquivoCSV = "\\Users\\TI\\Documents\\Repositorios\\fiscalizacao\\teste.csv";
	    BufferedReader br = null;
	    String linha = "";
	    try {
	    	br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));
	    	
	    	
		    	
		        while ((linha = br.readLine()) != null) {  	        	
		        	FiscalizacaoCsv contentFiscal = new FiscalizacaoCsv(em, linha);
		        	try {
				    	em.getTransaction().begin();
		    			if(contentFiscal.getCnpj() != null) {
		    				CriarEmpresa empresaBuilder = new CriarEmpresa(
		    						em,
		    						contentFiscal.getData(), 
		    						contentFiscal.getCnpj(), 
		    						contentFiscal.getRazao(), 
		    						contentFiscal.getLogradouro(), 
		    						contentFiscal.getCep(),
		    						contentFiscal.getBairro()
		    						);
		    				empresa = empresaBuilder.getEmpresa();
							@SuppressWarnings("unused")
							CriarFiscalizacao fiscalizacao = new CriarFiscalizacao(em, empresa);
		    			}
		    			em.getTransaction().commit();
			    	} catch(Exception ex) {
						em.getTransaction().rollback();
						ex.printStackTrace();
					}
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
