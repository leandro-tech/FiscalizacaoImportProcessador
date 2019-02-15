package br.com.creativesoul.fiscalizacao.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.creativesoul.fiscalizacao.entity.Uf;

@Named
@RequestScoped
public class UfDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private DAO <Uf> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Uf>(this.em, Uf.class);
	}
	
	@Inject
	private EntityManager em;
	
	public boolean existe(Uf uf) {
			
			TypedQuery<Uf> query = em.createQuery(
					  " select u.sigla from tb_uf u "
					+ " where u.sigla = :pSigla", Uf.class);
			
			query.setParameter("pSigla", uf.getSigla());
//			query.setParameter("pSenha", usuario.getSenha());
			try {
				@SuppressWarnings("unused")
				Uf resultado = query.getSingleResult();
				return true;
			} catch (NoResultException ex) {
				return false;
			}
		}
	
//	public void adiciona(Uf usuario) {
//		dao.adiciona(usuario);
//	}

	public void atualiza(Uf usuario){
		em.merge(usuario);
	}

//	public void remove(Uf usuario) {
//		dao.remove(usuario);
//	}

	public Uf buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Uf> listaTodosPaginada(int firstResult, int maxResults) {
		return dao.listaTodosPaginada(firstResult, maxResults);
	}

	public Uf buscaPorEmail(String email) {
		String jpql = " select u from Usuario u where u.email = :pEmail";
		TypedQuery<Uf> query = em.createQuery(jpql, Uf.class);
		query.setParameter("pEmail", email.trim().toLowerCase());
		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
