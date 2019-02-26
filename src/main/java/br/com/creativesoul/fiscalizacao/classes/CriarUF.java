package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarUF {
	
	Uf uf = new Uf();
	UfDao ufDao = new UfDao();
		   
		public CriarUF(EntityManager em, String nomeUF) {
			DAO<Uf> dao = new DAO<>(em, Uf.class);
			String sigla = obterUf(nomeUF);	
			
	    	uf.setSigla(sigla);
	    	uf.setNome(nomeUF);   
	    	
	    	if(!ufDao.checkUf(em, uf)) 
	    		dao.add(uf);
	    	else
	    		uf = ufDao.ufExistente();    	
		}
		
		public String obterUf(String nomeUF) {		
	    	String siglaUF = "";
	        String[] separador = nomeUF.split(" "); 
	        for (int i = 0; i < separador.length; i++) {
	            String s = separador[i];
	            if(i+1 <= 2) {
	            	siglaUF = siglaUF.concat(String.valueOf(s.charAt(0)));
	            }	                
	        }
			return siglaUF;
		}
		
		public Uf getUf() {
			return uf;
		}
	
}
