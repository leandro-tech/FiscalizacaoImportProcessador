package br.com.creativesoul.fiscalizacao.classes;


public class ValidarCNPJ {
	 
	  public boolean isCNPJ(String CNPJ) {
		  
		  CNPJ = CNPJ.replaceAll("-","");
		  CNPJ = CNPJ.replaceAll("/","");
		  CNPJ = CNPJ.replaceAll("\\.","");
		  
		  if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
			  CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
			  CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
			  CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
			  CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
			  (CNPJ.length() != 14))
			  return false;
		  
		  int digito13, digito14;
		  int soma = 0;
		  int peso = 2;
		  int num;
		  int resto;
	      
	      for (int i = 11; i >= 0; i--) {
	        num = (int)(CNPJ.charAt(i) - 48);
	        soma += (num * peso);
	        peso += 1;
	        
	        if (peso == 10)
	           peso = 2;
	      }
	      
	      resto = soma % 11;
	      
	      if ((resto == 0) || (resto == 1))
	    	  digito13 = 0;
	      else 
	    	  digito13 = (11 - resto) + 48;
	      
	      soma = 0;
	      peso = 2;
	      for (int i = 12; i >= 0; i--) {
	        num = (int)(CNPJ.charAt(i) - 48);
	        soma += (num * peso);
	        peso += 1;
	        
	        if (peso == 10)
	           peso = 2;
		  }
	      
	      resto = soma % 11;
	      if ((resto == 0) || (resto == 1))
	         digito14 = 0;
	      else 
	    	  digito14 = (11 - resto) + 48;
	      
	      if ((digito13 == (int) CNPJ.charAt(12)) && (digito14 == (int) CNPJ.charAt(13))) {
//	    	  System.out.println("++++++++++++++valido");
	          return true;
	       }
	       else {
//	    	   System.out.println("--------------------invalido");
	    	   return false;
	       }
	      
	  }
}
