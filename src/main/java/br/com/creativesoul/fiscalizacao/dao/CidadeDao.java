package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Cidade;


public class CidadeDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Cidade resultado;
	
	public boolean checkCidade(EntityManager em, Cidade cidade) {
		
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select u from Cidade u");
			jpql.append("     join u.uf d");
			jpql.append(" where ");
			jpql.append("	u.nome = :pNome ");
			jpql.append(" 	and d.id = :pUf");
			
			TypedQuery<Cidade> query = em.createQuery(jpql.toString(), Cidade.class);
			
			query.setParameter("pNome", cidade.getNome());
			query.setParameter("pUf", cidade.getUf().getId());
			try {
				resultado = query.getSingleResult();	
				return true;
		    } catch (Exception e) {
		        return false;
		    }
	}
	
	public Cidade cidadeExistente() {		
		try {
			return resultado;
	    } catch (Exception e) {
	        return null;
	    }
	}
	
}
