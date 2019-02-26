package br.com.creativesoul.fiscalizacao.entity;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;

import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;

class FiscalicacaoCsvTest {
	
	EntityManager em = JPAUtil.getEntityManager();
	
	@Test
	void deveDecodificarLinhaDoCsvDeFiscalizacao() {
		String linha = "2009;2009/02;49.205.834/0001-22;TABAJARA S/A;RUA WASHINGTON,227;11410-150;VILA MAIA;Guarujá;São Paulo";
		FiscalizacaoCsv csv = new FiscalizacaoCsv(em, linha);
		assertEquals(2009, csv.getAno());
		assertEquals(2, csv.getMes());
		assertEquals("49.205.834/0001-22", csv.getCnpj());
	
	}

}
