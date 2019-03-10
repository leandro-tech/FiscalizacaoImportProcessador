package br.com.creativesoul.fiscalizacao.classes;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class CriarUF {
	
	public Uf criarUF(EntityManager em, String nomeUF) {
		
		if(nomeUF.equals("Sem Informação")) {
			return null;
		}
		
		UfDao ufDao = new UfDao(em);
		String sigla = obterUf(nomeUF);	
		
		Uf uf = ufDao.buscaPorSigla(sigla);
		
    	if(uf == null) {
    		uf = new Uf();
    		uf.setSigla(sigla);
    		uf.setNome(nomeUF);
    		ufDao.add(uf);
    	}
    	return uf;
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
	
}
