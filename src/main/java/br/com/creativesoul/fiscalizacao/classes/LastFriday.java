package br.com.creativesoul.fiscalizacao.classes;

import java.time.LocalDate;
import java.util.Calendar;

public class LastFriday {

	private LocalDate date;

	public LastFriday(int ano, int mes) {
		Calendar cal = Calendar.getInstance();
		cal.set(ano, mes, 1);
		cal.add(Calendar.DAY_OF_MONTH, -( cal.get( Calendar.DAY_OF_WEEK ) % 7 + 1 ));
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		date = LocalDate.of(ano, mes, dia);
	}

	public LocalDate getData() {
		return date;
	}

}
