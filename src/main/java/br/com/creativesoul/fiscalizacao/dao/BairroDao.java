package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Bairro;

public class BairroDao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Bairro resultado;
	
	public boolean checkBairro(EntityManager em, Bairro bairro) {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select b from Bairro b");
			jpql.append("     join b.cidade c");
			jpql.append("     join b.uf u");
			jpql.append(" where ");
			jpql.append("   b.nome = :pNome ");
			jpql.append("   and c.id = :pCidade");
			jpql.append("   and u.id = :pUf");
			
			TypedQuery<Bairro> query = em.createQuery(jpql.toString(), Bairro.class);
			
			query.setParameter("pNome", bairro.getNome());
			query.setParameter("pCidade", bairro.getCidade().getId());
			query.setParameter("pUf", bairro.getUf().getId());
			try {
				resultado = query.getSingleResult();
				return true;
		    } catch (Exception e) {
		        return false;
		    }
	}
	
	public Bairro bairroExistente() {		
		try {
			return resultado;
	    } catch (Exception e) {
	        return null;
	    }
	}

}
