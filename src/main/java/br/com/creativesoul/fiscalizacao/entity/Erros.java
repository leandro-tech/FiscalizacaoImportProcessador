package br.com.creativesoul.fiscalizacao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_erros")
public class Erros implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id")	
	private Long id;
	
	@Column(name = "ano_termino", nullable = true, unique = false)
	private String ano_termino;
	
	@Column(name = "mes_termino", nullable = true, unique = false)
	private String mes_termino;
	
	@Column(name = "cnpj", nullable = true, unique = false)
	private String cnpj;
	
	@Column(name = "razao", nullable = true, unique = false)
	private String razao;
	
	@Column(name = "logradouro", nullable = true, unique = false)
	private String logradouro;
	
	@Column(name = "cep", nullable = true, unique = false)
	private String cep;
	
	@Column(name = "bairro", nullable = true, unique = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = true, unique = false)
	private String cidade;
	
	@Column(name = "uf", nullable = true, unique = false)
	private String uf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAno_termino() {
		return ano_termino;
	}

	public void setAno_termino(String ano_termino) {
		this.ano_termino = ano_termino;
	}

	public String getMes_termino() {
		return mes_termino;
	}

	public void setMes_termino(String mes_termino) {
		this.mes_termino = mes_termino;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano_termino == null) ? 0 : ano_termino.hashCode());
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((mes_termino == null) ? 0 : mes_termino.hashCode());
		result = prime * result + ((razao == null) ? 0 : razao.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erros other = (Erros) obj;
		if (ano_termino == null) {
			if (other.ano_termino != null)
				return false;
		} else if (!ano_termino.equals(other.ano_termino))
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (mes_termino == null) {
			if (other.mes_termino != null)
				return false;
		} else if (!mes_termino.equals(other.mes_termino))
			return false;
		if (razao == null) {
			if (other.razao != null)
				return false;
		} else if (!razao.equals(other.razao))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}

}
