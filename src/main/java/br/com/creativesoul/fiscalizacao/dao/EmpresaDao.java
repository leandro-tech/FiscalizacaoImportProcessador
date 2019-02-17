package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Empresa;

public class EmpresaDao implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private Empresa resultado;
		
	public boolean checkCnpj(EntityManager em, Empresa empresa) {
			StringBuilder jpql = new StringBuilder();
			jpql.append(" select u from Empresa u ");
			jpql.append(" where ");
			jpql.append(" u.cnpj = :pCnpj ");
			
			TypedQuery<Empresa> query = em.createQuery(jpql.toString(), Empresa.class);
			
			query.setParameter("pCnpj", empresa.getCnpj());
			try {
				resultado = query.getSingleResult();
				return true;
		    } catch (Exception e) {
		        return false;
		    }
	}
	
	public Empresa empresaExistente() {		
		try {
			return resultado;
	    } catch (Exception e) {
	        return null;
	    }
	}
	
//	@SuppressWarnings("unchecked")
//	public void adiciona(Empresa empresa) {
//		dao.add(empresa);
//	}
//	
//	public void atualiza(Empresa empresa){
//		em.merge(empresa);
//	}
	
}
