package br.com.creativesoul.fiscalizacao.classes;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.creativesoul.fiscalizacao.dao.DAO;
import br.com.creativesoul.fiscalizacao.dao.EmpresaDao;
import br.com.creativesoul.fiscalizacao.entity.Bairro;
import br.com.creativesoul.fiscalizacao.entity.Empresa;

public class CriarEmpresa {
	
	Empresa empresa = new Empresa();
	EmpresaDao empresaDao = new EmpresaDao();
	
	public CriarEmpresa(EntityManager em, LocalDate data, String cnpj, String razao, String logradouro, String cep, Bairro bairro) {	
			DAO<Empresa> dao = new DAO<>(em, Empresa.class);
			empresa.setDt_atualizacao(data);
			empresa.setCnpj(cnpj);
			empresa.setRazao(razao);
			empresa.setLogradouro(logradouro);
			empresa.setCep(cep);
			empresa.setBairro(bairro);
			empresa.setCidade(bairro.getCidade());
			empresa.setUf(bairro.getUf());
			
			
			if(!empresaDao.checkCnpj(em, empresa)) {
				dao.add(empresa);
			}
			else {
				Empresa empresaExistente = empresaDao.empresaExistente();
				if(empresaExistente.getDt_atualizacao().isBefore(empresa.getDt_atualizacao())) {	
					empresaExistente.setDt_atualizacao(empresa.getDt_atualizacao());
					empresaExistente.setRazao(empresa.getRazao());
					empresaExistente.setLogradouro(empresa.getLogradouro());
					empresaExistente.setCep(empresa.getCep());
					empresaExistente.setBairro(empresa.getBairro());
					empresaExistente.setCidade(empresa.getCidade());
					empresaExistente.setUf(empresa.getUf());
					dao.update(empresaExistente);
				}
				empresa = empresaDao.empresaExistente();
			}
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	
}
