package br.com.creativesoul.fiscalizacao.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class ImportadorProcessadorLinha implements ProcessadorLinha {

	private List<ProcessadorLinha> processadores = new ArrayList<>();
	private EntityManager em;
	
	public ImportadorProcessadorLinha(EntityManager em) {
		this.em = em;
		processadores.add(new MunicipioProcessadorLinha(em));
		processadores.add(new BairroProcessadorLinha(em));
		processadores.add(new EmpresaProcessadorLinha(em));
		processadores.add(new FiscalizacaoProcessadorLinha(em));
		processadores.add(new ErrosProcessadorLinha(em));
	}
	
	@Override
	public void processa(String linha) {
		
		try {
			em.getTransaction().begin();
			for (ProcessadorLinha processadorLinha : processadores) {
				processadorLinha.processa(linha);
			}
			em.getTransaction().commit();
		} catch(Exception ex) {
			em.getTransaction().rollback();
			System.out.println(ex.getMessage());
		}

	}

}
