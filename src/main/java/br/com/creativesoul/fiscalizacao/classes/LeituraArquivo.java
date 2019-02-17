package br.com.creativesoul.fiscalizacao.classes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import br.com.creativesoul.fiscalizacao.conexao.JPAUtil;
import br.com.creativesoul.fiscalizacao.dao.BairroDao;
import br.com.creativesoul.fiscalizacao.dao.CidadeDao;
import br.com.creativesoul.fiscalizacao.dao.EmpresaDao;
import br.com.creativesoul.fiscalizacao.dao.UfDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Cidade;
import br.com.creativesoul.fiscalizacao.entity.Empresa;
import br.com.creativesoul.fiscalizacao.entity.Erros;
import br.com.creativesoul.fiscalizacao.entity.Fiscalizacao;
import br.com.creativesoul.fiscalizacao.entity.Uf;

public class LeituraArquivo {
	
	UfDao ufDao = new UfDao();
	CidadeDao cidadeDao = new CidadeDao();
	BairroDao bairroDao = new BairroDao();
	EmpresaDao empresaDao = new EmpresaDao();
	EntityManager em = JPAUtil.getEntityManager();
	String NULL = "Sem Informação";
	
	public void persist() {		
		try {
			em.getTransaction().begin();			
			read();
			em.getTransaction().commit();
		
			} catch(Exception ex) {
				em.getTransaction().rollback();
				ex.printStackTrace();
			}	
	}
	
	
	public void read() {
				
		String arquivoCSV = "\\Users\\TI\\Documents\\Repositorios\\fiscalizacao\\teste.csv";
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    try {
	    	br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivoCSV), "UTF-8"));
	        
	        while ((linha = br.readLine()) != null) {  	        	
	        	String[] content = linha.split(csvDivisor);	
       			CriarFiscalizacao(content[0], content[1], content[2], content[3], content[4], content[5], content[6], content[7], content[8]);
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }		
	}
	
	public String obterUf(String nomeUF) {		
    	String siglaUF = "";
        String[] separador = nomeUF.split(" "); 
        for (int i = 0; i < separador.length; i++) {
            String s = separador[i];
            if(i+1 <= 2) {
            	siglaUF = siglaUF.concat(String.valueOf(s.charAt(0)));
            }	                
        }
		return siglaUF;
	}
	   
	public Uf CriarUF(String nomeUF) {
		String sigla = obterUf(nomeUF);	 
    	Uf uf = new Uf();
    	uf.setSigla(sigla);
    	uf.setNome(nomeUF);   
    	
    	if(!ufDao.checkUf(em, uf)) {
			em.persist(uf);	
    		return uf;
    	}
    	else
    		return ufDao.ufExistente();
    	
	}
	
	public Cidade CriarCidade(String nomeCidade, String uf) {		
		Cidade cidade = new Cidade();
		cidade.setNome(nomeCidade);
		cidade.setUf(CriarUF(uf));
		
		if(!cidadeDao.checkCidade(em, cidade)) {
			em.persist(cidade);	
			return cidade;
		}
		else {
			return cidadeDao.cidadeExistente();
		}
		
	}

	public Bairro CriarBairro(String nomeBairro, String nomeCidade, String nomeUf) {	
		Bairro bairro = new Bairro();
		bairro.setNome(nomeBairro);
		bairro.setCidade(CriarCidade(nomeCidade, nomeUf));
		bairro.setUf(CriarUF(nomeUf));
//		if(!nomeBairro.equals(NULL)) {			
		if(!bairroDao.checkBairro(em, bairro) && !nomeBairro.equals(NULL)) {
			em.persist(bairro);
			return bairro;
		}
		else {
			return bairroDao.bairroExistente();
		}
//		}		
		
	}
	
	public Empresa CriarEmpresa(String ano_termino, String mes_termino, String cnpj, String razao, String logradouro, String cep, String bairro, String cidade, String uf) {	
		
		if(!cnpj.equals(NULL)) {		
			razao = razao.replaceAll("\"","");
			Empresa empresa = new Empresa();
			empresa.setDt_atualizacao(Data(mes_termino));
			empresa.setCnpj(cnpj);
			empresa.setRazao(razao);
			empresa.setLogradouro(logradouro);
			empresa.setCep(cep);
			empresa.setBairro(CriarBairro(bairro, cidade, uf));
			empresa.setCidade(CriarCidade(cidade, uf));
			empresa.setUf(CriarUF(uf));
			
			if(!empresaDao.checkCnpj(em, empresa)) {
				em.persist(empresa);
//				empresaDao.adiciona(empresa);	
				return empresa;
			}
			else {
				Empresa empresaExistente = empresaDao.empresaExistente();
				if(empresaExistente.getDt_atualizacao().isBefore(empresa.getDt_atualizacao())) {					
//					Empresa teste = em.find(Empresa.class, 1);		
//					System.out.println(teste);
					System.out.println("atual: " + empresaExistente.getDt_atualizacao());
					System.out.println("novo: " + empresa.getDt_atualizacao());
				}
				return empresaDao.empresaExistente();
			}
		}
		else {
			CriarErro(ano_termino, mes_termino, cnpj, razao, logradouro, cep, bairro, cidade, uf);
			return null;
		}		
	}
	
	public void CriarFiscalizacao(String ano_termino, String mes_termino, String cnpj, String razao, String logradouro, String cep, String bairro, String cidade, String uf) {
		
		Empresa empresa = CriarEmpresa(ano_termino, mes_termino, cnpj, razao, logradouro, cep, bairro, cidade, uf);
		if(empresa == null) {
			return;
		}
		
		Fiscalizacao fiscal = new Fiscalizacao();
		fiscal.setData(Data(mes_termino));
		fiscal.setEmpresa(empresa);
		fiscal.setRazao(empresa.getRazao());
		fiscal.setLogradouro(empresa.getLogradouro());
		fiscal.setCep(empresa.getCep());
		fiscal.setCidade(empresa.getCidade());
		fiscal.setBairro(empresa.getBairro());
		fiscal.setUf(empresa.getUf());
		
		em.persist(fiscal);
	}
	
	public LocalDateTime Data(String dataErro) {
		
		StringTokenizer st = new StringTokenizer(dataErro);
		int ano = Integer.parseInt(st.nextToken("/"));
		int mes = Integer.parseInt(st.nextToken("/"));
	    
		System.out.println(ano + "| " + mes);
		
		Calendar cal = Calendar.getInstance();
		cal.set(ano, mes, 1);
		cal.add(Calendar.DAY_OF_MONTH, -( cal.get( Calendar.DAY_OF_WEEK ) % 7 + 1 ));
		Instant timestamp = cal.getTime().toInstant();
		LocalDateTime date = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault()); 
		return date;
	}
	
	public void CriarErro(String ano_termino, String mes_termino, String cnpj, String razao, String logradouro, String cep, String bairro, String cidade, String uf) {
		Erros erro = new Erros();
		erro.setAno_termino(ano_termino);
		erro.setMes_termino(mes_termino);
		erro.setCnpj(cnpj);
		erro.setRazao(razao);
		erro.setLogradouro(logradouro);
		erro.setCep(cep);
		erro.setBairro(bairro);
		erro.setCidade(cidade);
		erro.setUf(uf);
		em.persist(erro);
	}
	
}
