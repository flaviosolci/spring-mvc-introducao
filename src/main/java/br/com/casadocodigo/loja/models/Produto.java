package br.com.casadocodigo.loja.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String titulo;

	private String descricao;

	private int paginas;

	@DateTimeFormat
	private Calendar dataLancamento;

	private String sumarioPath;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<Preco> precos;

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(final String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(final List<Preco> precos) {
		this.precos = precos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(final int paginas) {
		this.paginas = paginas;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the dataLancamento
	 */
	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * @param dataLancamento the dataLancamento to set
	 */
	public void setDataLancamento(final Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("Produto [id=%s, titulo=%s, descricao=%s, paginas=%s, dataLancamento=%s, precos=%s]", id, titulo, descricao, paginas, dataLancamento,
				precos != null ? precos.subList(0, Math.min(precos.size(), maxLen)) : null);
	}

}
