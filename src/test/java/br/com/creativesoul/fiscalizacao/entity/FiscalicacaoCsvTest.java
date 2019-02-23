package br.com.creativesoul.fiscalizacao.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FiscalicacaoCsvTest {

	@Test
	void deveDecodificarLinhaDoCsvDeFiscalizacao() {
		String linha = "2009;2009/02;49.205.834/0001-22;TABAJARA S/A;RUA WASHINGTON,227;11410-150;VILA MAIA;Guarujá;São Paulo";
		FiscalizacaoCsv csv = new FiscalizacaoCsv(linha);
		assertEquals(2009, csv.getAno());
		assertEquals(2, csv.getMes());
	}

}
