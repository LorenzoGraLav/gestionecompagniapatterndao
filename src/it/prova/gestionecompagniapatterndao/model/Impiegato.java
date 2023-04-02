package it.prova.gestionecompagniapatterndao.model;

import java.time.LocalDate;

public class Impiegato {
	private Long id;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private LocalDate dataDiNascita;
	private LocalDate dataAssunzione;
	private Compagnia compagnia;
	
	public Impiegato() {
		
	}
	
	public Impiegato(String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, LocalDate dataAssunzione) {
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
	}
	
	
	public Impiegato(Long id, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita,
			LocalDate dataAssunzione) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
	}



	public Impiegato(Long id, String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita,
			LocalDate dataAssunzione, Compagnia compagnia) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
		this.compagnia = compagnia;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCodiceFiscale() {
		return codiceFiscale;
	}


	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}


	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}


	public LocalDate getDataAssunzione() {
		return dataAssunzione;
	}


	public void setDataAssunzione(LocalDate dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}


	public Compagnia getCompagnia() {
		return compagnia;
	}


	public void setCompagnia(Compagnia compagnia) {
		this.compagnia = compagnia;
	}


	@Override
	public String toString() {
		return "Impiegato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale
				+ ", datanascita=" + dataDiNascita + ", dataassunzione=" + dataAssunzione + ", compagnia=" + compagnia
				+ "]";
	}
	
	
}
