package br.com.creativesoul.fiscalizacao.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.Erros;
import br.com.creativesoul.fiscalizacao.entity.Fiscalizacao;
import br.com.creativesoul.fiscalizacao.entity.FiscalizacaoCsv;
import br.com.creativesoul.fiscalizacao.entity.Uf;

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
	        	FiscalizacaoCsv ConteudoLinha = new FiscalizacaoCsv(em, linha);
	        		
	        	CriarUF ufBuilder = new CriarUF();
	    		Uf uf = ufBuilder.criarUF(em, ConteudoLinha.getUf());
	    		
	    		CriarCidade cidadeBuilder = new CriarCidade();
	    		Cidade cidade = cidadeBuilder.criarCidade(em, ConteudoLinha.getCidade(), uf);
	    		
	    		CriarBairro bairroBuilder = new CriarBairro();
	    		Bairro bairro = bairroBuilder.criarBairro(em, ConteudoLinha.getBairro(), cidade, uf);
	    		
	        	try {
	        		if(ConteudoLinha.getCnpj() != null) {
				    	em.getTransaction().begin();
	    				CriarEmpresa empresaBuilder = new CriarEmpresa();	
	    				Empresa empresa = empresaBuilder.criarEmpresa(em, 
	    															  ConteudoLinha.getData(), 
	    															  ConteudoLinha.getCnpj(), 
	    															  ConteudoLinha.getRazao(), 
	    															  ConteudoLinha.getLogradouro(),
	    															  ConteudoLinha.getCep(), 
	    															  uf, 
	    															  cidade, 
	    															  bairro);
	    				
	    				Fiscalizacao fiscal = new Fiscalizacao();
	    				fiscal.setEmpresa(empresa);
	    				fiscal.setData(empresa.getDt_atualizacao());
	    				fiscal.setRazao(empresa.getRazao());
	    				fiscal.setLogradouro(empresa.getLogradouro());
	    				fiscal.setCep(empresa.getCep());
	    				fiscal.setCidade(empresa.getCidade());
	    				fiscal.setBairro(empresa.getBairro());
	    				fiscal.setUf(empresa.getUf());
	    				em.persist(fiscal);
		    			em.getTransaction().commit();
	        		}
	        		else {
	        			String[] conteudoErro = ConteudoLinha.getConteudoOriginal();
	        			Erros erro = new Erros();
	        			erro.setAno_termino(conteudoErro[0]);
	        			erro.setMes_termino(conteudoErro[1]);
	        			erro.setCnpj(conteudoErro[2]);
	        			erro.setRazao(conteudoErro[3]);
	        			erro.setLogradouro(conteudoErro[4]);
	        			erro.setCep(conteudoErro[5]);
	        			erro.setBairro(conteudoErro[6]);
	        			erro.setCidade(conteudoErro[7]);
	        			erro.setUf(conteudoErro[8]);
	        			em.persist(erro);
	        		}
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
