package br.com.creativesoul.fiscalizacao.main;


import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.classes.LeituraArquivo;
import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Uf;
import br.com.creativesoul.fiscalizacao.entity.Usuario;

public class Main {
	
	//@Inject
	private Usuario ufDao;
	
	public static void main(String[] args) {
		System.out.println("Oi!");
		EntityManager em = JPAUtil.getEntityManager();
				
		LeituraArquivo file = new LeituraArquivo();
		file.read();
		
		Uf uf = new Uf();
		uf.setSigla("SP");
		uf.setNome("São Paulo");
		
		Uf uf2 = new Uf();
		uf2.setSigla("SP1");
		uf2.setNome("São Paulo1");
		
		
		try {
			em.getTransaction().begin();
			
//			for(int i = 1; i <= 5; i++) {
//				Usuario usuario = new Usuario();
//				usuario.setNome("Teste " + i);
//				em.persist(usuario);
//			}
			
			
			em.persist(uf);	
			
			System.out.println(uf2.getNome());
			System.out.println(uf2.getSigla());
			
			em.persist(uf2);
						
//			if(ufDao.existe(uf2) == true) {
//				System.out.println("repetido");
//			}
//			else {
//				System.out.println("Ok");
////				em.persist(uf2);
//			}			
			
			em.getTransaction().commit();
			
		} catch(Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		}
		System.exit(0);
	}

}
