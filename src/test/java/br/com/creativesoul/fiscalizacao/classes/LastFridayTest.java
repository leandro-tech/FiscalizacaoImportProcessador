package br.com.creativesoul.fiscalizacao.classes;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class LastFridayTest {

	@Test
	void deveRetornarUltimaDataDaSextaFeiraDoMes() {
		LastFriday lastFriday = new LastFriday(2018, 12);
		LocalDate dataEsperada = LocalDate.of(2018, 12, 28);
		assertEquals(dataEsperada, lastFriday.getData());
	}

}
