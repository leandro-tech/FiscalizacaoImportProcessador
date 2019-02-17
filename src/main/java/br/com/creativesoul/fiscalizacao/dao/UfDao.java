package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Uf;


public class UfDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Uf resultado;		
	
	public boolean checkUf(EntityManager em, Uf uf) {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select u from Uf u ");
			jpql.append(" where ");
			jpql.append(" u.sigla = :pSigla ");
			
			TypedQuery<Uf> query = em.createQuery(jpql.toString(), Uf.class);
			
			query.setParameter("pSigla", uf.getSigla());
			try {				
				resultado = query.getSingleResult();					
				return true;
		    } catch (Exception e) {
		        return false;
		    }
	}
	
	public Uf ufExistente() {		
			try {
				return resultado;
		    } catch (Exception e) {
		        return null;
		    }
	}
}
